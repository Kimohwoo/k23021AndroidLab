package com.example.test0517

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test0517.databinding.ActivityMainBinding
import com.example.test0517.databinding.ItemRecyclerviewBinding

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun getItemCount(): Int {
            return datas.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding = (holder as MyViewHolder).binding
            binding.itemData.text = datas[position]
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //툴바 적용하기
        setSupportActionBar(binding.toolbar)

        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("Item $i")
        }

        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = MyAdapter(datas)
        binding.recyclerView.adapter = adapter
//        binding.recyclerView.addItemDecoration(MyDecoration(this))

        binding.mainDrawerView.setNavigationItemSelectedListener {
                menuItem ->
            when(menuItem.itemId){
                R.id.login -> {
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.home -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    true
                }
                R.id.adduser -> {
                    startActivity(Intent(applicationContext, AddUserActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}