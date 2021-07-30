package com.example.outfood1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class outFood1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OutFood1SingleBtn.setOnClickListener {
            val intent = Intent(this, outFood3::class.java)
            startActivityForResult(intent,101)

        } // (혼밥 버튼 클릭시) 화면 전환 소스코드

        OutFood1FlexBtn.setOnClickListener {
            val intent = Intent(this, outFood3::class.java)
            startActivityForResult(intent,101)

        } // (플렉스 버튼 클릭시) 화면 전환 소스코드

        OutFood1ParBtn.setOnClickListener {
            val intent = Intent(this, outFood3::class.java)
            startActivityForResult(intent,101)

        } // (부모님 버튼 클릭시) 화면 전환 소스코드

        OutFood1VegBtn.setOnClickListener {
            val intent = Intent(this, outFood3::class.java)
            startActivityForResult(intent,101)

        } // (부모님 버튼 클릭시) 화면 전환 소스코드


        imageView4.setOnClickListener {
            finish()
            // 서랍 넣으면 이 코드에서 imageView4 부분을 서랍 누를 햄버거 메뉴로 바꾸면 될듯
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            101 -> println("사라진 액티비티로부터의 응답, ${requestCode}, ${resultCode}")

        }

    }



}
