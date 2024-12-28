# Kodein integration

!!! success
    To use the `rememberScreenModel` you should first import `io.github.hristogochev:vortex-kodein` (see [Setup](../setup.md)).

Declare your `ScreenModel`s using the `bindProvider` bind.

```kotlin
val homeModule = DI.Module(name = "home") {
    bindProvider { HomeScreenModel() } 
}
```

Call `rememberScreenModel()` or `rememberNavigatorScreenModel()` to get a new instance.

```kotlin
class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<HomeScreenModel>()
        // ...
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.rememberNavigatorScreenModel<HomeScreenModel>()
        // ...
    }
}
```

!!! info "You can find source code for a working example [here](https://github.com/hristogochev/vortex)."