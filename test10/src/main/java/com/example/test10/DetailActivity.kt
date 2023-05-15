package com.example.test10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test10.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}