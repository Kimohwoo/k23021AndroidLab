package com.example.test11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.test11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuItem1 : MenuItem? = menu?.add(0,0,0,"메뉴1")
        val menuItem2 : MenuItem? = menu?.add(0,1,0, "메뉴2")
        return super.onCreateOptionsMenu(menu)
    }

    //메뉴가 선택되었을 떄, 이벤트 처리하는 함수
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        0 -> {
            Toast.makeText(this@MainActivity, "메뉴1", Toast.LENGTH_SHORT).show()
            true
        }
        1 -> {
            val intent = Intent(this@MainActivity, ThreeActivity::class.java)
            //단순 화면 전환으로만 사용하니까, 기본
            startActivity(intent)
            true
        } else -> super.onOptionsItemSelected(item)
    }

    //재정의 샘플
    override fun onSupportNavigateUp(): Boolean {
        Toast.makeText(this@MainActivity, "메인 업버튼 동작", Toast.LENGTH_SHORT).show()

        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //코드로 업버튼 생성
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btn1.setOnClickListener {

            //버튼 클릭시 화면 전환 방법, 미리 인텐트를 이용하고 구체적인 데이터 전달은
            //13장에서 이야기
            val intent = Intent(this@MainActivity, TwoActivity::class.java)
            //단순 화면 전환으로만 사용하니까, 기본
            startActivity(intent)

        }

    }
}