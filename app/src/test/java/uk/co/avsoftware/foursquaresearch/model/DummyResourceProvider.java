package uk.co.avsoftware.foursquaresearch.model;

import uk.co.avsoftware.foursquaresearch.resource.ResourceProvider;

/**
 * Created by andy on 07/07/2017.
 */

public class DummyResourceProvider implements ResourceProvider {
    @Override
    public String getString(int resId) {
        return ""+resId;
    }

    @Override
    public String getString(int resId, Object... formatArgs) {
        return ""+resId;
    }
}
