package uk.co.avsoftware.foursquaresearch.dagger;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import uk.co.avsoftware.foursquaresearch.api.FoursquareApi;
import uk.co.avsoftware.foursquaresearch.main.MainViewModel;
import uk.co.avsoftware.foursquaresearch.resource.ResourceProvider;

/**
 * Created by andy on 07/07/2017.
 */

@Module
public class MainActivityModule {

    @Provides
    @ActivityScope
    FoursquareApi provideFourSquareApi( Retrofit retrofit) {
        return retrofit.create(FoursquareApi.class);
    }

    @Provides
    @ActivityScope
    MainViewModel provideMainViewModel(FoursquareApi api, ResourceProvider resourceProvider){
        return new MainViewModel(api, resourceProvider);
    }
}
