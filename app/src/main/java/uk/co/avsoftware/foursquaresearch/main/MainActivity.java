package uk.co.avsoftware.foursquaresearch.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import uk.co.avsoftware.foursquaresearch.FoursquareApplication;
import uk.co.avsoftware.foursquaresearch.R;
import uk.co.avsoftware.foursquaresearch.dagger.DaggerMainActivityComponent;
import uk.co.avsoftware.foursquaresearch.dagger.MainActivityComponent;
import uk.co.avsoftware.foursquaresearch.dagger.RetrofitComponent;
import uk.co.avsoftware.foursquaresearch.databinding.ActivityMainBinding;
import uk.co.avsoftware.foursquaresearch.model.Venue;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CompositeDisposable disposable;

    private VenueViewAdapter viewAdapter;

    @Inject
    protected MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        disposable = new CompositeDisposable();

        RetrofitComponent retrofitComponent = FoursquareApplication.getRetrofitComponent(this);
        MainActivityComponent mainComponent = DaggerMainActivityComponent.builder()
                .retrofitComponent(retrofitComponent)
                .build();
        mainComponent.inject(this);

        // bind view model
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mViewModel);

        // set up recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAdapter = new VenueViewAdapter();
        recyclerView.setAdapter(viewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        disposable.add( mViewModel.venues
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleVenueList));
    }



    private void handleVenueList(List<Venue> venues){
        String message = getString(R.string.got_venues_message, venues.size());
        mViewModel.message.set(message);
        viewAdapter.setVenues(venues);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.dispose();
        disposable.dispose();
    }
}
