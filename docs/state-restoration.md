# State restoration

The default state restoration mechanism of Vortex requires every instance of `Screen` to be savable
in
a [Bundle](https://developer.android.com/guide/components/activities/parcelables-and-bundles),
therefore all params and
properties of your `Screen` implementations should be either `Java Serializable` or `Parcelable`.
Otherwise, your app
will crash upon attempting to restore its state.

Keep in mind that `Parcelables` are not `Java Serializable` by default and `Java Serializables` are
not `Parcelable` by
default.

### Java Serializable

```kotlin
// ✔️ DO
data class Post(/*...*/) : java.io.Serializable

data class ValidScreen(
    val userId: Long, // Built-in serializable types
    val post: Post // Your own serializable types
) : Screen {

    // Serializable properties
    val tag = "ValidScreen"

    // Lazily initialized serializable types
    val randomId by lazy { UUID.randomUUID() }

    // ...
}

// 🚫 DON'T
class Post(/*...*/)

data class InvalidScreen(
    val context: Context, // Built-in non-serializable types
    val post: Post, // Your own non-serializable types
    val parcelable: SomeParcelable // Android Parcelable is not Java Serializable by default
) : Screen {

    // Non-serializable properties
    val postService = PostService()

    // ...
}
```

### Android Parcelables

```kotlin
// ✔️ DO
@Parcelize
data class Post(/*...*/) : Parcelable

@Parcelize
data class ValidScreen(
    val post: Post // Your own parcelable types
) : Screen, Parcelable {
    // ...
}

// 🚫 DON'T
class Post(/*...*/)

@Parcelize
data class InvalidScreen(
    val context: Context, // Built-in non-parcelable types
    val post: Post, // Your own non-parcelable types
    val serializable: SomeSerializable // Java Serializable are not Android Parcelable by default
) : Screen, Parcelable {
    // ...
}
```

#### Enforcing Android Parcelable on your screens

You can build your own Screen type for enforcing in at compile time that all yours screens should be
Parcelable by
creating an interface that implement Parcelable.

```kotlin
interface ParcelableScreen : Screen, Parcelable

// Compile
@Parcelize
data class Post(/*...*/) : Parcelable

@Parcelize
data class ValidScreen(
    val post: Post
) : ParcelableScreen {
    // ...
}

// Not compile
data class Post(/*...*/)

@Parcelize
data class ValidScreen(
    val post: Post
) : ParcelableScreen {
    // ...
}
```

### Multiplatform state restoration

When working in a Multiplatform project you may need a common `Java Serializable` or `Parcelable`
interface/annotation,
you can create one like this:

```kotlin
// commonMain - module core
expect interface JavaSerializable

// androidMain - module core
actual typealias JavaSerializable = java.io.Serializable

// non AndroidMain (ios, web, etc) - module core
actual interface JavaSerializable
```

### Custom state restoration mechanism

You can create your own state restoration mechanism by inheriting from
the [NavigatorSaverProvider](https://github.com/hristogochev/vortex/blob/main/vortex/src/commonMain/kotlin/io/github/hristogochev/vortex/navigator/NavigatorSaverProvider.kt)
interface into a data object.

Keep in mind that a `Navigator` needs it's `key`, `screens` and `screenStateKeys` upon restoration.

You also need to pass it it's `parent` reference, which is conveniently accessible to you upon
implementing the interface.

Here’s an example of implementing a navigator saver provider, based on [kevinvanmierlo's solution](https://github.com/hristogochev/vortex/issues/1) for restoring screens with non-serializable parameters and properties:
```kotlin
@Composable
fun App() {
    // This is at the top of our root navigator,
    // but you can also have different navigator saver providers for each Navigator
    CompositionLocalProvider(
        LocalNavigatorSaverProvider provides ExternalNavigatorSaverProvider,
    ) {
        Navigator(HomeScreen)
    }
}

object NavigatorsStore {
    // A mutable map of navigators, that takes the navigators' keys as keys, and their screens and screenStateKeys as values
    val navigators: MutableMap<String, Map<String, Any>> = mutableMapOf()
}

data object ExternalNavigatorSaverProvider : NavigatorSaverProvider<String> {
    override fun provide(parent: Navigator?): Saver<Navigator, String> {
        return Saver(
            save = { navigator ->
                // We need to use `.toList()` to create a copy of the items and screen state keys,
                // otherwise their reference will be used, which will not work
                val contentsMap= mapOf(
                    "items" to navigator.items.toList(),
                    "screenStateKeys" to navigator.getAllScreenStateKeys().toList()
                )
                
                // Save the current navigator contents into an outside map, using its key
                NavigatorsStore.navigators[navigator.key] = contentsMap
                
                // Only tell our saver about the navigator's key, since we manage the saving externally
                navigator.key
            },
            restore = restore@{ key ->
                // If there are no saved contents for the navigator by it's key,
                // forget the current saved one and recreate the navigator
                val navigatorContents = NavigatorsStore.navigators[key] ?: return@restore null

                // If any of the core components of a navigator are missing,
                // forget the current saved one and recreate the navigator
                @Suppress("UNCHECKED_CAST")
                val savedScreens =
                    navigatorContents["items"] as? List<Screen> ?: run {
                        NavigatorsStore.navigators.remove(key)
                        return@restore null
                    }

                @Suppress("UNCHECKED_CAST")
                val savedScreenStateKeys =
                    navigatorContents["screenStateKeys"] as? List<String> ?: run {
                        NavigatorsStore.navigators.remove(key)
                        return@restore null
                    }

                Navigator(
                    initialScreens = savedScreens,
                    key = key,
                    parent = parent,
                    screenStateKeys = ThreadSafeSet(savedScreenStateKeys)
                )
            }
        )
    }
}
```

### Identifying screens

The `Screen` interface has a `key` property that defines it in each `Navigator`.<br>
The default key for a `Screen` is its name. You can override it to set your own key.

```kotlin
data class HomeScreen(
    override val key: String = "CUSTOM_KEY"
) : Screen {

    @Composable
    override fun Content() {
        // ...
    }
}
```

Vortex also has a `uniqueScreenKey` function, that generates a random key.

```kotlin
override val key = uniqueScreenKey()
```

!!! warning
You should **always** set your own key, if the screen is used multiple times in the same
`Navigator`, or is
an [anonymous](https://kotlinlang.org/docs/object-declarations.html#object-expressions)
or [local](https://kotlinlang.org/spec/declarations.html#local-class-declaration) class.



