package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class test6 : AppCompatActivity(), View.OnClickListener {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var test6btn : Button
    lateinit var test6btn2 : Button
    lateinit var floatingActionButton6 : FloatingActionButton

    //상태 저장용 변수
    var sensing : Int = 0
    var iNtuition : Int = 0

    //전 액티비티 인텐트 변수
    var psensing : Int = 0
    var piNtuition : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test6)

        test6btn=findViewById(R.id.test6btn)
        test6btn2=findViewById(R.id.test6btn2)
        floatingActionButton6=findViewById(R.id.floatingActionButton6)

        dbManager= DBManager(this,"mbti",null,1)

        test6btn.setOnClickListener(this)
        test6btn2.setOnClickListener(this)
        floatingActionButton6.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.test6btn -> {//1번 질문을 눌렀을 때
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
                val intent = Intent(this, test7::class.java)
                startActivity(intent)
                intent.putExtra("intent_sensing",sensing)
                intent.putExtra("intent_iNtuition",iNtuition)
            }
            R.id.test6btn2 -> {//2번 질문을 눌렀을 때
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
                val intent = Intent(this, test7::class.java)
                startActivity(intent)
                intent.putExtra("intent_sensing",sensing)
                intent.putExtra("intent_iNtuition",iNtuition)
            }
            R.id.floatingActionButton6 -> {//뒤로가기 버튼을 눌렀을 때
                //이번 액티비티에서 선택되기 전 액티비티 상태로 업데이트
                psensing=intent.getIntExtra("intent_sensing",0)
                piNtuition=intent.getIntExtra("intent_iNtuition",0)

                sqlitedb=dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE mbti SET sensing="+psensing+", iNtuition="+piNtuition+";")
                sqlitedb.close()

                //이전 액티비티로 전환
                val intent = Intent(this, test5::class.java)
                startActivity(intent)
            }

        }
    }
}