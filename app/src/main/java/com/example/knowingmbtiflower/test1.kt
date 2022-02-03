package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test1 : AppCompatActivity(), View.OnClickListener{
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test1btn : Button
    lateinit var test1btn2 : Button
    lateinit var floatingActionButton : FloatingActionButton

    //상태 저장용 변수
    var extra : Int = 0
    var intro : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        test1btn=findViewById(R.id.test1btn)
        test1btn2=findViewById(R.id.test1btn2)
        floatingActionButton=findViewById(R.id.floatingActionButton)

        dbManager= DBManager(this,"mbti",null,1)

        test1btn.setOnClickListener(this)
        test1btn2.setOnClickListener(this)
        floatingActionButton.setOnClickListener(this)

        //시작하면서 변수 0 초기화
        sqlitedb=dbManager.writableDatabase
        dbManager.onUpgrade(sqlitedb, 1,2)
        sqlitedb.execSQL("INSERT INTO mbti VALUES (0, 0, 0, 0, 0, 0, 0, 0);")
        sqlitedb.close()



    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test1btn -> {//1번 질문을 눌렀을 때
                //해당 mbti변수 증가시키기
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
                val intent = Intent(this, test2::class.java)
                intent.putExtra("intent_extra",extra)
                intent.putExtra("intent_intro",intro)
                startActivity(intent)

            }
            R.id.test1btn2 -> {//2번 질문을 눌렀을 때
                //해당 mbti변수 증가시키기
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
                val intent = Intent(this, test2::class.java)
                intent.putExtra("intent_extra",extra)
                intent.putExtra("intent_intro",intro)
                startActivity(intent)
            }
            R.id.floatingActionButton -> {//뒤로가기 버튼을 눌렀을 때
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}