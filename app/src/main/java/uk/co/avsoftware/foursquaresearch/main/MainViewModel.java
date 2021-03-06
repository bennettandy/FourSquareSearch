package uk.co.avsoftware.foursquaresearch.main;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import uk.co.avsoftware.foursquaresearch.BuildConfig;
import uk.co.avsoftware.foursquaresearch.R;
import uk.co.avsoftware.foursquaresearch.api.FoursquareApi;
import uk.co.avsoftware.foursquaresearch.model.Venue;
import uk.co.avsoftware.foursquaresearch.model.VenueAPIResponse;
import uk.co.avsoftware.foursquaresearch.resource.ResourceProvider;

/**
 * Created by andy on 07/07/2017.
 */

public class MainViewModel {

    private FoursquareApi mApi;
    private ResourceProvider mResourceProvider;

    private static final int MIN_SEARCH_LENGTH = 3;

    public final ObservableField<String> message = new ObservableField<>("");

    public final ObservableField<String> searchTerm = new ObservableField<>("");

    public final ObservableBoolean searchEnabled = new ObservableBoolean(false);

    public final ObservableBoolean progress = new ObservableBoolean(false);

    public final PublishSubject<List<Venue>> venues = PublishSubject.create();

    private CompositeDisposable disposable = new CompositeDisposable();

    public MainViewModel(FoursquareApi api, ResourceProvider resourceProvider) {
        this.mApi = api;
        this.mResourceProvider = resourceProvider;

        message.set(mResourceProvider.getString(R.string.enter_location));

        searchTerm.addOnPropertyChangedCallback(new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable sender, int propertyId) {
                String term = searchTerm.get();
                searchEnabled.set(term != null && term.length() > MIN_SEARCH_LENGTH);
            }
        });
    }

    public void searchVenues() {
        disposable.add(mApi.searchVenuesNear(searchTerm.get(), BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disp -> { progress.set(true); message.set(mResourceProvider.getString(R.string.searching));})
                .doOnSuccess(venueAPIResponse -> progress.set(false))
                .doOnError(throwable -> progress.set(false))
                .observeOn(Schedulers.computation())
                .map(new Function<VenueAPIResponse, List<Venue>>() {
                    @Override
                    public List<Venue> apply(@NonNull VenueAPIResponse venueAPIResponse) throws Exception {
                        if (venueAPIResponse.response() != null) {
                            return venueAPIResponse.response().venues();
                        } else {
                            return Collections.emptyList();
                        }
                    }
                })
                .subscribe(venues::onNext, this::handleVenueError));

    }

    private void handleVenueError(Throwable t){
        message.set(mResourceProvider.getString(R.string.search_error));
    }

    void dispose() {
        disposable.dispose();
    }
}
