package com.example.ch11_jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ch11_jetpack.databinding.ActivityMainBinding
import com.example.ch11_jetpack.databinding.FragmentOneBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

}