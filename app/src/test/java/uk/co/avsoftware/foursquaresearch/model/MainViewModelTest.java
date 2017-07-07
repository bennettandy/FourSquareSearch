package uk.co.avsoftware.foursquaresearch.model;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import uk.co.avsoftware.foursquaresearch.api.FoursquareApi;
import uk.co.avsoftware.foursquaresearch.main.MainViewModel;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


/**
 * Created by andy on 07/07/2017.
 */

public class MainViewModelTest {

    @Mock
    FoursquareApi apiService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testVenues_success() throws Exception {

        when(apiService.searchVenuesNear(anyString(), anyString(), anyString())).thenReturn(Single.just(createTestResponse(false)));

        MainViewModel vm = new MainViewModel(apiService);

        TestObserver<List<Venue>> observer = new TestObserver<>();

        vm.venues
                .doOnSubscribe(disposable -> vm.searchVenues())
                .subscribe(observer);

        observer.awaitCount(1);
        observer.dispose();
        observer.assertNoErrors();
        observer.assertValueCount(1);

        List<List<Venue>> values = observer.values();
        List<Venue> venues = values.get(0);
        assertEquals(2, venues.size());

        Venue one = venues.get(0);
        Venue two = venues.get(1);

        assertNotNull(one);
        assertNotNull(two);

        assertEquals("test one", one.name());
        assertEquals("test two", two.name());
    }

    @Test
    public void testVenues_empty() throws Exception {

        when(apiService.searchVenuesNear(anyString(), anyString(), anyString())).thenReturn(Single.just(createTestResponse(true)));

        MainViewModel vm = new MainViewModel(apiService);

        TestObserver<List<Venue>> observer = new TestObserver<>();

        vm.venues
                .doOnSubscribe(disposable -> vm.searchVenues())
                .subscribe(observer);

        observer.awaitCount(1);
        observer.dispose();
        observer.assertNoErrors();
        observer.assertValueCount(1);

        List<List<Venue>> values = observer.values();
        List<Venue> venues = values.get(0);
        assertEquals(0, venues.size());

    }


    private VenueAPIResponse createTestResponse(boolean empty) {
        MetaData meta = MetaData.create("200", null, null, null);
        List<Venue> venues = new ArrayList<>();
        if (!empty){
            venues.add(Venue.create("test one", "12345", "http://test.org"));
            venues.add(Venue.create("test two", "65432", "http://ignore.org"));
        }
        VenueList venueList = VenueList.create(venues);
        return VenueAPIResponse.create(meta, venueList);
    }
}
