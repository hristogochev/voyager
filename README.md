[![Maven metadata URL](https://img.shields.io/maven-metadata/v?color=blue&metadataUrl=https://s01.oss.sonatype.org/service/local/repo_groups/public/content/cafe/adriel/voyager/voyager-core/maven-metadata.xml&style=for-the-badge)](https://repo.maven.apache.org/maven2/cafe/adriel/voyager/)
[![Android API](https://img.shields.io/badge/api-21%2B-brightgreen.svg?style=for-the-badge)](https://android-arsenal.com/api?level=21)
[![kotlin](https://img.shields.io/github/languages/top/hristogochev/vortex.svg?style=for-the-badge&color=blueviolet)](https://kotlinlang.org/)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=for-the-badge)](https://ktlint.github.io/)
[![License MIT](https://img.shields.io/github/license/hristogochev/vortex.svg?style=for-the-badge&color=orange)](LICENSE.md)

<h1 align="center">
    <br>
    <img height="150" src="docs/img/logo_vortex.png"/>
    <br>
    <a href="https://io.github.hristogochev/vortex">Vortex</a>: Spin up your Compose Navigation
</h1>

Stability-focused Compose Multiplatform Navigation Library, fork of [Voyager](https://github.com/adrielcafe/voyager).

Vortex maintains nearly the same [API](https://hristogochev.github.io/vortex) as Voyager, ensuring seamless integration:

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

### Documentation
See the [project website](https://hristogochev.github.io/vortex) for documentation and APIs.

### **Features**
- [Supported platforms](setup.md): Android, iOS, Desktop, Wasm, JS
* [Basic navigation](navigation/index.md)
* [Nested navigation](navigation/nested-navigation.md)
* [Bottom sheet navigation](navigation/bottom-sheet-navigation.md)
* [Advanced navigation](navigation/advanced-navigation.md)
* [Tab navigation](navigation/tab-navigation.md) (like bottom navigation)
* [ScreenModel](screenmodel/index.md) (Multiplatform ViewModel)
* [Transitions](transitions.md) per screen
* [State restoration](state-restoration.md) after Activity recreation
* [Callbacks](lifecycle.md) for screen creation and disposal
* [Deep linking](deep-links.md) support
* State-aware [Stack API](stack-api.md)

### Differences with Voyager

##### Navigation
* **Navigator-Screen Linking**:<br>Navigators are now directly linked to their parent screen instead of their parent navigator.
* **Automatic Disposal**:<br>When a screen is disposed, all navigators within it are automatically disposed.
* **Flexible Bottom Sheet Navigation**:<br>Bottom sheet navigation can now be used in any bottom sheet.
* **Simplified Bottom Sheet Navigators**:<br> Bottom sheet navigators have been streamlined into simple navigators with a `disposeOnForgotten` flag.

##### Transitions
* **Navigator Default Transitions**:<br>You can specify default navigator overridable screen appear and disappear transitions.
* **Runtime Appear Transition**:<br>You can specify the appear transition for a screen at runtime.
* **Runtime Disappear Transition**:<br>You can specify the disappear transition for a screen at runtime.

##### Lifecycle
* **Screen Disposable Effect**:<br>A new screen disposable effect is available, tied to the lifecycle of the screen.
* **Reliable Application Lifecycle Owner**:<br>You can now reliably access the application's multiplatform lifecycle owner without risk of failure to update.

##### Missing features
* **Android specific state management**:<br>No support for Android-specific APIs, such as LiveData, Hilt, and RxJava.
* **No screen-specific lifecycle owners**:<br>Screens no longer override the default LocalLifecycleOwner.


### Acknowledgments

* Big thanks to [Adriel Café](https://adriel.cafe/) and all contributors of the original [Voyager](https://github.com/adrielcafe/voyager) library.

### Samples

| [Stack API](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/stateStack) | [ScreenModel](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/screenModel) | [Basic nav.](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/basicNavigation) |
|----------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| ![navigation-stack](https://user-images.githubusercontent.com/2512298/126323192-9b6349fe-7b96-4acf-b62e-c75165d909e1.gif)        | ![navigation-screenmodel](https://user-images.githubusercontent.com/2512298/131770829-fa85cb19-cc76-4fbf-9bdc-165997d5349d.gif)     | ![navigation-basic](https://user-images.githubusercontent.com/2512298/126323165-47760eec-2ba2-48ee-8e3a-841d50098d33.gif)              |

| [BottomSheet nav.](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/bottomSheetNavigation) | [Tab nav.](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/tabNavigation) | [Nested nav.](https://github.com/adrielcafe/voyager/tree/main/samples/android/src/main/java/cafe/adriel/voyager/sample/nestedNavigation) |
|----------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| ![navigation-bottom-sheet](https://user-images.githubusercontent.com/2512298/131191122-18025192-ce4d-4659-9afa-aacfdb488796.gif)                   | ![navigation-tab](https://user-images.githubusercontent.com/2512298/126323588-2f970953-0adb-47f8-b2fb-91c5854656bd.gif)            | ![navigation-nested](https://user-images.githubusercontent.com/2512298/126323027-a2633aef-9402-4df8-9384-45935d7986cf.gif)               |
