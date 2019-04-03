[![JitPack](https://img.shields.io/jitpack/v/github/adrielcafe/pufferdb.svg?style=flat-square)](https://jitpack.io/#adrielcafe/pufferdb) 
[![Android API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat-square)](https://android-arsenal.com/api?level=14) 
[![License MIT](https://img.shields.io/github/license/adrielcafe/pufferdb.svg?style=flat-square)](https://opensource.org/licenses/MIT) 
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=flat-square)](https://ktlint.github.io/) 

<h1>
	<img src="https://github.com/adrielcafe/PufferDB/blob/master/logo.svg?raw=true" height="100"> PufferDB
</h1>

**PufferDB** is a :zap: key-value database powered by **P**rotocol B**uffer**s (aka [Protobuf](https://developers.google.com/protocol-buffers/)).

Protocol Buffers are a language-neutral, platform-neutral extensible mechanism for serializing structured data. Compared to JSON, Protobuf files are [smaller and faster](https://auth0.com/blog/beating-json-performance-with-protobuf/) to read/write because it stores data in a [efficient binary format](https://developers.google.com/protocol-buffers/docs/encoding).

## Features
* [Thread-safe](https://github.com/adrielcafe/PufferDB/blob/master/core/src/main/kotlin/cafe/adriel/pufferdb/core/PufferDB.kt#L23)
* Fast (benchmark coming soon™)
* Works on [Android](#) and [JVM](#)
* Wrappers for [Coroutines](#) and [RxJava](#)
* [Simple API](https://github.com/adrielcafe/PufferDB/blob/master/core/src/main/kotlin/cafe/adriel/pufferdb/core/Puffer.kt)

### Supported types
So far, PufferDB supports the following types:
- [x] Double
- [x] Float
- [x] Int
- [x] Long
- [x] Boolean
- [x] String
- [ ] Serializable
- [ ] Parcelable

## Getting Started

### Import to your project
1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        ...

        maven { url 'https://jitpack.io' }
    }
}
```

2. Next, add the desired dependencies to your module:

```gradle
dependencies {
    ...

    // Core
    implementation "com.github.adrielcafe.pufferdb:core:$currentVersion"

    // Android helper
    implementation "com.github.adrielcafe.pufferdb:android:$currentVersion"

    // Coroutines wrapper
    implementation "com.github.adrielcafe.pufferdb:coroutines:$currentVersion"

    // RxJava wrapper
    implementation "com.github.adrielcafe.pufferdb:rxjava:$currentVersion"
}
```

#### Current version: [![JitPack](https://img.shields.io/jitpack/v/github/adrielcafe/pufferdb.svg?style=flat-square)](https://jitpack.io/#adrielcafe/pufferdb)

### Core (TODO)


### Android
The Android module contains a `AndroidPufferDB` helper class:
```kotlin
// Returns a default Puffer instance
val puffer = AndroidPufferDB.withDefault(context)

// Returns a File on Context.filesDir that should be used by Puffer
val pufferFile = AndroidPufferDB.getPufferFile(context, "my.db")
```

### Coroutines
The Coroutines module contains a `CoroutinePufferDB` wrapper class and some useful extension functions:
```kotlin
val pufferFile = File("path/to/puffer/file")
val puffer = CoroutinePufferDB.with(pufferFile)

puffer.apply {
    // All methods are suspend functions that runs on Dispachers.IO context
    launch {
        val myValue = get<String>("myKey")
        val myValueWithDefault = get("myKey", "defaultValue")
        
        put("myOtherKey", 123)

        getKeys().forEach { key ->
            // ...
        }

        if(contains("myKey")){
            // ...
        }

        remove("myOtherKey")

        removeAll()
    }
}
```

If you don't want to use this wrapper class, there's some built in extension functions that can be used with the Core module:
```kotlin
val pufferFile = File("path/to/puffer/file")
val puffer = PufferDB.with(pufferFile) // <- Note that we're using the Core PufferDB

puffer.apply {
    launch {
        val myValue = getSuspend<String>("myKey")

        val myValue = getAsync<String>("myKey").await()
        
        putSuspend("myOtherKey", 123)

        putAsync("myOtherKey", 123).await()
    }
}
```

### RxJava
The RxJava module contains a `RxPufferDB` wrapper class and some useful extension functions:
```kotlin
val pufferFile = File("path/to/puffer/file")
val puffer = RxPufferDB.with(pufferFile)

puffer.apply {
    // Some methods returns Single<T>...
    get<String>("myKey") // OR get("myKey", "defaultValue")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { myValue ->
            // ...
        }

    // ... And others returns Completable
    put("myOtherKey", 123)
        // ...
        .subscribe {
            // ...
        }

    getKeys()
        // ...
        .subscribe { keys ->
            // ...
        }

    contains("myKey")
        // ...
        .subscribe { contains ->
            // ...
        }

    remove("myOtherKey")
        // ...
        .subscribe {
            // ...
        }

    removeAll()
        // ...
        .subscribe {
            // ...
        }
}
```

Similar to the Coroutines module, the RxJava module also provides some useful built in extension functions that can be used with the Core module:
```kotlin
val pufferFile = File("path/to/puffer/file")
val puffer = PufferDB.with(pufferFile) // <- Note that we're using the Core PufferDB

puffer.apply {
    val myValue = getSingle<String>("myKey").blockingGet()

    putCompletable("myOtherKey", 123).blockingAwait()

    getKeysObservable().blockingSubscribe { keys ->
        // ...
    }
}
```
