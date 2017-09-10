package uk.co.avsoftware.foursquaresearch;

import android.app.Application;
import android.content.Context;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Date;

import timber.log.Timber;
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

        // Firebase Messaging
        FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
        String messageToken = instance.getToken();
        Timber.d("Message Token: " + messageToken);
        Timber.d("ID " + instance.getId());
        Timber.d("Created " + new Date(instance.getCreationTime()));
    }

    public static RetrofitComponent getRetrofitComponent( Context ctx){
        FoursquareApplication app = (FoursquareApplication) ctx.getApplicationContext();
        return app.mRetrofitComponent;
    }
}
