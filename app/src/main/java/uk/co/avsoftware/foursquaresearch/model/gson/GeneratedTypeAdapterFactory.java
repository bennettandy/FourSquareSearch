package uk.co.avsoftware.foursquaresearch.model.gson;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by andy on 07/07/2017.
 */

@GsonTypeAdapterFactory
public abstract class GeneratedTypeAdapterFactory implements TypeAdapterFactory{
    // Static factory method to access the package
    // private generated implementation
    public static TypeAdapterFactory create() {
        return new AutoValueGson_GeneratedTypeAdapterFactory();
    }
}
