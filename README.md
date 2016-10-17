# Flux Unit Test Sample Project #


This project contains a template which can be used to start an Android project using Flux architecture integrating Unit and E2E tests. We can find more information about Flux on the next link: [https://github.com/lgvalle/android-flux-todo-app](Link URL).

Basically we have included a `MainActivity` activity with a Navigation Drawer, that was generated on the creation wizard in the Android Studio. There is also a small Flux routine that shows how the architecture works. Unit and E2E tests were created to show how to test Flux code.


### Source packages: ###

  `action` Contains actions creator and emiters

  `dagger` Contains Dagger graphs declaration for dependency injection

  `dispatcher` Contains the Dispatcher that dispatchs actions to stores

  `event` Contains all events to send through the EventBus

  `store` Contains the app state

  `view` Contains the app UI 



### Third party libraries: ###

  `Butterknife` [http://jakewharton.github.io/butterknife/](Link URL)

  `Eventbus` [https://github.com/greenrobot/EventBus](Link URL)

  `RxJava` [https://github.com/ReactiveX/RxJava](Link URL)

  `RxAndroid` [https://github.com/ReactiveX/RxJava/wiki/The-RxJava-Android-Module](Link URL)

  `Retrofit` [https://github.com/square/retrofit](Link URL)

  `OkHttp3` [http://square.github.io/okhttp/](Link URL)

  `Timber` [https://github.com/JakeWharton/timber](Link URL)

  `Dagger` [http://google.github.io/dagger/](Link URL)

  `JUnit` [http://junit.org/junit4/](Link URL)

  `Mockito` [http://mockito.org/](Link URL)

  `Powermock` [https://github.com/jayway/powermock](Link URL)

  `Espresso` [https://developer.android.com/training/testing/ui-testing/espresso-testing.html](Link URL)

  `Hamcrest` [http://hamcrest.org/JavaHamcrest/](Link URL)



### Gradle tasks to be executed in Unix command line mainly for continuous integration purposes: ###

`./gradlew clean` clean project

`./gradlew assembleDebug` Generate debug APK

`./gradlew lintDebug` Execute Lint

`./gradlew testDebugUnitTestCoverage` Execute JaCoCo code coverage

`./gradlew findbugsDebug` Execute Findbugs

`./gradlew testDebugUnitTest` Execute Unit Tests set

`./gradlew connectedCheck` Execute E2E Tests set

`./gradlew assembleRelease` Generate release APK



### References ###


JaCoCo was applied to code coverage analysis, it was configured in file: `FluxTest/app/jacoco.gradle`, for more click next link: 

[https://blog.gouline.net/code-coverage-on-android-with-jacoco-92ec90c9355e#.b8hep49e6](Link URL)


Lint was applied to static code analysis, it was configured in file: `FluxTest/app/lint.xml`, for more click next link: 

[http://tools.android.com/tips/lint/suppressing-lint-warnings](Link URL) 

[http://www.kiodev.com/android-studio-lint-options/](Link URL) 

[https://android.googlesource.com/platform/tools/base/+/e6a5b9c7c1bca4da402de442315b5ff1ada819c7](Link URL)


Findbugs was applied to static code analysis, it was configured in files: `FluxTest/app/findbugs.gradle` y `FluxTest/app/findbugs.xml`, for more click next link: 

[https://androidbycode.wordpress.com/2015/02/13/static-code-analysis-automation-using-findbugs-android-studio/](Link URL)