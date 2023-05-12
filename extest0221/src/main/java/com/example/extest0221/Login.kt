package com.example.extest0221

import java.util.Scanner

data class User(val id:String, val pw:String, val email:String, val phone:String){
}

class Login(){
   fun check(Id:String, pw:String): Boolean{
       if(Id.equals("admin")&&pw.equals("1234")){
           return true
       }
           return false
   }
}


class Register(){

}

fun main(){

    val sc:Scanner = Scanner(System.`in`)

    println("아이디 입력")
    val id = sc.nextLine()
    println("비밀번호 입력")
    val pw = sc.nextLine()
    println("이메일 입력")
    val email = sc.nextLine()
    println("번호 입력")
    val phone = sc.nextLine()
    val user = User(id, pw, email, phone)
    val login = Login()

    if(login.check(id, pw)){
        println("로그인 성공")
    } else {
        println("로그인 실패")
    }



}