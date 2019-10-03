package rxjava.chapter2

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.*

/*
 * Cold Observable
 * * 아무도 구독하지 않으면 데이터 발행하지 않음
 * * 구독하면 준비된 데이터를 처음부터 발행
 * * 웹 요청, 데이터베이스 쿼리와 파일 읽기 등
 * Hot Observable
 * * 구독자의 존재 여부와 관계없이 데이터를 발행
 * * 구독하면 구독한 시점부터 데이터 발행
 * * 마우스 이벤트, 키보드 이벤트, 시스템 이벤트, 센서 데이터와 주식 가격 등
 * * 배압(back pressure)을 고려해야 함
 */
fun main() {
    fun asyncSubject() {
        /*
         * Observable에서 발행한 마지막 데이터를 얻어올 수 있는 클래스
         */
        var subject: AsyncSubject<*>

        subject = AsyncSubject.create<String>()
        /*
         * 두 구독자 모두 마지막에 발행된 5를 발급받는다
         */
        with(subject) {
            commonSubscribe()
            onNext("1")
            onNext("3")
            commonSubscribe()
            onNext("5")
            onComplete()
        }

        /*
         * AsyncSubject 클래스는 구독자로도 동작할 수 있다
         */
        val temperature = arrayOf(10.1f, 13.4f, 12.5f)
        val source = Observable.fromArray(*temperature)

        subject = AsyncSubject.create<Float>()
        subject.commonSubscribe()

        source.subscribe(subject)

        /*
         * Observable과 마찬가지로 onComplete 이후의 onNext는 모두 무시됨
         */
        subject = AsyncSubject.create<Int>()
        with(subject) {
            onNext(10)
            onNext(11)
            commonSubscribe()
            onNext(12)
            onComplete()
            onNext(13)
            commonSubscribe()
            commonSubscribe()
        }
    }

    fun behaviorSubject() {
        /*
         * 구독을 하면 가장 최근 값 혹은 기본값을 넘겨주는 클래스
        */
        val subject = BehaviorSubject.createDefault(6)
        with(subject) {
            commonSubscribe()
            onNext(1)
            onNext(3)
            commonSubscribe()
            onNext(5)
            onComplete()
        }
    }

    fun publishSubject() {
        /*
         * 가장 기본적인 Subject
         * 오직 해당 시간에 발행한 데이터를 그대로 전달
         */
        val subject = PublishSubject.create<String>()
        with(subject) {
            commonSubscribe()
            onNext("1")
            onNext("3")
            commonSubscribe()
            onNext("5")
            onComplete()
        }
    }

    fun replaySubject() {
        /*
         * Cold Observable처럼 동작
         * 구독자가 생기면 데이터의 처음부터 끝까지 발행
         * 모든 데이터를 저장해두므로 가장 메모리 누수에 주의
         */

        val subject = ReplaySubject.create<Int>()
        with(subject) {
            commonSubscribe()
            onNext(1)
            onNext(3)
            commonSubscribe()
            onNext(5)
            onComplete()
        }
    }

    asyncSubject()
    behaviorSubject()
    publishSubject()
    replaySubject()
}

val subscriberCount = hashMapOf<Subject<*>, Int>()
inline fun <reified T> Subject<T>.commonSubscribe(): Disposable {
    (subscriberCount[this] ?: 0).also { int ->
        subscriberCount[this] = int + 1
    }
    val count = subscriberCount[this]

    return subscribe {
        println("Subscriber of ${this::class.java.simpleName}<${T::class.java.simpleName}> #$count -> $it")
    }
}