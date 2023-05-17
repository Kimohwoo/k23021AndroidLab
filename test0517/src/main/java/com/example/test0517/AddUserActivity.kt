package com.example.test0517

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test0517.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back1.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        binding.add.setOnClickListener {
            Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

    }
}