## Key Features
- Enjoy viewing your coins in a clear, simple and smooth interface!
- A more understandable interface experience with custom developed charts.
- Create your own favorite list and get the chance to follow it from different devices!

## Architecture
MVVM (Model-View-ViewModel) with Clean Architecture principles in this project. 
This architectural approach provides independence, scalability and testability
by separating the different layers of the project.

### Modules
**Feature** - Feature modules contain feature-specific codes. Thus, while developing on different features, it is aimed to minimize code conflict and to make the features independent from each other.
**Core** - Core modules contain code that can be used in the entire application and that will affect the entire application. It also provides a more independent development experience by moving database and network operations away from feature modules as much as possible.


### Layers
The feature modules is divided into the following layers:

- **Data**: Access to data sources, network requests and data storage operations are performed in this layer. This layer works independently of the data sources, providing the retrieval and storage of data.
- **Domain**: Business logic and data processing operations take place in this layer. The operations required to access the data (querying, transforming, filtering etc.) are defined here.
- **Presentation**: User interface related operations take place in this layer. Data retrieval and display, user interactions, and UI feedback are managed in this layer. Communication is provided with ViewModels, which minimizes dependency on Views.

## Libraries
- **Material Design** - UI design
- **AndroidX** - ViewModel, Splash Screen API, Paging3
- **KotlinX** - Coroutines, Flow, StateFlow, Parcelization
- **Hilt** -  Dependency Injection
- **Navigation Component** - User navigation
- **Glide** - Loading Images
- **Room** - Database Storage
- **Retrofit** - Network requests
- **OkHttp-Chucker** - Manage and monitor HTTP requests


### Testing Libraries:
- **JUnit4** - Unit testing framework

## Screenshots
