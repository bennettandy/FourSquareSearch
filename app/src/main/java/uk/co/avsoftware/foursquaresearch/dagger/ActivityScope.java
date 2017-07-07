package uk.co.avsoftware.foursquaresearch.dagger;

/**
 * Created by andy on 18/06/2017.
 */

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
