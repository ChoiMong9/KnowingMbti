package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test7 : AppCompatActivity(), View.OnClickListener {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test7btn : Button
    lateinit var test7btn2 : Button
    lateinit var floatingActionButton7 : FloatingActionButton

    //상태 저장용 변수
    var thinking : Int = 0
    var feeling : Int = 0

    //전 액티비티 인텐트 변수
    var psensing : Int = 0
    var piNtuition : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test7)

        test7btn=findViewById(R.id.test7btn)
        test7btn2=findViewById(R.id.test7btn2)
        floatingActionButton7=findViewById(R.id.floatingActionButton7)

        dbManager= DBManager(this,"mbti",null,1)

        test7btn.setOnClickListener(this)
        test7btn2.setOnClickListener(this)
        floatingActionButton7.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test7btn -> {//1번 질문을 눌렀을 때
                //업데이트전 mbti상태 인텐트로 보내기
                val intent = Intent(this, test8::class.java)
                intent.putExtra("intent_thinking",thinking)
                intent.putExtra("intent_feeling",feeling)

                //해당 mbti변수 증가시키기
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
                startActivity(intent)
            }
            R.id.test7btn2 -> {//2번 질문을 눌렀을 때
                //업데이트전 mbti상태 인텐트로 보내기
                val intent = Intent(this, test8::class.java)
                intent.putExtra("intent_thinking",thinking)
                intent.putExtra("intent_feeling",feeling)

                //해당 mbti변수 증가시키기
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
                startActivity(intent)
            }
            R.id.floatingActionButton7 -> {//뒤로가기 버튼을 눌렀을 때
                //이번 액티비티에서 선택되기 전 액티비티 상태로 업데이트
                psensing=intent.getIntExtra("intent_sensing",0)
                piNtuition=intent.getIntExtra("intent_iNtuition",0)

                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET sensing="+psensing+", iNtuition="+piNtuition+";")
                sqlitedb.close()

                //이전 액티비티로 전환
                val intent = Intent(this, test6::class.java)
                startActivity(intent)
            }

        }
    }
}