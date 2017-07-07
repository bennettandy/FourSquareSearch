package uk.co.avsoftware.foursquaresearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.avsoftware.foursquaresearch.api.FoursquareApi;
import uk.co.avsoftware.foursquaresearch.dagger.DaggerMainActivityComponent;
import uk.co.avsoftware.foursquaresearch.dagger.MainActivityComponent;
import uk.co.avsoftware.foursquaresearch.dagger.RetrofitComponent;
import uk.co.avsoftware.foursquaresearch.model.Venue;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CompositeDisposable disposable;

    @Inject
    protected FoursquareApi mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        disposable = new CompositeDisposable();

        setContentView(R.layout.activity_main);

        RetrofitComponent retrofitComponent = FoursquareApplication.getRetrofitComponent(this);
        MainActivityComponent mainComponent = DaggerMainActivityComponent.builder()
                .retrofitComponent(retrofitComponent)
                .build();
        mainComponent.inject(this);

        Log.d(TAG, "Got api " + mApi);
    }

    @Override
    protected void onResume() {
        super.onResume();
        callApi();
    }

    private void callApi(){
        // TODO: refactor and remove requirements of client id and secret from this call
        Observable<Venue> observable = mApi.searchVenues("shop", getString(R.string.client_id), getString(R.string.client_secret));

        disposable.add( observable.subscribeOn(Schedulers.io()).subscribe(venue -> Log.d(TAG, "Venue " + venue.name()), throwable -> Log.e(TAG, "Failed", throwable), () -> Log.d(TAG, "Completed")) );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
