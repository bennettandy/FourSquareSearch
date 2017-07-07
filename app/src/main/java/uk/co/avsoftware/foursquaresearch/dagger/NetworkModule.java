package uk.co.avsoftware.foursquaresearch.dagger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import uk.co.avsoftware.foursquaresearch.model.gson.GeneratedTypeAdapterFactory;

/**
 * Created by andy on 07/07/2017.
 */

@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapterFactory(GeneratedTypeAdapterFactory.create());
        return gsonBuilder.create();
    }
}
