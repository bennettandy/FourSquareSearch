package uk.co.avsoftware.foursquaresearch.dagger;

import dagger.Component;
import uk.co.avsoftware.foursquaresearch.main.MainActivity;

/**
 * Created by andy on 07/07/2017.
 */

@ActivityScope
@Component(dependencies = RetrofitComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity activity);
}
