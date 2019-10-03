package rxjava.chapter2

import io.reactivex.Maybe
import io.reactivex.disposables.Disposable

/*
 * 0(onComplete) or 1(onSuccess) or x(onError)
 */
fun main() {
    fun maybe() {
        Maybe.just("Hello Maybe")
            .commonSubscribe()

        Maybe.empty<String>()
            .commonSubscribe()
    }

    maybe()
}

fun <T> Maybe<T>.commonSubscribe(): Disposable =
    subscribe({ println(it) }, {}, { println("onComplete") })