import android.os.Build.VERSION_CODES.P

//최상위 영역
//java : int num = 1;
//코틀린 : val(or var) 변수명 : 타입 = 값
val num : Int = 1


class Test {
}

class User3(name:String){//선언부
    //본문
    //주생성자 생략 -> 디폴트 생성자 만들어줌
    //보조 생성자를 이용
    //var name : String
    //var count : Int

    //문제점 -> 주생성자와, 보조 생성자가 같이 있을경우
    //해결책은 -> 보조 생성자, 주생성자로 연결하눈 부분이 필요
    //this
    //결론, 보조생성자 이용하면 되지만, 주 생성자를 이용해라는 의미,
    //작업 할 때에도, 주 생성자에서 작업을 더 많이 하는 편
    constructor(name: String, count: Int) : this(name){

    }

}

class User2(val name: String, count:Int){
    //주생성자의 지역변수 name
    //주생성자 class 이름 옆 선언
    //constructor 생략을 많이 하고 ()방식으로 사용

    //클래스의 멤버 변수 name
    //var name = "lsy"

    init {
        // init 함수 안에서 주 생성자의 매개변수를 사용가능
        //하지만, class 맴버 변수로 사용이 불가능
        println("init  호출. 주생성자 매개변수 사용 : $name")
    }

    fun someFun(){
        println("init  호출. 주생성자 매개변수 사용 : $name")
    }

}

class User{//주 생성자 생략, 보조생성자 사용
    var name = "kow"
    //생성자 모양이, 자바에서는 클래스명과 동일
    //코틀린은 생성자 키워드가 따로 존재
    //보조 생성자
    constructor(name : String){
        this.name = name
    }
    fun someFun(){
        println("someFun()의 name : $name")
    }
}

//코틀린 기본 상속이 안된다
//필요한 키워드 : open
//부모 사용시 반드시 초기화를 해야 사용가능하기에
//super() 주생성자 호출 해야합니다.
open class Super(){
    open var superData = 10
    //접근 지정자 확인, protected만 확인
    //결론, 자식 클래스에서만, 부모클래스 멤버 접근 가능 여부 확인
    //main 함수에서는 접근 불가
    protected var protectedData = 20
    
    open fun superFun(){
        println("superFun 호출됨")
    }

}

class Sub : Super(){
    //부모의 멤버 재정의해서 사용하려고 하면 안됨
    //키워드, open, ovaerride가 없어서
    override var superData = 20
    
    override fun superFun() {
        //자식클래스 접근 가능
        protectedData++
        println("자식에서 부모 함수 재정의")
    }
}

//비 data 클래스
class NonData(val name: String, val pw: String){

}
//data 클래스 -> 실제 값을 비교 해주는 변수는 주 생성자의 변수만 해줌
data class Data(val name: String, val pw: String){
    lateinit var email:String
    constructor(name:String, pw: String, email: String):this(name,pw){
        this.email = email
    }
}

open class Super2{
    open var data = 10
    open fun some(){
        println("i am Super2: $data")
    }
}

val obj = object:Super2() { //타입 지정을 안하면 Any 기본값
    //사용 못함 , 반드시 타입 지정
    override var data = 20
    override fun some() {
        println("i am Super2 재사용 값: $data")
    }
}

class companionClassTest{
    //자바 static 키워드와 동일한 기능
    //멤버에 접근시, 클래스명에 점을 찍고 접근함
    companion object{
        var data = 10
        fun some(){
            println("data 의 값 : $data")
        }
    }
}

