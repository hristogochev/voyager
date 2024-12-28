# Advanced navigation

### Custom rendering position

You can specify where exactly the current screen of a `Navigator` is rendered.

This can be useful if you want to, for example, display a top bar above your screens.

A call to `Navigator()` provides a `scope` where you can write additional composable logic that wraps its current
screen. If you choose to use it, you must call `CurrentScreen` to render any contents:

```kotlin
@Composable
fun App(){
    Navigator(HomeScreen){ navigator ->
        Scaffold(
            topBar = { /* ... */ },
            content = { CurrentScreen(navigator) },
        )
    }
}
```

### Pass arguments to the current screen content

You may want to pass arguments to the contents of a screen, instead of its screen class.

One of the cases where this can be useful is if you need to pass the `innerPadding` of a `Scaffold`.

To do this, you can create your own screen interface that accepts the arguments you need:

```kotlin
interface ScaffoldScreen : Screen {

    @Composable
    fun Content(innerPadding: PaddingValues)

    @Composable
    override fun Content() {
        error("Called Content without arguments")
    }
}
```

And then cast the current screen to it, invoking its new `Content` function:

```kotlin
@Composable
fun App(){
    Navigator(HomeScreen){ navigator ->
        Scaffold(
            topBar = { /* ... */ },
            content = { innerPadding ->
                CurrentScreen(navigator){ currentScreen ->
                    (currentScreen as ScaffoldScreen).Content(innerPadding)
                }
            }
        )
    }
}
```

### Custom back handlers

By default, any navigator has its own back handler which `pops` the current screen.
You can opt out of this behaviour by setting the `defaultBackHandler` flag to `false`.
You can then optionally create your own back handler:

```kotlin
@Composable
fun App(){
    Navigator(HomeScreen, defaultBackHandler = false){ navigator ->
   
        // Custom back handler   
        BackHandler(enabled = /* ... */){
            // ...
        }
        
        CurrentScreen(navigator)
    }
}
```
