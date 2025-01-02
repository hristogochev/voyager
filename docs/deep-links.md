# Deep links

!!! warning
    Currently, Vortex does not provide a built-in solution to handle Deeplink and URIs. see issues [#149](https://github.com/adrielcafe/voyager/issues/149) and [#382](https://github.com/adrielcafe/voyager/issues/382) of Voyager.

You can initialize the `Navigator` with multiple screens, that way, the first visible screen will be the last one and will be possible to return (`pop()`) to the previous screens.

```kotlin
val postId = getPostIdFromIntent()

setContent {
    Navigator(
        HomeScreen,
        PostListScreen(),
        PostDetailsScreen(postId)
    )
}
```