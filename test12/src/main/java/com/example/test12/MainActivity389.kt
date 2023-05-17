package com.example.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.test12.databinding.ActivityMain389Binding

class MainActivity389 : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain389Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.btn1.setOnClickListener{
            when(binding.btn1.isExtended){
                true -> binding.btn1.shrink()
                false -> binding.btn1.extend()
            }
        }

        //플로팅 액션바 화면에 마치 둥둥 떠있는 버튼 연상
        //구글의 머터리얼 디자인에서 나온 기능을 가져다 사용 중
        //이벤트 처리 부분이 좀 더 쉽다.

        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("kkang","navigation item click... ${it.title}")
            true
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