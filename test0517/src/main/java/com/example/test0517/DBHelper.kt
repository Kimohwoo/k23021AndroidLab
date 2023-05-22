package com.example.test0517

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table users (" +
                "_id VARCHAR primary key autoincrement," +
                "password VARCHAR NOT NULL," +
                "email VARCHAR, " +
                "phone VARCHAR," +
                "sex VARCHAR )"
        )
    }

    fun insertData(id: String?, password: String?, email: String?, phone: String?): Boolean {
        // 디비 사용시 쓰기, 수정, 삭제 ->writableDatabase 사용함.
        val db = this.writableDatabase
        // execSQL -> 대신에 ContentValues() 를 이용하면
        // SQL  문장 없이, 바로 메서드에 값만 인자로 넣어서
        // 이용하면, 쉽게 insert를 구현가능.
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, password)
        contentValues.put(COL_3, email)
        contentValues.put(COL_4, phone)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return if (result == -1L) false else true
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    companion object {
        // DatabaseHelper 클래스명 -> mydb
        // mydb.DATABASE_NAME -> 사용가능.
        const val DATABASE_NAME = "STUDENT.db" // 데이터베이스 명
        const val TABLE_NAME = "student_table" // 테이블 명

        // 테이블 항목
        const val COL_1 = "id"
        const val COL_2 = "password"
        const val COL_3 = "email"
        const val COL_4 = "phone"
    }

}