package uk.co.avsoftware.foursquaresearch.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by andy on 06/07/2017.
 */

@AutoValue
public abstract class VenueAPIResponse {

    public static TypeAdapter<VenueAPIResponse> typeAdapter(Gson gson) {
        return new AutoValue_VenueAPIResponse.GsonTypeAdapter(gson);
    }

    public abstract MetaData meta();
    public abstract VenueList response();

    public boolean isError(){
        return !meta().code().equals("200");
    }
}
