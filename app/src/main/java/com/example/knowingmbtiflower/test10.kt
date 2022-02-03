package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test10 : AppCompatActivity(), View.OnClickListener {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test10btn : Button
    lateinit var test10btn2 : Button
    lateinit var floatingActionButton10 : FloatingActionButton

    //상태 저장용 변수
    var judging : Int = 0
    var perceiving : Int = 0

    //전 액티비티 인텐트 변수
    var pthinking : Int = 0
    var pfeeling : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test10)

        test10btn=findViewById(R.id.test10btn)
        test10btn2=findViewById(R.id.test10btn2)
        floatingActionButton10=findViewById(R.id.floatingActionButton10)

        dbManager= DBManager(this,"mbti",null,1)

        test10btn.setOnClickListener(this)
        test10btn2.setOnClickListener(this)
        floatingActionButton10.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test10btn -> {//1번 질문을 눌렀을 때
                //업데이트전 mbti상태 인텐트로 보내기
                val intent = Intent(this, test11::class.java)
                intent.putExtra("intent_judging",judging)
                intent.putExtra("intent_perceiving",perceiving)

                //해당 mbti변수 증가시키기
                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET judging=judging+1;")
                sqlitedb.close()

                //반영된 결과 인텐트로 옮기기
                sqlitedb=dbManager.readableDatabase

                var cursor : Cursor
                cursor=sqlitedb.rawQuery("SELECT * FROM mbti;",null)

                if(cursor.moveToNext()){
                    judging=cursor.getInt((cursor.getColumnIndex("judging")))
                    perceiving=cursor.getInt((cursor.getColumnIndex("perceiving")))
                }
                cursor.close()
                sqlitedb.close()
                dbManager.close()

                //화면전환
                startActivity(intent)
            }
            R.id.test10btn2 -> {//2번 질문을 눌렀을 때
                //업데이트전 mbti상태 인텐트로 보내기
                val intent = Intent(this, test11::class.java)
                intent.putExtra("intent_judging",judging)
                intent.putExtra("intent_perceiving",perceiving)

                //해당 mbti변수 증가시키기
                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET perceiving=perceiving+1;")
                sqlitedb.close()

                //반영된 결과 인텐트로 옮기기
                sqlitedb=dbManager.readableDatabase

                var cursor : Cursor
                cursor=sqlitedb.rawQuery("SELECT * FROM mbti;",null)

                if(cursor.moveToNext()){
                    judging=cursor.getInt((cursor.getColumnIndex("judging")))
                    perceiving=cursor.getInt((cursor.getColumnIndex("perceiving")))
                }
                cursor.close()
                sqlitedb.close()
                dbManager.close()

                //화면전환
                startActivity(intent)
            }
            R.id.floatingActionButton10 -> {//뒤로가기 버튼을 눌렀을 때
                //이번 액티비티에서 선택되기 전 액티비티 상태로 업데이트
                pthinking=intent.getIntExtra("intent_thinking",0)
                pfeeling=intent.getIntExtra("intent_feeling",0)

                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET thinking="+pthinking+", feeling="+pfeeling+";")
                sqlitedb.close()

                //이전 액티비티로 전환
                val intent = Intent(this, test8::class.java)
                startActivity(intent)
            }

        }
    }
}