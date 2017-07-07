package uk.co.avsoftware.foursquaresearch;

import android.app.Application;
import android.content.Context;

import uk.co.avsoftware.foursquaresearch.dagger.DaggerRetrofitComponent;
import uk.co.avsoftware.foursquaresearch.dagger.RetrofitComponent;
import uk.co.avsoftware.foursquaresearch.dagger.RetrofitModule;
import uk.co.avsoftware.foursquaresearch.resource.ResourceProviderImpl;

/**
 * Created by andy on 07/07/2017.
 */

public class FoursquareApplication extends Application {

    private RetrofitComponent mRetrofitComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // create Application Scope Component
        mRetrofitComponent = DaggerRetrofitComponent.builder()
                // list of modules that are part of this component need to be created here too
                .retrofitModule(new RetrofitModule("https://api.foursquare.com", getCacheDir(), new ResourceProviderImpl(this)))
                .build();
    }

    public static RetrofitComponent getRetrofitComponent( Context ctx){
        FoursquareApplication app = (FoursquareApplication) ctx.getApplicationContext();
        return app.mRetrofitComponent;
    }
}
