package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test11.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)

        //이 속성은 API 28부터 사용가능
        binding.text1.lineHeight

        //제트팩에서 제공하는 androidx.appcompat.widget.AppCompatTextView 사용하면
        binding.textA2.lineHeight

    }
}