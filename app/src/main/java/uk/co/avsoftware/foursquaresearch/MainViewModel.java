package uk.co.avsoftware.foursquaresearch;

import android.databinding.ObservableField;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import uk.co.avsoftware.foursquaresearch.api.FoursquareApi;
import uk.co.avsoftware.foursquaresearch.model.Venue;

/**
 * Created by andy on 07/07/2017.
 */

public class MainViewModel {

    FoursquareApi mApi;

    public final ObservableField<String> message = new ObservableField<>("unset");

    public MainViewModel(FoursquareApi api) {
        this.mApi = api;
    }

    public Observable<List<Venue>> searchVenues(String searchTerm ){
        return mApi.searchVenues("shop", BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
                .subscribeOn(Schedulers.io())
                .flatMap(venueAPIResponse -> {
                    if (venueAPIResponse.response()!=null){
                        return Observable.just(venueAPIResponse.response().venues());
                    }
                    else
                    {
                        return Observable.empty();
                    }
                });

    }
}
