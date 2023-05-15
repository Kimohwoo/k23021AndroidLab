package com.example.test10

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.test10.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val eventHandler = object : DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            if(which == DialogInterface.BUTTON_POSITIVE){
                Toast.makeText(this@MainActivity, "확인시 토스트 띄우기", Toast.LENGTH_SHORT).show()
            } else if(which == DialogInterface.BUTTON_NEGATIVE){
                Toast.makeText(this@MainActivity, "취소시 토스트 띄우기", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun showTest(){
        val toast = Toast.makeText(this, "메세지 내용", Toast.LENGTH_SHORT)
        toast.addCallback(
            object : Toast.Callback(){
                override fun onToastHidden() {
                    super.onToastHidden()
                    Log.d("lsy", "토스트 숨겨진 후 추가 기능 동작")
                }

                override fun onToastShown() {
                    super.onToastShown()
                    Log.d("lsy", "토스트 보여진 후 추가 기능 동작")
                }
            }
        )
        toast.show()
    }


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //소리부분 확인작업
        binding.btnSound.setOnClickListener {
            val notification : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
            ringtone.play()
        }


        binding.btnCheck1.setOnClickListener {
            val items = arrayOf<String>("두루치기","된장", "밀면", "칼국수")
            AlertDialog.Builder(this).run {
                setTitle("체크박스 alert 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setSingleChoiceItems(items, 0, object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("lsy",
                            "선택한 점심 메뉴 : ${items[which]} 이 선택되었습니다.")
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }

        }

        //다이얼로그에 체크박스 선택 부분 해보기
        binding.btnCheck.setOnClickListener {
            val items = arrayOf<String>("두루치기","된장", "밀면", "칼국수")
            AlertDialog.Builder(this).run {
                setTitle("체크박스 alert 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setMultiChoiceItems(items, booleanArrayOf(true, false, false, false),
                object : DialogInterface.OnMultiChoiceClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                        Log.d("lsy",
                            "선택한 점심 메뉴 : ${items[which]} 이 ${if(isChecked)"선택됨" else "선택 해제됨"}")
                    }
                })
                setCancelable(false)
                setPositiveButton("닫기", null)
                show()
            }.setCanceledOnTouchOutside(true)
        }

        //다이얼로그에 메뉴 선택 부분 확인 해보기
        binding.btnMenu.setOnClickListener {
            val items = arrayOf<String>("두루치기","된장", "밀면", "칼국수")
            AlertDialog.Builder(this).run {
                setTitle("메뉴 alert 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(items, object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("lsy", "선택한 점심 메뉴 : ${items[which]}")
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }
        }

        //경고창, 특정 정보를 띄워서, 확인시 동작 기능, 취소시 동작
        //설정한 eventHandler 변수에 담긴 , 익명 함수를 사용함
        binding.btnalert.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("test Dialog")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("확인", eventHandler)
                setNegativeButton("취소", eventHandler)
//                setNeutralButton("More", null)
//                setPositiveButton("OK", null)
//                setNegativeButton("Cancle", null)
                show()
            }
        }

        binding.btnTime.setOnClickListener{
            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Log.d("lsy", "time : $hourOfDay, minute : $minute")
                    Toast.makeText(this@MainActivity, "${hourOfDay}시 ${minute}분", Toast.LENGTH_SHORT).show()
                }
            }, 0, 0, true).show()
        }

        //val toast = Toast.makeText(this, "메세지 내용", Toast.LENGTH_SHORT)

        //날짜 다이얼로그 띄우기, 출력은 콘솔 또는 토스트 메세지
        binding.btnDate.setOnClickListener {
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d("lsy", "year : $year, month : ${month+1}, dayOfMonth : $dayOfMonth")
                    Toast.makeText(this@MainActivity, "${year}년 ${month+1}월 ${dayOfMonth}일", Toast.LENGTH_SHORT).show()
                }
            }, 2020, 5, 15).show()
        }

        binding.btn1.setOnClickListener {
            //toast.show()
            //Toast.makeText(this, "토스트 출력 방법 2", Toast.LENGTH_SHORT).show()

            //옵션, 토스트 메세지가 보여지거나 사라졌을 경우에 추가 기능을 확인중
            showTest()
        }

        /*val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){
            isGranted ->
            if(isGranted){
                Log.d("lsy", "승인됨")
            } else {
                Log.d("lsy", "승인안됨")
            }
        }

        val status = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
        if(status == PackageManager.PERMISSION_GRANTED){
            Log.d("lsy", "승인 됨2")
        } else {
            requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
        }*/

    }
}