package com.example.test11

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test11.databinding.ActivityMain5Binding
import com.example.test11.databinding.Item342Binding

//참고 코드 : MainActivity350
class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //여기 부분 부터 아래 전부다 붙여넣기 했음.
        val datas = mutableListOf<String>()
        for(i in 1..9){
            datas.add("Item $i")
        }
        //설정 적용
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(datas)
        binding.recyclerView.addItemDecoration(MyDecoration(this))
    }

    //순서 1
    class MyViewHolder(val binding: Item342Binding): RecyclerView.ViewHolder(binding.root)
    //순서 2
    class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun getItemCount(): Int{
            Log.d("lsy", "init datas size: ${datas.size}")
            return datas.size
        }

        // 우리가 만든 뷰 홀더 생성자 호출하는 부분
        //엑티비티에서 바인딩 설정 후 적용하는 코드 부분과 비슷
        //보통 샘플 설정 코드, 이 패턴으로 자주 사용함
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyViewHolder(Item342Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Log.d("lsy","onBindViewHolder : $position")
            val binding=(holder as MyViewHolder).binding
            //뷰에 데이터 출력
            binding.itemData.text= datas[position]

            //뷰에 이벤트 추가
            binding.itemRoot.setOnClickListener{
                Log.d("lsy", "item root click : $position")
            }
        }
    }
    //순서 3 옵션 이미지 넣고 사이즈 조절, 위치 할당 하는 코드
    class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)
            c.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.stadium), 0f,0f,null);
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)
            //뷰 사이즈 계산
            val width = parent.width
            val height = parent.height
            //이미지 사이즈 계산
            val dr: Drawable? = ResourcesCompat.getDrawable(context.getResources(), R.drawable.kbo, null)
            val drWidth = dr?.intrinsicWidth
            val drHeight = dr?.intrinsicHeight
            //이미지가 그려질 위치 계산
            val left = width / 2 - drWidth?.div(2) as Int
            val top = height / 2 - drHeight?.div(2) as Int
            c.drawBitmap(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.kbo),
                left.toFloat(),
                top.toFloat(),
                null
            )

        }

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

            view.setBackgroundColor(Color.LTGRAY)
            ViewCompat.setElevation(view, 20.0f)

        }
    }
}