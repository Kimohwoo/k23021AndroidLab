package com.example.test12

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        //val handler = Handler()
        Handler().postDelayed(Runnable {
            val intent = Intent(applicationContext, MainActivity389::class.java)
            startActivity(intent) //인트로 실행 후 바로 MainActivity로 넘어감.
            finish()
        }, 1000) //1초 후 인트로 실행


    }
}