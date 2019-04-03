package cafe.adriel.pufferdb.rxjava

import cafe.adriel.pufferdb.core.Puffer
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

// Single functions

fun <T : Any> Puffer.getSingle(key: String, defaultValue: T? = null) =
    Single.fromCallable { get(key, defaultValue) }

fun Puffer.getKeysSingle() =
    Single.fromCallable { getKeys() }

fun Puffer.containsSingle(key: String) =
    Single.fromCallable { contains(key) }

// Observable functions

fun <T : Any> Puffer.getObservable(key: String, defaultValue: T? = null) =
    Observable.fromCallable { get(key, defaultValue) }

fun Puffer.getKeysObservable() =
    Observable.fromCallable { getKeys() }

fun Puffer.containsObservable(key: String) =
    Observable.fromCallable { contains(key) }

// Completable functions

fun <T : Any> Puffer.putCompletable(key: String, value: T) =
    Completable.fromAction { put(key, value) }

fun Puffer.removeCompletable(key: String) =
    Completable.fromAction { remove(key) }

fun Puffer.removeAllCompletable() =
    Completable.fromAction { removeAll() }
