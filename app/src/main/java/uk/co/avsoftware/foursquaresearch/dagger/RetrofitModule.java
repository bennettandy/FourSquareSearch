package uk.co.avsoftware.foursquaresearch.dagger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.avsoftware.foursquaresearch.model.gson.GeneratedTypeAdapterFactory;
import uk.co.avsoftware.foursquaresearch.resource.ResourceProvider;

/**
 * Created by andy on 07/07/2017.
 */

@Module
public class RetrofitModule {

    private String mBaseUrl;
    private File mCacheDirectory;
    private ResourceProvider resourceProvider;

    private static final int CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB

    public RetrofitModule(String baseUrl, File cacheDirectory, ResourceProvider provider) {
        this.mBaseUrl = baseUrl;
        this.mCacheDirectory = cacheDirectory;
        this.resourceProvider = provider;
    }

    @Provides
    @ApplicationScope
    ResourceProvider provideResourceProvider(){
        return resourceProvider;
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
    Cache provideOkHttpCache() {
        return new Cache(mCacheDirectory, CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(Cache cache) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.cache(cache);
        return builder.build();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }
}
