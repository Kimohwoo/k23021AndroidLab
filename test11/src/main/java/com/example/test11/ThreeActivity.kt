package com.example.test11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.test11.databinding.ActivityThreeBinding

class ThreeActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater.inflate(R.menu.menu_three, menu)

        //search뷰 이벤트 처리 관련 코드...
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_three, menu)
        val menuItem = menu?.findItem(R.id.serchMenu)
        val searchView = menuItem?.actionView as SearchView
        //액션바 -> 서치뷰의 이벤트 핸틀러 정의
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            //기본 제공함수 3가지
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 수행하는 함수
                Log.d("lsy", "검색어가 변경시 내용: ${newText}")
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드의 검색 버튼을 클릭한 순간의 이벤트
                Toast.makeText(this@ThreeActivity, "클릭시 이벤트 발생", Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return true


        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn2.setOnClickListener {
            val intent = Intent(this@ThreeActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}