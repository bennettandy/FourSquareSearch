package uk.co.avsoftware.foursquaresearch.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by andy on 06/07/2017.
 */

@AutoValue
public abstract class Venue {

    public static Venue create(String name, String id, String url) {
        return new AutoValue_Venue(name, id, url, null, null);
    }

    public static TypeAdapter<Venue> typeAdapter(Gson gson) {
        return new AutoValue_Venue.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract String name();
    @Nullable
    public abstract String id();
    @Nullable
    public abstract String url();
    @Nullable
    public abstract Location location();
    @Nullable
    public abstract Contact contact();
}
