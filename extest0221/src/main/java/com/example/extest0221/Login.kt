package com.example.extest0221

import java.util.Scanner

open class User(val id:String, val pw:String, val email:String, val phone:String){
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


    val id = sc.nextLine()
    val pw = sc.nextLine()
    val email = sc.nextLine()
    val phone = sc.nextLine()
    val user = User(id, pw, email, phone)



}