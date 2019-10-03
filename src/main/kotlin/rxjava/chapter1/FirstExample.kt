package rxjava.chapter1

import io.reactivex.Observable

fun main() {
    fun emit() {
        // just로 Hello와 RxJava 2!!를 발행
        Observable.just("Hello", "RxJava 2!!")
            // Observable을 구독. Observable은 구독자가 없으면 데이터를 발행하지 않음
            .subscribe {
                println(it)
            }
    }

    emit()
}