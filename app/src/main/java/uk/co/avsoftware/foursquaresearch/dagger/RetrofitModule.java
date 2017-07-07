package uk.co.avsoftware.foursquaresearch.dagger;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.avsoftware.foursquaresearch.model.gson.GeneratedTypeAdapterFactory;

/**
 * Created by andy on 07/07/2017.
 */

@Module
public class RetrofitModule {

    private String mBaseUrl;

    private static final int CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB

    public RetrofitModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @ApplicationScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapterFactory(GeneratedTypeAdapterFactory.create());
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    Cache provideOkHttpCache(Application application) {
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        return builder.build();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }
}
