// '데이터' 클래스
data class Person (
    // 프로퍼티
    val name: String,
    val age: Int? = null
)

// 최상위 함수
fun main (args: Array<String>) {
    val persons = listOf(
        Person("영희"),
        Person("철수",
            // 이름이 붙은 파라미터
            age = 29)
    )

    val oldest = persons.maxBy {
        // 람다 식과 "엘비스" 연산자
        it.age ?: 0
    }

    // 스트링 템플릿
    println("나이가 가장 많은사람: $oldest")
}