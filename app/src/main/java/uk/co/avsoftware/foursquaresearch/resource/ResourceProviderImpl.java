package uk.co.avsoftware.foursquaresearch.resource;

import android.content.Context;

/**
 * Created by andy on 07/07/2017.
 */

public class ResourceProviderImpl implements ResourceProvider {

    private Context context;

    public ResourceProviderImpl( Context ctx ){
        this.context = ctx;
    }

    @Override
    public String getString(int resId) {
        return context.getString(resId);
    }

    @Override
    public String getString(int resId, Object... formatArgs) {
        return context.getString(resId, formatArgs);
    }
}
