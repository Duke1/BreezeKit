package com.qfleng.sample.util

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.disposables.DisposableHelper
import io.reactivex.internal.functions.Functions
import io.reactivex.observers.LambdaConsumerIntrospection
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicReference


class RxError(detailMessage: String) : RuntimeException(detailMessage)

class RxObserver<T>(
    val next: (T) -> Unit,
    var onError: Consumer<in Throwable> = Consumer {
        it.printStackTrace()
    },
    var onComplete: Action = Functions.EMPTY_ACTION,
    var onSubscribe: Consumer<in Disposable> = Functions.emptyConsumer()
) : AtomicReference<Disposable>(), Observer<T>, Disposable, LambdaConsumerIntrospection {

    override fun onSubscribe(d: Disposable) {
        if (DisposableHelper.setOnce(this, d)) {
            try {
                onSubscribe.accept(this);
            } catch (ex: Throwable) {
                Exceptions.throwIfFatal(ex)
                d.dispose()
                onError(ex)
            }
        }
    }

    override fun onNext(t: T) {
        if (!isDisposed) {
            try {
                next(t)
            } catch (e: Throwable) {
                Exceptions.throwIfFatal(e)
                get().dispose()
                onError(e)
            }
        }
    }

    override fun onError(t: Throwable) {
        if (!isDisposed) {
            lazySet(DisposableHelper.DISPOSED)
            try {
                onError.accept(t)
            } catch (e: Throwable) {
                Exceptions.throwIfFatal(e)
                RxJavaPlugins.onError(CompositeException(t, e))
            }
        } else {
            RxJavaPlugins.onError(t)
        }
    }

    override fun onComplete() {
        if (!isDisposed) {
            lazySet(DisposableHelper.DISPOSED)
            try {
                onComplete.run()
            } catch (e: Throwable) {
                Exceptions.throwIfFatal(e)
                RxJavaPlugins.onError(e)
            }
        }
    }

    override fun dispose() {
        DisposableHelper.dispose(this)
    }

    override fun isDisposed(): Boolean {
        return get() == DisposableHelper.DISPOSED
    }

    override fun hasCustomOnError(): Boolean {
        return onError != Functions.ON_ERROR_MISSING
    }
}


class RxHelper {
    companion object {
        /**
         * @param scheduler Schedulers.io(), AndroidSchedulers.mainThread()
         */
        fun <R> doIt(
            func: () -> R,
            observer: RxObserver<R>,
            scheduler: Scheduler = AndroidSchedulers.mainThread()
        ) {
            Observable.just("")
                .map { func() }
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(observer)
        }

    }
}