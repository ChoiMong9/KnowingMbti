package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test2 : AppCompatActivity(), View.OnClickListener {
    var dbManager: DBManager = DBManager(this,"mbti",null,1)
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test2btn : Button
    lateinit var test2btn2 : Button
    lateinit var floatingActionButton2 : FloatingActionButton

    //상태 저장용 변수
    var extra : Int = 0
    var intro : Int = 0

    //전 액티비티 인텐트 변수
    var pextra : Int = 0
    var pintro : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        test2btn=findViewById(R.id.test2btn)
        test2btn2=findViewById(R.id.test2btn2)
        floatingActionButton2=findViewById(R.id.floatingActionButton2)

        test2btn.setOnClickListener(this)
        test2btn2.setOnClickListener(this)
        floatingActionButton2.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test2btn -> {//1번 질문을 눌렀을 때
                //업데이트전 mbti상태 인텐트로 보내기
                val intent = Intent(this, test3::class.java)
                intent.putExtra("intent_extra",extra)
                intent.putExtra("intent_intro",intro)

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
                startActivity(intent)
            }
            R.id.test2btn2 -> {//2번 질문을 눌렀을 때
                //업데이트전 mbti상태 인텐트로 보내기
                val intent = Intent(this, test3::class.java)
                intent.putExtra("intent_extra",extra)
                intent.putExtra("intent_intro",intro)

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
                startActivity(intent)
            }
            R.id.floatingActionButton2 -> {//뒤로가기 버튼을 눌렀을 때
                //이번 액티비티에서 선택되기 전 액티비티 상태로 업데이트
                //전 액티비티에서 인텐트 불러오기
                pextra=intent.getIntExtra("intent_extra",0)
                pintro=intent.getIntExtra("intent_intro",0)

                //해당 mbti변수 증가시키기
                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET extra="+pextra+", intro="+pintro+";")
                sqlitedb.close()
                //이전 액티비티로 전환
                val intent = Intent(this, test1::class.java)
                startActivity(intent)
            }

        }
    }
}