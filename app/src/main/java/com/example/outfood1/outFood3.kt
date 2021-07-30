package com.example.outfood1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_out_food3.*

class outFood3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_food3)

        // 본 화면에선 1. 리싸이클러뷰의 레이아웃 매니저 할당, 2. 어댑터에 데이터 저장, 3. 데이터가 저장된 어댑터를 리싸이클러뷰의 어댑터에 저장하여 보여줄 수 있도록
        // 이 세 단계를 거친다.
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager // 1. 리싸이클러뷰의 레이아웃 매니저 할당

        val adapter = outFood3ReviewAdapter() // (어댑터 생성)

        adapter.items.add(outFood3Review("익명","4.0","매운 떡볶이 땡기는 날엔 강추합니다! 근데 실내가 너무 덥습니다."))
        adapter.items.add(outFood3Review("익명", "5.0","굉장히 맛있어요! 사장님도 정말 착하십니다!"))
        adapter.items.add(outFood3Review("익명", "3.0","알바생이 부족해보입니다. 웨이팅도 길었구요. 맛은 소문대로 맛있었습니다."))
        adapter.items.add(outFood3Review("익명", "4.5","친구랑 갔는데 둘 다 너무 만족했습니다! 다음에 또 오고 싶어요! 특히 치즈가 맛있습니다~"))
        // 2. 어댑터에 데이터 저장
        // 익명
        recyclerView.adapter = adapter
        // 3. 데이터가 담긴 어댑터를 리싸이클러뷰에 할당
    }
}