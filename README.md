# FourSquareSearch
Testing foursquare search api https://developer.foursquare.com/docs/venues/search

Tried to use standard libraries including Dagger2 and RX Java.
Also using RetroLambda and AutoValue.

Initially setup Retrofit instance in Dagger2, used this to generate the call to the FourSquare Api.
FourSquare API doesn't require authentication but does require the app to be registered and provide Client ID and Client Secret.
These were initally hardcoded then moved to the BuildConfig which seemed the right place for them.

Using MVVM with a View Model class exposing ObservableFields that are wired to the UI via Android Data Bindings in the layout xml.

ViewModel can be easily supplied with a mock API for testing.

Had to time box it and we can do much more with the displayed Venues.
We could also do more around testing, but we had a time limit.

Entering a city name and pressing search results in the population of a RecyclerView with a number of Venue Cards.
