package uk.co.avsoftware.foursquaresearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import uk.co.avsoftware.foursquaresearch.api.FoursquareApi;
import uk.co.avsoftware.foursquaresearch.dagger.DaggerMainActivityComponent;
import uk.co.avsoftware.foursquaresearch.dagger.MainActivityComponent;
import uk.co.avsoftware.foursquaresearch.dagger.RetrofitComponent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    protected FoursquareApi mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitComponent retrofitComponent = FoursquareApplication.getRetrofitComponent(this);
        MainActivityComponent mainComponent = DaggerMainActivityComponent.builder()
                .retrofitComponent(retrofitComponent)
                .build();
        mainComponent.inject(this);

        Log.d(TAG, "Got api " + mApi);
    }
}
