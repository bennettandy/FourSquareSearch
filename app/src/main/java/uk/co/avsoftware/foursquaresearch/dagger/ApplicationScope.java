package uk.co.avsoftware.foursquaresearch.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by andy on 18/06/2017.
 */

@Scope
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ApplicationScope
{
}