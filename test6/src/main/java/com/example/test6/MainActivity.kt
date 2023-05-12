package com.example.test6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.test6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {// AppCompatActivity 부모 클래스로 부터 상속 받음.
    // 코드 작업 공간, 뷰 작업도 가능 하지만 가급적 뷰는 XML에서 작업하기
    // -> 여기는 데이터 관련 작업 및 이벤트 핸들러 등 작업위해, 분리
    // 한 공간에 뷰까지 더해서 작업 시 가독성이 안좋음, 유지보수도 어려움
    override fun onCreate(savedInstanceState: Bundle?) { //onCreate 액티비티의 생명주기에서 더 설명할 예정
        super.onCreate(savedInstanceState) // savedInstanceState -> 16장 저장소 개념에서 설명 예정
        // res -> layout -> xml파일 불러서 실제 화면 출력 하는 곳
        // 리소스 R.java 파일에 상수값으로 저장되는데, 여기서 불러옴
        // 그래서 코드에서 리소스 값 불러올 때 항상 R.layout 등으로 시작을해서 불러옴
        // 화면을 출력하겠다. 레이아웃 (뷰 그룹 : 뷰(텍스트, 이미지, 체크박스, 인풋, 라디오...)를 모아주는 역할)
        // setContentView(R.layout.activity_main)

        //뷰 바인딩 기술을 통해서 뷰들을 특정 바인딩이라는 객체에 모두 모아
        // 접근을 쉽게 해주는 기술
        // 사용하기전 항상, build.gradle 파일에 설정 혹, sync now 적용해서 사용

        //이 문법은 자동으로 생성된 바인딩 파일을 inflate 함수와 인자는 layoutInflater 고정
        //setContentView 화면에 그리기
        //bindng 변수에 모두 뷰가 다 들어가 있음
        // 필요한 뷰를 꺼내서 사용
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var status = 0
        //button1 을 뷰 바인딩 기법으로 접근
        binding.btn1.setOnClickListener {
            if(status == 0) {
                binding.img1.visibility = View.INVISIBLE
                status = 1
            } else {
                binding.img1.visibility = View.VISIBLE
                status = 0
            }
        }

        /*val button1 = findViewById<Button>(R.id.btn1)
        val img1 = findViewById<ImageView>(R.id.img1)
        var status = 0

        // button1 눌러서 -> image1 show/hide
        button1.setOnClickListener {
            if(status == 0) {
                img1.visibility = View.INVISIBLE
                status = 1
            } else {
                img1.visibility = View.VISIBLE
                status = 0
            }
        }*/

    }
}