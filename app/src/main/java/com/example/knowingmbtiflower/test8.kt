package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test8 : AppCompatActivity(), View.OnClickListener {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test8btn : Button
    lateinit var test8btn2 : Button
    lateinit var floatingActionButton8 : FloatingActionButton

    //상태 저장용 변수
    var thinking : Int = 0
    var feeling : Int = 0

    //전 액티비티 인텐트 변수
    var pthinking : Int = 0
    var pfeeling : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test8)

        test8btn=findViewById(R.id.test8btn)
        test8btn2=findViewById(R.id.test8btn2)
        floatingActionButton8=findViewById(R.id.floatingActionButton8)

        dbManager= DBManager(this,"mbti",null,1)

        test8btn.setOnClickListener(this)
        test8btn2.setOnClickListener(this)
        floatingActionButton8.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test8btn -> {//1번 질문을 눌렀을 때
                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET thinking=thinking+1;")
                sqlitedb.close()

                //반영된 결과 인텐트로 옮기기
                sqlitedb=dbManager.readableDatabase

                var cursor : Cursor
                cursor=sqlitedb.rawQuery("SELECT * FROM mbti;",null)

                if(cursor.moveToNext()){
                    thinking=cursor.getInt((cursor.getColumnIndex("thinking")))
                    feeling=cursor.getInt((cursor.getColumnIndex("feeling")))
                }
                cursor.close()
                sqlitedb.close()
                dbManager.close()

                //화면전환
                val intent = Intent(this, test9::class.java)
                startActivity(intent)
                intent.putExtra("intent_thinking",thinking)
                intent.putExtra("intent_feeling",feeling)
            }
            R.id.test8btn2 -> {//2번 질문을 눌렀을 때
                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET feeling=feeling+1;")
                sqlitedb.close()

                //반영된 결과 인텐트로 옮기기
                sqlitedb=dbManager.readableDatabase

                var cursor : Cursor
                cursor=sqlitedb.rawQuery("SELECT * FROM mbti;",null)

                if(cursor.moveToNext()){
                    thinking=cursor.getInt((cursor.getColumnIndex("thinking")))
                    feeling=cursor.getInt((cursor.getColumnIndex("feeling")))
                }
                cursor.close()
                sqlitedb.close()
                dbManager.close()

                //화면전환
                val intent = Intent(this, test9::class.java)
                startActivity(intent)
                intent.putExtra("intent_thinking",thinking)
                intent.putExtra("intent_feeling",feeling)
            }
            R.id.floatingActionButton8 -> {//뒤로가기 버튼을 눌렀을 때
                //이번 액티비티에서 선택되기 전 액티비티 상태로 업데이트
                pthinking=intent.getIntExtra("intent_thinking",0)
                pfeeling=intent.getIntExtra("intent_feeling",0)

                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET thinking="+pthinking+", feeling="+pfeeling+";")
                sqlitedb.close()

                //이전 액티비티로 전환
                val intent = Intent(this, test7::class.java)
                startActivity(intent)
            }

        }
    }
}