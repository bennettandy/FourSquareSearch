package uk.co.avsoftware.foursquaresearch.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by andy on 06/07/2017.
 */

@AutoValue
public abstract class MetaData {

    public static MetaData create(String code, String requestId, String error_type, String error_detail){
        return new AutoValue_MetaData(code, requestId, error_type, error_detail);
    }
    public static TypeAdapter<MetaData> typeAdapter(Gson gson) {
        return new AutoValue_MetaData.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract String code();
    @Nullable
    abstract String requestId();
    @Nullable
    abstract String error_type();
    @Nullable
    public abstract String error_detail();
}
