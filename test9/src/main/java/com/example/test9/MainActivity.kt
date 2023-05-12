package com.example.test9

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowMetrics
import com.example.test9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.textView1.text = getString(R.string.test)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val windowMetrics : WindowMetrics = windowManager.currentWindowMetrics
            Log.d("lsy", "width: ${windowMetrics.bounds.width()}, height : ${windowMetrics.bounds.height()}")
        } else{
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display?.getRealMetrics(displayMetrics)
            Log.d("lsy", "width: ${displayMetrics.widthPixels}, height : ${displayMetrics.heightPixels}")
        }

    }
}