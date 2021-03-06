package uk.co.avsoftware.foursquaresearch.dagger;

import dagger.Component;
import retrofit2.Retrofit;
import uk.co.avsoftware.foursquaresearch.resource.ResourceProvider;

/**
 * Created by andy on 07/07/2017.
 */

@ApplicationScope
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    Retrofit retrofit();
    ResourceProvider resourceProvider();
}
