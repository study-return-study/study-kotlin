package rxjava.chapter2

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher
import java.util.concurrent.Executors
import kotlin.math.sin
import kotlin.reflect.KFunction

fun main() {
    fun just() {
        val disposable = Observable.just("RED", "GREEN", "BLUE")
            .commonSubscribe(::just.name)

        // 발행이 모두 완료되면 dispose() 발행을 다른 쓰레드에서 하면 출력 결과는 어떻게?
        println("${disposable.isDisposed}")
    }

    fun create() {
        /*
         * dispose되었을 때 등록된 콜백을 모두 해제해야 한다.
         * 구독하는 동안에만 onNext와 onComplete를 호출해야 한다
         * 에러가 발생했을 때 오직 onError로만 에러를 전달해야 한다
         * 배압을 직접 처리해야 한다
         */
        Observable.create<Int> { emitter ->
            if (!emitter.isDisposed) {
                emitter.onNext(100)
                emitter.onNext(200)
                emitter.onNext(300)
                emitter.onComplete()
            }
        }.commonSubscribe(::create.name)
    }

    fun fromArray() {
        val array = arrayOf(100, 200, 300)

        // spread 연산자를 붙여주지 않으면 array 자체를 발행하게 됨
        Observable.fromArray(*array)
            .commonSubscribe(::fromArray.name)
    }

    fun fromIterable() {
        Observable.fromIterable(listOf("Jerry", "William", "Bob"))
            .commonSubscribe(::fromIterable.name)
    }

    fun fromCallable() {
        /*
         * 결과를 계산하고, 할 수 없는 경우 예외를 던짐
         */
        val callable = { "Hello Callable" }

        Observable.fromCallable(callable).commonSubscribe(::fromCallable.name)
    }

    fun fromFuture() {
        val future = Executors.newSingleThreadExecutor().submit(fun(): String { return "Hello Future" })
        Observable.fromFuture(future).commonSubscribe(::fromFuture.name)
    }

    fun fromPublisher() {
        val publisher = Publisher<String> {
            it.onNext("Hello Publisher")
            it.onComplete()
        }

        Observable.fromPublisher(publisher).commonSubscribe(::fromPublisher.name)
    }

    just()
    create()
    fromArray()
    fromIterable()
    fromCallable()
    fromFuture()
    fromPublisher()
}

fun <T> Observable<T>.commonSubscribe(functionName: String): Disposable = subscribe({
    println("$functionName $it")
}, {}, { println("$functionName onComplete\n") })