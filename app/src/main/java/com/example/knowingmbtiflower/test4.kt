package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test4 : AppCompatActivity(), View.OnClickListener {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test4btn : Button
    lateinit var test4btn2 : Button
    lateinit var floatingActionButton4 : FloatingActionButton

    //상태 저장용 변수
    var sensing : Int = 0
    var iNtuition : Int = 0

    //전 액티비티 인텐트 변수
    var pextra : Int = 0
    var pintro : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test4)

        test4btn=findViewById(R.id.test4btn)
        test4btn2=findViewById(R.id.test4btn2)
        floatingActionButton4=findViewById(R.id.floatingActionButton4)

        dbManager= DBManager(this,"mbti",null,1)

        test4btn.setOnClickListener(this)
        test4btn2.setOnClickListener(this)
        floatingActionButton4.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test4btn -> {//1번 질문을 눌렀을 때
                //업데이트전 mbti상태 인텐트로 보내기
                val intent = Intent(this, test5::class.java)
                intent.putExtra("intent_sensing",sensing)
                intent.putExtra("intent_iNtuition",iNtuition)

                //해당 mbti변수 증가시키기
                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET sensing=sensing+1;")
                sqlitedb.close()

                //반영된 결과 인텐트로 옮기기
                sqlitedb=dbManager.readableDatabase

                var cursor : Cursor
                cursor=sqlitedb.rawQuery("SELECT * FROM mbti;",null)

                if(cursor.moveToNext()){
                    sensing=cursor.getInt((cursor.getColumnIndex("sensing")))
                    iNtuition=cursor.getInt((cursor.getColumnIndex("iNtuition")))
                }
                cursor.close()
                sqlitedb.close()
                dbManager.close()

                //화면전환
                startActivity(intent)
            }
            R.id.test4btn2 -> {//2번 질문을 눌렀을 때
                //업데이트전 mbti상태 인텐트로 보내기
                val intent = Intent(this, test5::class.java)
                intent.putExtra("intent_sensing",sensing)
                intent.putExtra("intent_iNtuition",iNtuition)

                //해당 mbti변수 증가시키기
                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET iNtuition=iNtuition+1;")
                sqlitedb.close()

                //반영된 결과 인텐트로 옮기기
                sqlitedb=dbManager.readableDatabase

                var cursor : Cursor
                cursor=sqlitedb.rawQuery("SELECT * FROM mbti;",null)

                if(cursor.moveToNext()){
                    sensing=cursor.getInt((cursor.getColumnIndex("sensing")))
                    iNtuition=cursor.getInt((cursor.getColumnIndex("iNtuition")))
                }
                cursor.close()
                sqlitedb.close()
                dbManager.close()

                //화면전환
                startActivity(intent)
            }
            R.id.floatingActionButton4 -> {//뒤로가기 버튼을 눌렀을 때
                //이번 액티비티에서 선택되기 전 액티비티 상태로 업데이트
                pextra=intent.getIntExtra("intent_extra",0)
                pintro=intent.getIntExtra("intent_intro",0)

                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET extra="+pextra+", intro="+pintro+";")
                sqlitedb.close()

                //이전 액티비티로 전환
                val intent = Intent(this, test3::class.java)
                startActivity(intent)
            }

        }
    }
}