<h1 align="center">🎥ThmanyahCast</h1>


<h1>Project Overview</h1>

A modern Android application built with Jetpack Compose that dynamically renders content sections fetched from an API. The app intelligently displays different types of content, Audio Articles, Episodes, Audio Books, and Podcasts, using various UI layouts based on content type and view configuration.
---------------------------

- **Infinite Scrolling**
  Sections are displayed with endless scrolling functionality on the main screen
- **Dynamic View Types**
  Each section has a specific display type: Two-line Grid, Square Grid, Queue, Big Square
  Browse the latest movies with seamless infinite scrolling.

https://github.com/user-attachments/assets/ad679a9a-9c48-440a-8ef5-37105fe3a6fb

- **Search Feature**
    - User types in the search box
    - App waits 200ms after the user stops typing
    - Then sends a search request to the server
    - Prevents too many API calls while the user is still typing

https://github.com/user-attachments/assets/d3fd3be4-0157-4853-a78f-65d7c1ed8b5f

- The app is designed to work properly even when there is no internet connection.

https://github.com/user-attachments/assets/cfd79a6f-b080-40f3-8eaa-1f8c113ba2ff


---------------------------
---------------------------




Model-View-ViewModel (MVVM) is a client application architecture template proposed by John Gossman
as an alternative to the MVC and MVP patterns when using Data Binding technology. Its concept
separates data presentation logic from business logic by moving it into a specific class, allowing
for a clear distinction.

![MVVM3](https://user-images.githubusercontent.com/1812129/68319232-446cf900-00be-11ea-92cf-cad817b2af2c.png)

**Why Promoting MVVM VS MVP:**

- The ViewModel has a Built-in LifecycleOwner; however, the Presenter does not, and you have to take
  this responsibility on your side.
- ViewModel doesn't have a reference for View; on the other hand, Presenter still holds a reference
  for View, even if you made it a weak reference.
- ViewModel survives configuration changes, while it is your responsibility to survive the
  configuration changes in the Presenter's case. (Saving and restoring the UI state)

**MVVM Best Practice:**

- Avoid references to Views in ViewModels.
- Instead of pushing data to the UI, let the UI observe changes.
- Distribute responsibilities, and add a domain layer if needed.
- Add a data repository as the single-point entry to your data.
- Expose information about the state of your data using a wrapper or another LiveData.
- Consider edge cases, leaks, and how long-running operations can affect the instances in your
  architecture.
- Don’t put logic in the ViewModel that is critical to saving a clean state or related to data. Any
  call you make from a ViewModel can be the last one.

**Keep your code clean according to MVVM**
-----------------------------

- Yes, liveData is easy and powerful, but you should know how to use it.
- For a live date that will emit a data stream, it has to be in your
  data layer and don't inform those observables anything else, like which thread they will consume,
  cause it is another
- For live data that emits UI binding events, it must be in your ViewModel Layer.
- Observers in UI Consume and react to live data values and bind them.
  responsibility, and according to the `Single responsibility principle`
  In `SOLID (object-oriented design)`, so don't break this concept by
  mixing the responsibilities.


![mvvm](https://github.com/user-attachments/assets/553d131f-8d3a-4f92-8114-b411006d1b5f)


## Challenges

Time Constraint Challenges:

Completing complex modularization within tight deadlines
Making architectural decisions quickly while ensuring long-term maintainability
Balancing code quality with delivery speed


## Enhancements

1. Advanced Caching Strategy: Implement multi-layer caching with Room database for offline-first
   experience and Redis-like in-memory caching for frequently accessed data.
2. Dynamic Feature Modules: Convert feature modules to dynamic delivery modules, allowing users to
   download only needed features on demand, reducing initial app size.
