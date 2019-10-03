package rxjava.chapter2

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

/*
 * 오직 1개의 데이터만 발행 가능
 * 결과가 유일한 서버 API 호출 시 유용
 * onComplete + onNext = onSuccess
 */
fun main() {
    fun just() {
        Single.just("Hello Single").commonSubscribe(::just.name)
    }

    fun fromObservable() {
        val source = Observable.just("Hello Single")
        Single.fromObservable(source).commonSubscribe(::fromObservable.name + "1")

        Observable.just("Hello Single")
            .single("default item")
            .commonSubscribe(::fromObservable.name + " just")

        Observable.fromArray("Red", "Blue", "Gold")
            .first("default value")
            .commonSubscribe(::fromObservable.name + " fromArray")

        Observable.empty<String>()
            .single("default value")
            .commonSubscribe(::fromObservable.name + " empty")

        Observable.just("ORD-1", "ORD-2")
            .take(1)
            .single("default order")
            .commonSubscribe(::fromObservable.name + " take")
    }

    fun error() {
        Observable.just("item1", "item2")
            .single("default item")
            .commonSubscribe(::error.name)
    }

    just()
    fromObservable()
    error()
}

fun <T> Single<T>.commonSubscribe(functionName: String): Disposable = subscribe({
    println("$functionName $it")
}, {println(it)})