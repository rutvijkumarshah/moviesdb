The Movies Db
===============

Tiny Android app to showcase themoviedb api integration and using Android Architecture component.

### Features.
1. Get list of "Now playing" movies.
2. List is supported in portait and landscape mode.

### Architecture details 
- App is using MVVM + Android architecture components.
- The current implementation is only using Remote repository, but it can be easily incorporated to use local repository for offline mode.
- UI is totally separted from business logic and netwowkring.
- LiveData & ViewModel handles most of lifecycle changes out of the box.
- Gson is used to map json data to Java model objects.
- OkHttp & Retrofit handles the networking to remote server.


### Future plans
- To utilize the local database(room) for offline support & unidirectional data flow model.
- Build Infinite scorlling using PageList.
- More screen to filter/sort movies.

## Demo video
https://github.com/rutvijkumarshah/moviesdb/blob/master/movies.mp4?raw=true
