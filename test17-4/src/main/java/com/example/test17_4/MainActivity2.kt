package com.example.test17_4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //공유 프리퍼런스 예제 사용해보기
        //순서1
        val pref = getSharedPreferences("inputPref", Context.MODE_PRIVATE)
        //값 설정 부분
        //순서2
        pref.edit().run {
            putString("test", "앱별 공유 프리퍼런스 테스트 중... 값 부분")
            putString("name", "이상용, 공유 파일명은 : inputPref")
            commit()
        }

        //값 가져오는 부분
        //순서3
        val resultStr2 : String? = pref.getString("test","default")
        val resultStr3 : String? = pref.getString("name","default")
        val result3 = resultStr2.toString()
        val result4 = resultStr3.toString()
        Log.d("lsy","test result3 결과 : $resultStr2")
        Log.d("lsy","name result4 결과 : $resultStr3")
        Toast.makeText(this, "${result3}, ${result4}", Toast.LENGTH_SHORT).show()
    }
}