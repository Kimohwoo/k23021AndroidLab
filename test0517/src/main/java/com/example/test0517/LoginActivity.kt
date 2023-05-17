package com.example.test0517

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test0517.databinding.ActivityLoginBinding
import com.example.test0517.databinding.ActivityMainBinding
import com.example.test0517.databinding.ItemRecyclerviewBinding

class LoginActivity : AppCompatActivity() {
        lateinit var toggle: ActionBarDrawerToggle
        val id = "admin"
        val password = "1234"
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
            //툴바 적용하기
            setSupportActionBar(binding.toolbar)

            binding.back.setOnClickListener {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }

            binding.check.setOnClickListener {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }

        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            //이벤트가 toggle 버튼에서 제공된거라면..
            if(toggle.onOptionsItemSelected(item)){
                return true
            }
            return super.onOptionsItemSelected(item)
        }

    }