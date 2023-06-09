package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityBundleBinding

class BundleActivity : AppCompatActivity() {
    //변경되는 변수 및, 바인딩 객체 따로 빼기
    var count = 0
    lateinit var binding: ActivityBundleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBundleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //MainActivity424 내용 참고
        //xml 뷰 작업에서 버튼, 텍스트 뷰
        //버튼 클릭시, 해당 값이 변경되는 부분 확인
        //번들 객체 이용, 저장, 불러오기

        binding.plusCountButton.setOnClickListener {
            count++
            binding.countResultView.text="$count"
        }



    }


    //재정의하기 매개변수 1개인 경우
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("lsy","onSaveInstanceState..........")
        outState.putString("data1", "hello")
        outState.putInt("data2", 20)
        outState.putInt("data3", 10)
    }


    //재정의하기 매개변수 1개인 경우
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data1 = savedInstanceState.getString("data1")
        val data2 = savedInstanceState.getInt("data2")
        val data3 = savedInstanceState.getInt("data3")
        Log.d("lsy", "$data2 의 값과 $data3 의 값 확인")
        binding.countResultView.text="${data2-data3}"
    }

}