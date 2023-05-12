package com.example.ch8_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var initTime = 0L
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            if(initTime == 0L) {
                binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
                binding.chronometer.start()
                binding.stopButton.isEnabled = true
                binding.resetButton.isEnabled = true
                binding.startButton.isEnabled = false
            }
        }
        binding.stopButton.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.startButton.isEnabled=true
            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=false
        }
        binding.resetButton.setOnClickListener {
            binding.chronometer.stop()
            pauseTime = 0L
            binding.startButton.isEnabled=true
            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=false
        }

    }
}