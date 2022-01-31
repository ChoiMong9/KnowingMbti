package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test11 : AppCompatActivity(), View.OnClickListener {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test11btn : Button
    lateinit var test11btn2 : Button
    lateinit var floatingActionButton11 : FloatingActionButton

    //상태 저장용 변수
    var judging : Int = 0
    var perceiving : Int = 0

    //전 액티비티 인텐트 변수
    var pjudging : Int = 0
    var pperceiving : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test11)

        test11btn=findViewById(R.id.test11btn)
        test11btn2=findViewById(R.id.test11btn2)
        floatingActionButton11=findViewById(R.id.floatingActionButton11)

        dbManager= DBManager(this,"mbti",null,1)

        test11btn.setOnClickListener(this)
        test11btn2.setOnClickListener(this)
        floatingActionButton11.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test11btn -> {//1번 질문을 눌렀을 때
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
                val intent = Intent(this, test12::class.java)
                startActivity(intent)
                intent.putExtra("intent_judging",judging)
                intent.putExtra("intent_perceiving",perceiving)
            }
            R.id.test11btn2 -> {//2번 질문을 눌렀을 때
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
                val intent = Intent(this, test12::class.java)
                startActivity(intent)
                intent.putExtra("intent_judging",judging)
                intent.putExtra("intent_perceiving",perceiving)
            }
            R.id.floatingActionButton11 -> {//뒤로가기 버튼을 눌렀을 때
                //이번 액티비티에서 선택되기 전 액티비티 상태로 업데이트
                pjudging=intent.getIntExtra("intent_judging",0)
                pperceiving=intent.getIntExtra("intent_perceiving",0)

                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET judging="+pjudging+", perceiving="+pperceiving+";")
                sqlitedb.close()

                //이전 액티비티로 전환
                val intent = Intent(this, test10::class.java)
                startActivity(intent)
            }

        }
    }
}