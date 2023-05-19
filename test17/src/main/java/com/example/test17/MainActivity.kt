package com.example.test17

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    //참고 코드 ->
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    class DBTest(context: Context): SQLiteOpenHelper(context,"testDB", null, 1){

        override fun onCreate(db: SQLiteDatabase?) {
            if (db != null) {
                db.execSQL(" CREATE TABLE user_tb( " +
                        " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name NOT NULL, " +
                        "count INTEGER)")
            }

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }


    }

}