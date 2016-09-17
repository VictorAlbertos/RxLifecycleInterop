# RxLifecycleInterop

RxLifecycleInterop is a **workaround** until [**RxLifecycle**](https://github.com/trello/RxLifecycle) releases a new version supporting RxJava2. This library is just a wrapper around RxLifecycle to convert RxJava 1.x to RxJava 2.x types, honoring as much as possible the original Api. Because it's just a wrapper, both dependencies -1.x and 2.x RxJava- are going to end up in your project.  

To perform the conversion between RxJava 1.x and 2.x reactive types [**RxJava2Interop**](https://github.com/akarnokd/RxJava2Interop) has been used. 


## SetUp
Add to top level *gradle.build* file

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Add to app module *gradle.build* file
```gradle
dependencies {
    compile 'com.github.VictorAlbertos:RxLifecycleInterop:0.0.1'
    compile 'io.reactivex.rxjava2:rxjava:2.0.0-RC2'
}
```

## Usage
RxLifecycleInterop only brings support for [rxlifecycle-android](https://github.com/trello/RxLifecycle/tree/master/rxlifecycle-android) and [rxlifecycle-components](https://github.com/trello/RxLifecycle/tree/master/rxlifecycle-components). The  [navi](https://github.com/trello/RxLifecycle/tree/master/rxlifecycle-navi) and [kotlin](https://github.com/trello/RxLifecycle/tree/master/rxlifecycle-kotlin) extensions are not covered.

### Api equivalences

| RxLifecycle  | RxLifecycleInterop |
| ------------- | ------------- |
| RxLifecycleAndroid  | Rx2LifecycleAndroid  |
| RxFragment  | Rx2Fragment  |
| RxDialogFragment  | Rx2DialogFragment  |
| RxActivity  | Rx2Activity  |
| LifecycleTransformer  | LifecycleTransformer2x  |
| support.RxAppCompatActivity  | support.Rx2AppCompatActivity  |
| support.RxAppCompatDialogFragment  | support.Rx2AppCompatDialogFragment  |
| support.RxDialogFragment  | support.Rx2DialogFragment  |
| support.RxFragment  | support.Rx2Fragment  |
| support.RxFragmentActivity  | support.Rx2FragmentActivity  |


For further instructions about how to use RxLifecycle, refer to [RxLifecycle's docs](https://github.com/trello/RxLifecycle). RxLifecycleInterop also provides an [Android app]() showcasing everyone of the api equivalences.