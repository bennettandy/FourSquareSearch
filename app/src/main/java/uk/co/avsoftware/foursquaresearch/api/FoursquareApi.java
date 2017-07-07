package uk.co.avsoftware.foursquaresearch.api;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import uk.co.avsoftware.foursquaresearch.model.Venue;

/**
 * Created by andy on 07/07/2017.
 *
 * see https://developer.foursquare.com/docs/venues/search
 */

public interface FoursquareApi {
    @GET("venues/search")
    public Observable<Venue> searchVenues(@Field("search") String searchTerm );
}
