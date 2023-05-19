package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //데이터 가져오기
        val data1 = intent.getStringExtra("data1")
        // 2번째 매개변수, 디폴트 값은 해당 값이 없을 때의 값
        val data2 = intent.getIntExtra("data2", 0)

        binding.resultViewText.text = data1

        //후처리 작업 테스트
        //버튼 클릭시 후처리 작업
        binding.btn2.setOnClickListener {
            //작업 set
            intent.putExtra("result", "후처리 데이터 값")
            intent.putExtra("result2", "후처리 방법2 데이터 값")
            //결과 코드와, 인텐트 전달 작업
            setResult(-1, intent)
            //현재 화면 종료, 객체 완전 소멸
            finish()
        }

    }
}