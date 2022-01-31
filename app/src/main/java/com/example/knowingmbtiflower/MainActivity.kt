package com.example.knowingmbtiflower

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var startButton : Button
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton=findViewById(R.id.startButton)
        dbManager= DBManager(this,"mbti",null,1)

        //시작화면에서 db 초기화
        sqlitedb=dbManager.writableDatabase
        dbManager.onUpgrade(sqlitedb, 1,2)
        sqlitedb.close()

        //버튼을 누를 시 액티비티 전환
        startButton.setOnClickListener {
            val intent = Intent(this, test1::class.java)
            startActivity(intent)
        }
    }
}