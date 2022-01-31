package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test12 : AppCompatActivity(){
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test12btn : Button
    lateinit var test12btn2 : Button
    lateinit var floatingActionButton12 : FloatingActionButton

    //전 액티비티 인텐트 변수
    var pjudging : Int = 0
    var pperceiving : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test12)

        test12btn=findViewById(R.id.test12btn)
        test12btn2=findViewById(R.id.test12btn2)
        floatingActionButton12=findViewById(R.id.floatingActionButton12)

        dbManager= DBManager(this,"mbti",null,1)

        //마지막 변수 업데이트
        test12btn.setOnClickListener{
            sqlitedb=dbManager.writableDatabase
            sqlitedb.execSQL("UPDATE mbti SET judging=judging+1;")
            sqlitedb.close()

            val intent = Intent(this, result_main::class.java)
            startActivity(intent)
        }
        test12btn2.setOnClickListener{
            sqlitedb=dbManager.writableDatabase
            sqlitedb.execSQL("UPDATE mbti SET perceiving=perceiving+1;")
            sqlitedb.close()

            val intent = Intent(this, result_main::class.java)
            startActivity(intent)
        }
        floatingActionButton12.setOnClickListener{
            //이번 액티비티에서 선택되기 전 액티비티 상태로 업데이트
            pjudging=intent.getIntExtra("intent_judging",0)
            pperceiving=intent.getIntExtra("intent_perceiving",0)

            sqlitedb=dbManager.writableDatabase
            sqlitedb.execSQL("UPDATE mbti SET judging="+pjudging+", perceiving="+pperceiving+";")
            sqlitedb.close()

            //이전 액티비티로 전환
            val intent = Intent(this, test11::class.java)
            startActivity(intent)
        }

    }

}