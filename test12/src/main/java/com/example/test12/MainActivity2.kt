package com.example.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test12.databinding.ActivityMain2Binding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {
    //참고 코드 : 388
    //탭만 따로 구상하는것이 아니라 뷰페이저2 뷰와 연동을 함
    //연동하는 과정에서 해당 탭 설정주기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //탭 부분 추가 -> xml 에서 작업을 먼저 했음
        val tabLayout = binding.tabs

        //뷰 페이저2 추가 -> xml에서 viewpager부분을 추가
        val viewPager = binding.viewPager

        //뷰 페이저2 어댑터 추가하는 부분 방법 2가지, 
        // 1) 어댑터 (리사이클러 뷰방식) 2) Fragment로 추가하는 방식 -> 이방식으로 작업을 많이함
        // 2) 방식으로 Fragment 클래스 등록 함
        // 현재 하나의 MainActivity 파일 안에 클래스를 만들어서 작업을 하지만
        // 사실 프래그먼트 끼리 폴더를 만들어서 따로 관리함
        //지금은 간단해서 내부에서 작업을 함
        // 어댑터, 뷰 객체에 데이터를 연결(연동) 하는 부분
        viewPager.adapter= MyFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab${(position + 1)}"
        }.attach()

    }

    //프래그먼트 어댑터 클래스 재사용
    //원래는 각각 프래그먼트 또한 만들어야 하지만
    //지금은 재사용
    class MyFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        val fragments: List<Fragment>

        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]

    }
}