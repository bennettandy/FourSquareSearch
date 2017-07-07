package uk.co.avsoftware.foursquaresearch.main;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import uk.co.avsoftware.foursquaresearch.BuildConfig;
import uk.co.avsoftware.foursquaresearch.api.FoursquareApi;
import uk.co.avsoftware.foursquaresearch.model.Venue;

/**
 * Created by andy on 07/07/2017.
 */

public class MainViewModel {

    FoursquareApi mApi;

    private static final int MIN_SEARCH_LENGTH = 3;

    public final ObservableField<String> message = new ObservableField<>("unset");

    public final ObservableField<String> searchTerm = new ObservableField<>("fish");

    public final ObservableBoolean searchEnabled = new ObservableBoolean(false);

    public final ObservableBoolean progress = new ObservableBoolean(false);

    public final PublishSubject<List<Venue>> venues = PublishSubject.create();

    private CompositeDisposable disposable = new CompositeDisposable();

    public MainViewModel(FoursquareApi api) {
        this.mApi = api;

        searchTerm.addOnPropertyChangedCallback(new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable sender, int propertyId) {
                String term = searchTerm.get();
                searchEnabled.set(term != null && term.length() > MIN_SEARCH_LENGTH);
            }
        });
    }

    public void searchVenues() {
        disposable.add(mApi.searchVenues(searchTerm.get(), BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disp -> progress.set(true))
                .doOnComplete(() -> progress.set(false))
                .doOnError(throwable -> progress.set(false))
                .observeOn(Schedulers.computation())
                .flatMap(venueAPIResponse -> {
                    if (venueAPIResponse.response() != null) {
                        return Observable.just(venueAPIResponse.response().venues());
                    } else {
                        return Observable.empty();
                    }
                })
                .subscribe(venues::onNext));

    }

    public void dispose() {
        disposable.dispose();
    }
}
