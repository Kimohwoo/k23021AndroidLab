package com.example.test12

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test12.databinding.ActivityMainBinding
import com.example.test12.databinding.ItemRecyclerviewBinding

class MainActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_378, menu)
        return super.onCreateOptionsMenu(menu)
    }

    class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun getItemCount(): Int{
            return datas.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding=(holder as MyViewHolder).binding
            binding.itemData.text= datas[position]
        }
    }

    class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val index = parent.getChildAdapterPosition(view) + 1

            if (index % 3 == 0) //left, top, right, bottom
                outRect.set(10, 10, 10, 60)
            else
                outRect.set(10, 10, 10, 0)

            view.setBackgroundColor(Color.parseColor("#28A0FF"))
            ViewCompat.setElevation(view, 20.0f)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var toggle: ActionBarDrawerToggle

        //툴바 적용하기
        setSupportActionBar(binding.toolbar)
        
        //임시 데이터 만들기
        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("Item $i")
        }

        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()


        //리사이클러뷰 만드는 재료
        //뷰홀더, 어댑터, 레이아웃 매니저, 적용
        //클래스 2개 만들고
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = MyAdapter(datas)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(MyDecoration(this))

        //앱바 라는 뷰에 툴바, 이미지 뷰, 리사이클러뷰
        //참고 코드 : Main378
    }

}