package com.example.knowingmbtiflower

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class result_main : AppCompatActivity() {
    var dbManager: DBManager= DBManager(this,"mbti",null,1)
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var MyImage : ImageView
    lateinit var flowerName : TextView
    lateinit var flowerText : TextView
    lateinit var flowerGood : ImageView
    lateinit var flowerBad : ImageView
    lateinit var goodName : TextView
    lateinit var badName : TextView
    lateinit var restartBtn : Button

    var extra : Int = 0
    var intro : Int = 0
    var sensing : Int = 0
    var iNtuition : Int = 0
    var thinking : Int = 0
    var feeling : Int = 0
    var judging : Int = 0
    var perceiving : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_main)

        MyImage=findViewById(R.id.MyImage)
        flowerName=findViewById(R.id.flowerName)
        flowerText=findViewById(R.id.flowerText)
        flowerGood=findViewById(R.id.flowerGood)
        flowerBad=findViewById(R.id.flowerBad)
        goodName=findViewById(R.id.goodName)
        badName=findViewById(R.id.badName)
        restartBtn=findViewById(R.id.restartBtn)

        //다시시작 버튼
        restartBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //db 칼럼 추출하기
        sqlitedb=dbManager.readableDatabase

        var cursor : Cursor
        cursor=sqlitedb.rawQuery("SELECT * FROM mbti;",null)

        if(cursor.moveToNext()){
            extra=cursor.getInt((cursor.getColumnIndex("extra")))
            intro=cursor.getInt((cursor.getColumnIndex("intro")))
            sensing=cursor.getInt((cursor.getColumnIndex("sensing")))
            iNtuition=cursor.getInt((cursor.getColumnIndex("iNtuition")))
            thinking=cursor.getInt((cursor.getColumnIndex("thinking")))
            feeling=cursor.getInt((cursor.getColumnIndex("feeling")))
            judging=cursor.getInt((cursor.getColumnIndex("judging")))
            perceiving=cursor.getInt((cursor.getColumnIndex("perceiving")))
        }
        cursor.close()
        sqlitedb.close()
        dbManager.close()

        //mbti별로 결과 페이지 작성
        if(extra<intro && sensing>iNtuition && thinking>feeling && judging>perceiving){//istj
            MyImage.setImageResource(R.drawable.istj)
            flowerName.text="매화"
            flowerText.text="꽃말 : 고결, 충실, 인내, 맑은 마음\n가장 먼저 피어나 봄을 알리는 매화와 같이 의젓한 성격으로 장남, 장녀 같다는 이야기를 많이 듣는 편이에요.\n동양적인 이미지의 매화처럼 전통적인 가치관을 중요시해요. 하지만 자칫 꼰대로 느껴질 수 있으니 주의하는 게 좋겠어요.\n인내력과 책임강이 강하며 주어진 일은 끝까지 마무리해요.\n조직의 핵심 구성원 역할을 하는 경우가 많으며 충성심이 강해요.\n감정 표현에 서툴기는 하지만 세심하게 상대를 챙기는 사람이에요."
            flowerGood.setImageResource(R.drawable.esfp)
            goodName.text="히어리"
            flowerBad.setImageResource(R.drawable.enfj)
            badName.text="민들레"
        }
        if(extra<intro && sensing>iNtuition && thinking<feeling && judging>perceiving){//isfj
            MyImage.setImageResource(R.drawable.isfj)
            flowerName.text="개나리"
            flowerText.text="꽃말 : 희망, 기대, 깊은 정, 달성\n따사로운 개나리와 같이 따뜻한 마음씨를 가졌어요.\n공감 능력이 뛰어나며 안정과 평온을 중시해요.\n하지만 개나리의 줄기 속이 비어 있듯이 본인의 속내를 잘 드러내진 않아요.\n참고 참다 크게 터질 수 있으니 평소에 감정을 너무 억압하진 않는 게 좋겠어요.\n끈기 있고 성실하며 목표가 옳은 일이라면 그 목표를 달성하기 위해 열정적으로 행동하는 편이에요."
            flowerGood.setImageResource(R.drawable.esfp)
            goodName.text="히어리"
            flowerBad.setImageResource(R.drawable.enfp)
            badName.text="동백"
        }
        if(extra<intro && sensing<iNtuition && thinking<feeling && judging>perceiving){//infj
            MyImage.setImageResource(R.drawable.infj)
            flowerName.text="벌개미취"
            flowerText.text="꽃말 : 청초, 추억, 숨겨진 사랑, 너를 잊지 않으리\n국화과 꽃 중에서 가장 일찍 피는 벌개미취처럼 바라는 목적이 있다면 게으름을 피우지 않고 구체적으로 계획을 세워 이행하는 편이에요.\n벌개미취가 생명력이 강해 다른 식물들이 시도도 하지 않는 척박한 땅에도 뿌리를 내고 꽃을 피우듯이 열정적이며 호기심이 많아요.\n내성적이며 감정을 직접적으로 드러내지 않는 편이에요.\n비속어를 거의 사용하지 않고 예의범절을 중요시해요."
            flowerGood.setImageResource(R.drawable.entp)
            goodName.text="산수국"
            flowerBad.setImageResource(R.drawable.isfp)
            badName.text="작약"
        }
        if(extra<intro && sensing<iNtuition && thinking>feeling && judging>perceiving){//intj
            MyImage.setImageResource(R.drawable.intj)
            flowerName.text="모란"
            flowerText.text="꽃말 : 부귀, 영화, 왕자의 품격, 행복한 결혼\n부귀의 상징인 모란과 같이 개인의 성공을 중시하며 능력적인 명예를 얻고자 하는 편이에요.\n지적 능력이 높은 편이며, 본인의 목표를 이루기 위해 헌신적으로 행동해요.\n타인보다 개인을 더 중요시하며 이 때문에 다소 이기적이고 오만하게 느껴질 수도 있어요.\n매사를 비판적으로 보는 경향이 있어요."
            flowerGood.setImageResource(R.drawable.enfp)
            goodName.text="동백"
            flowerBad.setImageResource(R.drawable.isfj)
            badName.text="개나리"
        }
        if(extra<intro && sensing>iNtuition && thinking>feeling && judging<perceiving){//istp
            MyImage.setImageResource(R.drawable.istp)
            flowerName.text="꼬리풀"
            flowerText.text="꽃말 : 달성\n줄기가 곧게 서 있는 꼬리풀처럼 고집이 있고 자기 주장이 강해요.\n노력하는 것에 비해 효율이 좋은 편이에요. 이 때문에 벼락치기에 굉장히 능해요.\n그러나 지나치게 효율을 추구하다 보면 매사에 열정을 잃을 수 있으니 주의하는 게 좋겠어요.\n위선이나 가식을 떨지 않고 솔직한 편이에요.\n보통은 조용하지만 필요에 따라 사교적으로 변하기도 해요."
            flowerGood.setImageResource(R.drawable.esfj)
            goodName.text="구절초"
            flowerBad.setImageResource(R.drawable.enfp)
            badName.text="동백"
        }
        if(extra<intro && sensing>iNtuition && thinking<feeling && judging<perceiving){//isfp
            MyImage.setImageResource(R.drawable.isfp)
            flowerName.text="작약"
            flowerText.text="꽃말 : 부끄러움, 수줍음\n꽃의 크기가 크고 다채로운 색상이 있는 작약처럼 예술적인 재능과 감각이 뛰어난 경우가 많아요.\n혼자 있는 것을 좋아하며 다소 게으른 편이에요.\n진한 향기를 풍기는 작약처럼 조용하지만 사람들의 관심을 내심 즐겨요.\n동양에서 미인을 작약에 빗대어 표현하듯이 본인을 잘 꾸미는 편이에요."
            flowerGood.setImageResource(R.drawable.estj)
            goodName.text="초롱꽃"
            flowerBad.setImageResource(R.drawable.infj)
            badName.text="벌개미취"
        }
        if(extra<intro && sensing<iNtuition && thinking<feeling && judging<perceiving){//infp
            MyImage.setImageResource(R.drawable.infp)
            flowerName.text="수련"
            flowerText.text="꽃말 : 담백, 결백, 신비, 꿈, 청정\n피었다가 오므라들기를 반복하는 수련처럼 낯을 많이 가리며, 수줍음이 많은 사람으로 느껴지기도 해요.\n개성을 중시하고, 자신을 다른 사람들과는 다른 특별한 존재라고 생각해요.\n자기애가 강한 편이지만 때때로 굉장히 자기 비판적으로 변해요.\n공상, 몽상을 많이 하며 이 때문인지 매우 창의적이에요.\n진정성과 도덕성을 중시해요."
            flowerGood.setImageResource(R.drawable.enfj)
            goodName.text="민들레"
            flowerBad.setImageResource(R.drawable.estp)
            badName.text="만리화"
        }
        if(extra<intro && sensing<iNtuition && thinking>feeling && judging<perceiving){//intp
            MyImage.setImageResource(R.drawable.intp)
            flowerName.text="조팝"
            flowerText.text="꽃말 : 노련, 노력, 단정한 사랑\n연애에 관심이 없는 경우가 많지만 한 번 사랑에 빠지면 순정파 면모를 보여요.\n작은 꽃들이 다닥다닥 붙어 모여 있는 조팝나무처럼 내성적이고, 내향성이 강하지만 소수의 친한 사람들과는 굉장히 잘 지내요.\n최소한의 노력으로 최대의 이익을 얻고자 하며 결과적으로는 만족할 만한 성과를 얻는 편이에요.\n꿀을 가득 품고 있는 조팝처럼 항상 생각이 많고 아이디어가 뛰어나요. 하지만 머릿속이 너무 복잡해서 이를 표현하는 것에는 약해요."
            flowerGood.setImageResource(R.drawable.entj)
            goodName.text="톱풀"
            flowerBad.setImageResource(R.drawable.esfj)
            badName.text="구절초"
        }
        if(extra>intro && sensing>iNtuition && thinking>feeling && judging<perceiving){//estp
            MyImage.setImageResource(R.drawable.estp)
            flowerName.text="만리화"
            flowerText.text="꽃말 : 용감한 용사, 용사의 모자\n용감한 용사라는 만리화의 꽃말과 같이 도전, 모험적인 욕구가 강하며 스릴을 즐겨요.\n독성이 강한 만리화처럼 말을 직설적으로 하는 경향이 있으며, 다혈질인 경우도 많아요.\n흥미가 있는 분야에는 매우 적극적이고 열정적이에요. 또한 승부욕과 성취욕도 강해요.\n자신감이 넘치고, 자유로움을 추구하며 삶을 즐기는 편이에요."
            flowerGood.setImageResource(R.drawable.isfj)
            goodName.text="개나리"
            flowerBad.setImageResource(R.drawable.infp)
            badName.text="수련"
        }
        if(extra>intro && sensing>iNtuition && thinking<feeling && judging<perceiving){//esfp
            MyImage.setImageResource(R.drawable.esfp)
            flowerName.text="히어리"
            flowerText.text="꽃말 : 봄의노래\n히어리의 꽃말인 봄의 노래처럼 낙천적이며 즐거움을 추구하는 사람이에요.\n주로 산기슭이나 골짜기에 자라는 히어리처럼 대담하며 도전정신이 강해요.\n히어리의 꽃이 여러 개가 모여 달리듯이 사교성이 매우 뛰어나요.\n책임감이 다소 약한 편이며 회피 성향이 있어요."
            flowerGood.setImageResource(R.drawable.istj)
            goodName.text="매화"
            flowerBad.setImageResource(R.drawable.enfj)
            badName.text="민들레"
        }
        if(extra>intro && sensing<iNtuition && thinking<feeling && judging<perceiving){//enfp
            MyImage.setImageResource(R.drawable.enfp)
            flowerName.text="동백"
            flowerText.text="꽃말 : 진실한 사랑, 겸손한 마음, 그대를 누구보다도 사랑합니다\n화려한 동백처럼 꾸미는 것을 좋아하고 관심이 많아요.\n다른 꽃들이 지고 난 겨울에 홀로 피어 사랑을 한 몸에 받는 동백과 같이 사람들의 관심을 매우 즐기지만 혼자만의 시간을 필요로 해요.\n좋아하는 사람과 싫어하는 사람의 구분이 확실하며 상대가 진심으로 대해 주길 바라요.\n감정표현을 잘 하는 것은 좋지만 지나치게 감정적이거나 감정 기복이 심할 수 있으니 주의하는 게 좋겠어요.\n사랑에 빠지면 누구보다 열정적이지만 그만큼 빨리 식기도 하는 편이에요."
            flowerGood.setImageResource(R.drawable.intj)
            goodName.text="모란"
            flowerBad.setImageResource(R.drawable.istp)
            badName.text="꼬리풀"
        }
        if(extra>intro && sensing<iNtuition && thinking>feeling && judging<perceiving){//entp
            MyImage.setImageResource(R.drawable.entp)
            flowerName.text="산수국"
            flowerText.text="꽃말 : 변하기 쉬운 마음\n관심사가 많고 새로운 것에 흥미를 자주 느껴요.\n하지만 끈기가 부족해 대개 금방 싫증 나는 편이에요.\n산수국이 화려하고 큰 위장용 헛꽃으로 벌레들을 유인하듯이 재치있는성격으로 주변 사람들을 잘 이끌어요.\n토양의 산성도에 따라 꽃의 색이 바뀌는 산수국처럼 두뇌 회전이 빠르고 이해력이 뛰어나요."
            flowerGood.setImageResource(R.drawable.infj)
            goodName.text="벌개미취"
            flowerBad.setImageResource(R.drawable.istj)
            badName.text="매화"
        }
        if(extra>intro && sensing>iNtuition && thinking>feeling && judging>perceiving){//estj
            MyImage.setImageResource(R.drawable.estj)
            flowerName.text="초롱꽃"
            flowerText.text="꽃말 : 충실, 정의\n원리, 원칙을 중요시하는 사람이에요.\n거친 털이 있는 초롱꽃처럼 성격이 직설적이고 공감능력이 다소 부족하며, 이로 인해 주변 사람들과 갈등이 생기는 경우가 있어요.\n모든 일을 계획적으로 구상하고 실행하는 편이에요.\n도전정신이 강하며 지도력이 뛰어나 권력자가 될 확률이 높아요."
            flowerGood.setImageResource(R.drawable.isfp)
            goodName.text="작약"
            flowerBad.setImageResource(R.drawable.infp)
            badName.text="수련"
        }
        if(extra>intro && sensing>iNtuition && thinking<feeling && judging>perceiving){//esfj
            MyImage.setImageResource(R.drawable.esfj)
            flowerName.text="구절초"
            flowerText.text="꽃말 : 순수, 어머니의 사랑, 가을 여인\n다른 사람들을 잘 챙기며 정이 많아요.\n그러나 남을 챙기느라 본인을 못 챙길 수도 있으니 주의하는 게 좋겠어요.\n땅 속의 뿌리줄기를 주변으로 뻗어 나가는 구절초처럼 외향성이 매우 강해요.\n하늘을 향해 꽃을 피우는 구절초처럼 긍정적이고 낙천적인 편이에요.\n구절초가 약재로 많이 쓰이듯이 고민 상담을 부탁하는 사람들이 많아요."
            flowerGood.setImageResource(R.drawable.istp)
            goodName.text="꼬리풀"
            flowerBad.setImageResource(R.drawable.infj)
            badName.text="벌개미취"
        }
        if(extra>intro && sensing<iNtuition && thinking<feeling && judging>perceiving){//enfj
            MyImage.setImageResource(R.drawable.enfj)
            flowerName.text="민들레"
            flowerText.text="꽃말 : 행복, 감사하는 마음\n" + "주로 사람들의 손길이 미치는 장소에서 사는 민들레처럼 사람을 굉장히 좋아해요.\n" +"민들레가 열매를 멀리 운반하듯이 오지랖이 조금 넓은 편이에요.\n" + "이타적인 성격으로 따뜻한 마음씨를 가졌어요. 그러나 지나치면 본인이 손해를 볼 수도 있으니 주의하는 게 좋겠어요.\n" + "앞장서서 사람들을 이끄는 것을 좋아해요."
            flowerGood.setImageResource(R.drawable.infp)
            goodName.text="수련"
            flowerBad.setImageResource(R.drawable.esfp)
            badName.text="히어리"
        }
        if(extra>intro && sensing<iNtuition && thinking>feeling && judging>perceiving){//entj
            MyImage.setImageResource(R.drawable.entj)
            flowerName.text="톱풀"
            flowerText.text="꽃말 : 지도, 지도력, 치유\n" + "톱풀의 꽃말처럼 타고난 지도자형이며, 계획적이고 체계적이에요\n" + "잎이 톱날의 모양을 닮은 톱풀처럼 냉철하며, 부조리하고 비합리적인 것에 맞서 싸우는 편이에요.\n" + "자존감이 높고, 자기애도 강해요.\n" + "직설적인 화법으로 다른 사람에게 상처를 줄 수도 있으니 주의하는 게 좋겠어요."
            flowerGood.setImageResource(R.drawable.intp)
            goodName.text="조팝"
            flowerBad.setImageResource(R.drawable.estj)
            badName.text="초롱꽃"
        }
    }
}