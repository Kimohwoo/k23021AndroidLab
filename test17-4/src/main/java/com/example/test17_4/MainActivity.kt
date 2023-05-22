package com.example.test17_4

import com.example.test17_4.databinding.ActivityMainBinding
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var file : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //공유 프리퍼런스 값 불러오기
        val pref = getSharedPreferences("inputPref", Context.MODE_PRIVATE)
        //값 설정 부분

        //값 가져오는 부분
        val resultStr2 : String? = pref.getString("test","default")
        val resultStr3 : String? = pref.getString("name","default")
        val result3 = resultStr2.toString()
        val result4 = resultStr3.toString()
        Log.d("lsy","test result3 결과 : $resultStr2")
        Log.d("lsy","name result4 결과 : $resultStr3")
        Toast.makeText(this, "${result3}, ${result4}", Toast.LENGTH_SHORT).show()

        binding.button1.setOnClickListener {
            file = File(filesDir, "test230522-1.txt")
            Log.d("lsy", "${file?.absolutePath}")
            val writeStream: OutputStreamWriter = file.writer()
            //writeStream 객체에 write 함수로 쓰기 작업을 진행
            writeStream.write("앱별 저장소에 파일 저장 테스트 내용")
            // 적용하는 부분
            writeStream.flush()

//            openFileOutput("test.txt", Context.MODE_PRIVATE).use {
//                it.write("hello world!!".toByteArray())
//            }

        }
        binding.button2.setOnClickListener {
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("lsy", "$it")
            }

//            openFileInput("test.txt").bufferedReader().forEachLine {
//                Log.d("kkang", "$it")
//            }
        }
    }
}