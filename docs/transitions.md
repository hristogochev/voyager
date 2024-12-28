# Transitions


### Default transitions

You can specify the default `appear` and `disappear` transitions for every `Screen` in a `Navigator`.
Vortex comes bundled with a set of a few transitions you can use for your apps, in this example we will use the `FadeTransition`:

```kotlin hl_lines="4"
@Composable
fun App(){
    Navigator(HomeScreen) { navigator->
        CurrentScreen(navigator, onScreenAppear = FadeTransition, onScreenDisappear = FadeTransition)
    }
}
```

This will make it so that the default fade transition will play everytime a new screen is pushed or popped from the navigator.

### Per screen transitions

You can make a screen appear with a specific transition:

```kotlin hl_lines="14 21"
@Composable
fun App(){
    Navigator(HomeScreen)
}

data object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        Button(
            onClick = {
                navigator.push(DetailsScreen(1, onAppear = SlideTransition.Horizontal.Appear))
            }
        ) {
            Text(text = "Show details")
        }
    }
}
data class DetailsScreen(val id: Long, override val onAppear: ScreenTransition?) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        Button(
            onClick = {
                navigator.pop()
            }
        ) {
            Text(text = "Go back")
        }
    }
}
```

### Custom transitions

You can create your own transitions by implementing the `ScreenTransition` interface.

Take a look at the [source of the available transitions](https://github.com/hristogochev/vortex/tree/main/vortex/src/commonMain/kotlin/com/hristogochev/vortex/transitions) for working examples.

!!! warning
    Have encounter `Screen was used multiple times` crash? Provide a `uniqueScreenKey` for your Screens

    ```kotlin hl_lines="1"
    data class ScreenFoo(override val key: String = uniqueScreenKey()) : Screen {
    
        @Composable
        override fun Content() {
            // ...
        }
    }
    ```





