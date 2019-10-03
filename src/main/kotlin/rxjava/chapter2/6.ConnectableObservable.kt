package rxjava.chapter2

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.observables.ConnectableObservable
import java.util.concurrent.TimeUnit

/*
 * Cold Observable을 Hot Observable로 변환
 * subscribe()를 호출해도 아무일도 일어나지 않음
 * connect()를 호출한 시점부터 구독자에게 데이터를 발행
 */
fun main() {
    val data = arrayOf("1", "3", "5")
    val balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
        .map { data[it.toInt()] }
        .take(data.size.toLong())

    val source = balls.publish()
    source.commonSubscribe()
    source.commonSubscribe()
    source.connect()

    Thread.sleep(250)
    source.commonSubscribe()
    Thread.sleep(100)
}

var subscriberNumber = 0
fun <T> ConnectableObservable<T>.commonSubscribe(): Disposable {
    val number = ++subscriberNumber
    return subscribe {
        println("Subscriber #$number => $it")
    }
}