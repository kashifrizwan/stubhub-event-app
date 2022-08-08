# stubhub-event-app

This Android application receives a list of recursive events from Json assets and display in the recycler view. It also allows user to search the events from Json directory with City and Price tags.

## Architecture

- Model-View-ViewModel
- Hilt Dagger for Dependency Injection
- LiveData for Reactive Programming
- ViewBinding
- A repository making a decision to fetch data from local or remote source
- A local source parsing Json file to a Kotlin data class

## Design
![alt text](https://github.com/kashifrizwan/stubhub-event-app/blob/master/design.png?raw=true)