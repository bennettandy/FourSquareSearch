package uk.co.avsoftware.foursquaresearch.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by andy on 06/07/2017.
 *
 * This wasn't needed in the end, but does allow us to deal with location
 */

@AutoValue
public abstract class Location {

    public static TypeAdapter<Location> typeAdapter(Gson gson) {
        return new AutoValue_Location.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract String lat();
    @Nullable
    public abstract String lng();
    @Nullable
    public abstract List<String> formattedAddress();
}
