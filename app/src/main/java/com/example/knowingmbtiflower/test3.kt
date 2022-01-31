package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test3 : AppCompatActivity(), View.OnClickListener {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test3btn : Button
    lateinit var test3btn2 : Button
    lateinit var floatingActionButton3 : FloatingActionButton

    //상태 저장용 변수
    var extra : Int = 0
    var intro : Int = 0

    //전 액티비티 인텐트 변수
    var pextra : Int = 0
    var pintro : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test3)

        test3btn=findViewById(R.id.test3btn)
        test3btn2=findViewById(R.id.test3btn2)
        floatingActionButton3=findViewById(R.id.floatingActionButton3)

        dbManager= DBManager(this,"mbti",null,1)

        test3btn.setOnClickListener(this)
        test3btn2.setOnClickListener(this)
        floatingActionButton3.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test3btn -> {//1번 질문을 눌렀을 때
                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET extra=extra+1;")
                sqlitedb.close()

                //반영된 결과 인텐트로 옮기기
                sqlitedb=dbManager.readableDatabase

                var cursor : Cursor
                cursor=sqlitedb.rawQuery("SELECT * FROM mbti;",null)

                if(cursor.moveToNext()){
                    extra=cursor.getInt((cursor.getColumnIndex("extra")))
                    intro=cursor.getInt((cursor.getColumnIndex("intro")))
                }
                cursor.close()
                sqlitedb.close()
                dbManager.close()

                //화면전환
                val intent = Intent(this, test4::class.java)
                startActivity(intent)
                intent.putExtra("intent_extra",extra)
                intent.putExtra("intent_intro",intro)
            }
            R.id.test3btn2 -> {//2번 질문을 눌렀을 때
                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET intro=intro+1;")
                sqlitedb.close()

                //반영된 결과 인텐트로 옮기기
                sqlitedb=dbManager.readableDatabase

                var cursor : Cursor
                cursor=sqlitedb.rawQuery("SELECT * FROM mbti;",null)

                if(cursor.moveToNext()){
                    extra=cursor.getInt((cursor.getColumnIndex("extra")))
                    intro=cursor.getInt((cursor.getColumnIndex("intro")))
                }
                cursor.close()
                sqlitedb.close()
                dbManager.close()

                //화면전환
                val intent = Intent(this, test4::class.java)
                startActivity(intent)
                intent.putExtra("intent_extra",extra)
                intent.putExtra("intent_intro",intro)
            }
            R.id.floatingActionButton3 -> {//뒤로가기 버튼을 눌렀을 때
                //이번 액티비티에서 선택되기 전 액티비티 상태로 업데이트
                pextra=intent.getIntExtra("intent_extra",0)
                pintro=intent.getIntExtra("intent_intro",0)

                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET extra="+pextra+", intro="+pintro+";")
                sqlitedb.close()
                //이전 액티비티로 전환
                val intent = Intent(this, test2::class.java)
                startActivity(intent)
            }

        }
    }
}