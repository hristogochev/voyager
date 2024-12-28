# Lifecycle

Inside a `Screen`, you can call `ScreenDisposableEffect` to execute a block of code 
the first time a `Screen` appears or any of its keys change.
You can also execute a block of code 
when the `Screen` gets disposed or any of its keys change using the `onDispose` callback.

```kotlin
data object SearchScreen : Screen {

    @Composable
    override fun Content() {
    
        ScreenDisposableEffect {
            println("Created search screen")
            onDispose {
                println("Disposed of search screen")
            }
        }
        
        val counter = /*...*/
    
        ScreenDisposableEffect(counter) {
            println("Counter: $counter")
            onDispose {
                println("Counter changed")
            }
        }
        
        val lifecycleOwner = LocalLifecycleOwner.current
    
        ScreenDisposableEffect(lifecycleOwner) {
        
            val observer = object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
                fun onResume() {
                    println("Application resumed")
                }
            }
            
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }  
        }
        
        // ...
    }
}
```
