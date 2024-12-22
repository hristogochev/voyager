[![Maven metadata URL](https://img.shields.io/maven-metadata/v?color=blue&metadataUrl=https://s01.oss.sonatype.org/service/local/repo_groups/public/content/cafe/adriel/voyager/voyager-core/maven-metadata.xml&style=for-the-badge)](https://repo.maven.apache.org/maven2/cafe/adriel/voyager/)
[![Android API](https://img.shields.io/badge/api-21%2B-brightgreen.svg?style=for-the-badge)](https://android-arsenal.com/api?level=21)
[![kotlin](https://img.shields.io/github/languages/top/adrielcafe/voyager.svg?style=for-the-badge&color=blueviolet)](https://kotlinlang.org/)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=for-the-badge)](https://ktlint.github.io/)
[![License MIT](https://img.shields.io/github/license/adrielcafe/voyager.svg?style=for-the-badge&color=orange)](LICENSE.md)

<h1 align="center">
    <img height="150" src="images/vortex_og.png"/>
    <br>
    <a href="https://io.github.hristogochev/vortex">Vortex</a>: Where Navigation Meets Momentum
</h1>

A minimalist and stable Compose multiplatform navigation library, built as a simplified fork
of [Voyager](https://github.com/adrielcafe/voyager).

Build scalable apps with a minimal, developer-friendly [API](https://io.github.hristogochev.vortex/navigation).

```kotlin
class HomeScreenModel : ScreenModel {
    // ...
}

data object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<HomeScreenModel>()
        // ...
    }
}


@Composable
fun App() {
    Navigator(HomeScreen)
}
```

Spin into seamless navigation with Vortex 🌪️

### Documentation

See the [project website](https://io.github.hristogochev/vortex) for documentation and APIs.

### Features

- [Supported platforms](https://io.github.hristogochev/vortex/setup#platform-compatibility): Android, iOS, Desktop, Web.
- [Linear navigation](https://io.github.hristogochev/vortex/navigation)
- [BottomSheet navigation](https://io.github.hristogochev/vortex/navigation/bottomsheet-navigation)
- [Tab navigation](https://io.github.hristogochev/vortex/navigation/tab-navigation)
  like [Youtube app](https://play.google.com/store/apps/details?id=com.google.android.youtube)
- [Nested navigation](https://io.github.hristogochev/vortex/navigation/nested-navigation) (multiple stacks, parent
  navigation)
- [ScreenModel](https://io.github.hristogochev/vortex/screenmodel) (a.k.a. ViewModel) integrated
  with [Koin](https://voyager.adriel.cafe/screenmodel/koin-integration), [Kodein](https://voyager.adriel.cafe/screenmodel/kodein-integration), [Coroutines](https://voyager.adriel.cafe/screenmodel/coroutines-integration)
- State-aware [Stack API](https://io.github.hristogochev/vortex/stack-api)
- Built-in [transitions](https://io.github.hristogochev/vortex/transitions)
- [State restoration](https://io.github.hristogochev/vortex/state-restoration) after Activity recreation
- [Lifecycle](https://io.github.hristogochev/vortex/lifecycle) callbacks
- [Back press](https://io.github.hristogochev/vortex/back-press) handling
- [Deep linking](https://io.github.hristogochev/vortex/deep-links) support

### Samples

| [Stack API](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/stateStack) | [ScreenModel](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/screenModel) | [Basic nav.](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/basicNavigation) |
|----------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| ![navigation-stack](https://user-images.githubusercontent.com/2512298/126323192-9b6349fe-7b96-4acf-b62e-c75165d909e1.gif)        | ![navigation-screenmodel](https://user-images.githubusercontent.com/2512298/131770829-fa85cb19-cc76-4fbf-9bdc-165997d5349d.gif)     | ![navigation-basic](https://user-images.githubusercontent.com/2512298/126323165-47760eec-2ba2-48ee-8e3a-841d50098d33.gif)              |

| [BottomSheet nav.](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/bottomSheetNavigation) | [Tab nav.](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/tabNavigation) | [Nested nav.](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/nestedNavigation) |
|----------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| ![navigation-bottom-sheet](https://user-images.githubusercontent.com/2512298/131191122-18025192-ce4d-4659-9afa-aacfdb488796.gif)                   | ![navigation-tab](https://user-images.githubusercontent.com/2512298/126323588-2f970953-0adb-47f8-b2fb-91c5854656bd.gif)            | ![navigation-nested](https://user-images.githubusercontent.com/2512298/126323027-a2633aef-9402-4df8-9384-45935d7986cf.gif)               |
