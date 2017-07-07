package uk.co.avsoftware.foursquaresearch.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.co.avsoftware.foursquaresearch.model.VenueAPIResponse;

/**
 * Created by andy on 07/07/2017.
 *
 * see https://developer.foursquare.com/docs/venues/search
 */

public interface FoursquareApi {
    @GET("/v2/venues/search?v=20130815")
    public Single<VenueAPIResponse> searchVenuesNear(@Query("near") String searchTerm, @Query("client_id") String id, @Query("client_secret") String secret );
}
