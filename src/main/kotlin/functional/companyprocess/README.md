# 어떤 로직인가?
이름들을 captialize("mike" -> "Mike")하고 이름 사이에 ", "를 붙여 연결하여 하나의 스트링으로 만든다.

# 함수형으로 짠 로직은 어떻게 동작하는가?
이름 배열에는 ["breadkey, "dahunlim", "a", "nupdoong"]이 들어가 있다.

현재 배열 : ["breadkey", "dahunlim", "a", "nupdoong"]
## 1. filter
```
arrayOfNames.filter { it.length > 1 } 
```
말 그대로 컬렉션에서 조건에 맞지 않는 요소들은 걸러 새로운 컬렉션을 만든다. 조건이 it(코틀린에서 람다 식의 인자).length > 1 이므로 "a" 요소가 걸러진다.  

findAll이라고 부르는 곳도 있다.

현재 배열 : ["breadkey", "dahunlim", "nupdoong"]

## 2. map
```
arrayOfNames.map { it.capitalize() }
```
현재 컬렉션의 요소들에 같은 함수를 적용하여 새로운 컬렉션을 만든다. capitalize 함수를 적용한 새로운 컬렉션이 생성된다.


현재 배열 : ["Breadkey", "Dahunlim", "Nupdoong"]

## 3. reduce
```
arrayOfNames.reduce { acculmulator, string -> "$acculmulator, $string" }
```
캐터모피즘이라는 컬렉션 조작 개념의 특별한 변형이다. fold 함수와 기능이 중복되기도 하지만 차이가 있다. reduce는 누산기에 초기값을 넣은 채로 연산을 시작하지만 fold는 그렇지 않다.

가장 먼저 누산기<sup>accumulator</sup>에 초기값으로 첫번 째 요소인 "Breadkey"가 들어가 있다.

### 초기 상태
누산기 : "BreadKey"  
배열 : ["Dahunlim", "Nupdoong"]

이후 연산을 진행하는데, 누산기 안에 새로운 요소가 들어갈 때 연산으로 "$accumulator, $string" 즉 accumulator + ", " + string를 진행한다.
### 첫번 째 연산
누산기 : "Breadkey, Dahunlim"  
배열 : ["Nupdoong"]
### 마지막 연산
누산기 : "Breadkey, Dahunlim, Nupdoong"
배열 : 

# 결론
이와 비슷하게 컬렉션을 조작하는 로직에는 함수형으로 짰을 때 훨씬 **짧고** **직관적**이다.

얼마나 직관적이냐면 코드가 사람의 사고와 굉장히 비슷하다.
1. 이름목록에서 이름만 걸러낸다
: filter { it == 이름 }
2. 이름들을 captialize 한다
: map { it.capitalize() }
3. 이름들을 ", "로 연결한다
: reduce { accumulator, string -> accumulator + ", " + string }
