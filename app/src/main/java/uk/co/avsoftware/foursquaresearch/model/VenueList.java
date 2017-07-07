package uk.co.avsoftware.foursquaresearch.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by andy on 06/07/2017.
 */

@AutoValue
public abstract class VenueList {

    public static VenueList create(List<Venue> venues){
        return new AutoValue_VenueList(venues);
    }

    public static TypeAdapter<VenueList> typeAdapter(Gson gson) {
        return new AutoValue_VenueList.GsonTypeAdapter(gson);
    }

    public abstract List<Venue> venues();
}
