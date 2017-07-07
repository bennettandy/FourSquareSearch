package uk.co.avsoftware.foursquaresearch;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import uk.co.avsoftware.foursquaresearch.dagger.DaggerMainActivityComponent;
import uk.co.avsoftware.foursquaresearch.dagger.MainActivityComponent;
import uk.co.avsoftware.foursquaresearch.dagger.RetrofitComponent;
import uk.co.avsoftware.foursquaresearch.databinding.ActivityMainBinding;
import uk.co.avsoftware.foursquaresearch.model.Venue;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CompositeDisposable disposable;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        callApi();
    }

    private void callApi(){
        Observable<List<Venue>> observable = mViewModel.searchVenues("shop");
                observable.subscribe(this::handleVenueList,
                        throwable -> Log.e(TAG, "Failed", throwable), () -> Log.d(TAG, "Completed") );
    }

    private void handleVenueList(List<Venue> venues){
        Log.d(TAG, "Got " + venues.size() + " venues");

        mViewModel.message.set("Got " + venues.size() + " Venues");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
