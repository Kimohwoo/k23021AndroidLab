package com.example.test8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.CompoundButton
import com.example.test8.databinding.ActivityMainBinding

//class MyEventHandler : CompoundButton.OnCheckedChangeListener{
//    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        Log.d("lsy", "클레스로 구현 방법2 체크박스 클릭")
//    }
//}

//class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //방법1:
//        binding.check1.setOnCheckedChangeListener(this)

        //방법2:
        //클래스로 정의된 리스너 사용
//        binding.check1.setOnCheckedChangeListener(MyEventHandler())
        
        //방법3: SAM 기법 자바 : 함수형 인터페이스, 람다식 구현
        binding.check1.setOnCheckedChangeListener{
            a, b -> Log.d("lsy", "방법 3 SAM 기법 구현 : 체크박스 클릭")
        }

        binding.btn1.setOnLongClickListener{
            Log.d("lsy", "롱 클릭")
            true
        }
    }
//    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//
//        Log.d("lsy", "체크박스 클릭.")
//
//    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                Log.d("lsy", "좌표 x : ${event.x}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("lsy", "좌표 y : ${event.y}")
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_BACK -> Log.d("lsy", "Back KEY 누름")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("lsy", "볼륨 업 KEY 누름")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("lsy", "볼륨 다운 KEY 누름")
        }
        Log.d("lsy", "onKeyDown 실행")
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("lsy", "onKeyUp 실행")
        return super.onKeyUp(keyCode, event)
    }

}