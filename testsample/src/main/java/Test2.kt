class Test2 {
}

//고차함수 사용예제,
//고차함수는 : 매개변수 또는 결과 값 자리에 함수가 들어가는 형태
fun testH(arg:(Int)->Boolean):()->String{
    val result = if(arg(10)){
        "valid"
    } else {
        "invalid"
    }
    return {"testH result 확인 : $result"}
}

fun main(){
    //? null 허용 연산자 및, ?.null 허용변수 호출 : 접근시 반드시 ?. 접근
    //?: 앨비스 연산자 : null 이 아니면 아닌값이 호출되고
    //null이면, 지정한 기본값이 할당
    val data20:String? = "lsy"
    println("data20 의 길이 : ${data20?.length ?: 0}")

    //고차함수 사용예제
    val result16 = testH({no -> no > 0})
    println(result16())
}