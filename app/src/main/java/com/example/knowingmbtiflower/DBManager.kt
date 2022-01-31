package com.example.knowingmbtiflower

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {//mbti DB 생성
        db!!.execSQL("CREATE TABLE mbti (extra INTEGER, intro INTEGER, sensing INTEGER, iNtuition INTEGER, thinking INTEGER, feeling INTEGER, judging INTEGER, perceiving INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {//테이블 초기화 시 사용
        db!!.execSQL("DROP TABLE IF EXISTS mbti")
        onCreate(db)
    }
}