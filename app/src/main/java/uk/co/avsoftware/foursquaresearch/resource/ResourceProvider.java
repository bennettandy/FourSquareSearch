package uk.co.avsoftware.foursquaresearch.resource;

/**
 * Created by andy on 07/07/2017.
 */

public interface ResourceProvider {
    String getString(int resId);
    String getString(int resId, Object... formatArgs);
}
