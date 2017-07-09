package uk.co.avsoftware.foursquaresearch.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by andy on 06/07/2017.
 **/

@AutoValue
public abstract class Contact {

    public static TypeAdapter<Contact> typeAdapter(Gson gson) {
        return new AutoValue_Contact.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract String twitter();
    @Nullable
    public abstract String instagram();
    @Nullable
    public abstract String facebook();
    @Nullable
    public abstract String phone();
    @Nullable
    public abstract String formattedPhone();
}