fun main(){

    val some2 = {no1:Int, no2:Int -> println("출력")
        no1 * no2
    }
    println("익명함수 출력 확인 : ${some2(1,2)}")

    //매개변수가 1하나인 람다식(익명함수), it로 대체하기
    //자동으로 컴파일러가, 자바 문법 -> 코틀린 문법으로 대체할 때
    //자동 변환시, 매개변수가 1개인 경우 it로 바로 대체함
    //request, 응답 객체 response 응답 객체 하나만 가리킬 때,
    //이런 경우에도 it을 많이 사용함
    val result14:(Int)->Unit = {println(it)}
    val y = result14(100)

    //함수 타입 변수에서 데이터 타입이 있듯이,
    //함수 타입 있음 익명 클래스 만들 때, object
    //클래스 선언부 뒤에 생략(Any)
    //함수도 결괏괎의 타입을 생략한다면 -> Unit(void)
    //익명 함수이지만, 이것의 타입을 명시.

    //일반함수
    fun some(no1:Int, no2:Int):Int{
        return no1 + no2
    }

    val result13 = {no1:Int, no2:Int -> no1 + no2}
    val x = result13(1,2)
    println("x의 값 : $x")


    //companion 클래스 사용 예제
    companionClassTest.data
    companionClassTest.some()

    //object 익명 클래스 사용 예제
    obj.data = 30
    obj.some()

    //data 클래스 실제 값 비교
    val data13 = Data("lsy", "1234", "email1")
    val data14 = Data("lsy", "1234", "email2")
    println("data13 의 주소값 : $data13")
    println("data14 의 주소값 : $data14")
    println("equals 이용한 값비교 : ${data13.equals(data14)}")

    val none1 = NonData("lsy", "1234")
    val none2 = NonData("lsy", "1234")
    println("none1 의 주소값 : $none1")
    println("none1 의 주소값 : $none2")
    println("equals 이용한 값비교 : ${none1.equals(none2)}")

    val obj = Sub()
    println("obj.superData의 값: " + obj.superData)
    obj.superFun()
    //접근 불가
    //obj.protectedData

    val user2 = User2("lsy", 10)
    println("user2사용해보기 : " + user2)
    
    //객체 생성, 인스턴스 생성
    //자바 : User user = new User("kow");
    val user = User("이상용")
    println("user의 name : " + user.name)
    user.someFun()

    val data12 = arrayOf<Int>(1, 2, 3)
    for((index, value) in data12.indices.withIndex()){
        print("인덱스의 값: ")
        print(index)
        print("value의 값: ")
        print(value)
        if(index !== data12.size -1) print(",")
    }
    println()

    fun sum10():Int {
        val result = 0
        // in 1..10, 1 until 10, 1..10 step 2
        for(i in 10 downTo 1){
            val sum = 0
            val result = sum + i
            println("반복문의 result의 값은 : $result")
        }
        return result
    }
    sum10()

    //반복문 활용해보기
    val data11 = arrayOf<Int>(1, 2, 3)
    for(i in data11.indices){
        print(data11[i])
        if(i !== data11.size -1) print(",")
    }
    println()
    var data10 = 5
    var result10 = when{
        //is 해당 타입이 맞는지 확인
        data10 < 10 -> ("data10 < 10")
        else -> {
            "data10 의 값은 null"
        }
    }
    println("data10 조건으로 result10 출력하기 : $result10")

    var data9 : Any = 5
    when(data9){
        //is 해당 타입이 맞는지 확인
        is String -> println("data9의 값은 문자열 : ${data9}")
        in 1..10 -> println("data9의 값은 숫자 : ${data9}")
        else -> {
            println("data9 의 값은 null")
        }
    }

    var data8 = "abcd"
    when(data8){
        "10" -> println("data8 의 값은 10")
        "abc" -> println("data8 의 값은 abc")
        else -> {
            println("data8 의 값은 null")
        }
    }

    var data7 = 10
    when(data7){
        10 -> println("data7 의 값은 10")
        20 -> println("data7 의 값은 20")
        else -> {
            println("data7 의 값은 null")
        }
    }

    var data5 = 10
    var data6 = if(data5>0){
        println("표현식 확인")
        30
    } else{
        50
    }
    println("data6 : ${data6}")

    val map = mapOf<String, String>(Pair("one", "hello"), "two" to "2")
    println("""
        map size : ${map.size}
        map data : ${map.get("one")}, ${map.get("two")}
    """.trimIndent())

    //가변리스트 확인
    val mL = mutableListOf<Int>(1, 2, 3)
    mL.add(3, 100)
    println("""
        mL size : ${mL.size}
        mL data 인덱스 3 : ${mL[3]}
    """.trimIndent())
    var list = listOf<Int>(1,2,3)
    //불변 리스트라 변경 불가
    //list[0] = 100
    println("""
        list size : ${list.size}
        list data : ${list[0]}, ${list.get(1)}
    """.trimIndent())

    val data4 = intArrayOf(1,2,3)
    val data3 = arrayOf<Int>(10, 20, 30)

    val data2 : IntArray = IntArray(3, { 0 })
    data2[0] = 100
    println("data2의 값 조회 : ${data2[0]}")

    //배열 -> 자바(코틀린) : 동일한 데이터 타입의 값들을 할당함
    //비교 vs 자바스크립트 : 여러 가지의 데이터 타입의 값들을 할당함
    //Array(배열의 갯수, 초깃값)
    //람다식 문법 : { 매개변수 -> 실행 문장}
    //람다식에서 매개변수가 1개면 화살표, 매개변수를 생략
    // : {실행될 문장}
    val data1 : Array<Int> = Array(3,{0})
    println("data1의 값 조회 : ${data1[0]}")

    //함수의 매개변숭서는 var, val 키워드 사용금지
    //자동으로 val가 들어가 있다.
    fun sum3(no:Int, no2:Int){
        val result = no + no2
        println("no + no2 = $result")
    }

    //함수의 결괏값의 타입을 Nothing
    fun some1():Nothing? { // null 허용이 가능한 연산자
        return null
    }

    val num3 : Any = "이상용"
    fun sum2(no:Int, no2:Int){
        val result = no + no2
        println("no + no2 = $result")
    }

    sum2(10, 20)

    fun sum(no: Int) : Int{
        //타입이 추론 되면, 생략가능
        var sum = 0
        for(i in 1..no){
            sum += i
        }
        return sum
    }

    val result = sum(10)

    println("hi android")
    println("num의 값 : $num")
    println("result 값 : $result")
}