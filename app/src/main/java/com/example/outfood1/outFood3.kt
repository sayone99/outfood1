package com.example.outfood1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_out_food3.*
import kotlinx.android.synthetic.main.activity_out_food3.outFood3ReviewWriteCont
import kotlinx.android.synthetic.main.activity_out_food3.outFood3ReviewWriteName
import kotlinx.android.synthetic.main.activity_out_food3.outFood3ReviewWriteSave
import kotlinx.android.synthetic.main.activity_out_food3.outFoodStarInput
import kotlinx.android.synthetic.main.activity_out_food3_reviewwrite.*

class outFood3 : AppCompatActivity() {


    private lateinit var databaseRef: DatabaseReference
    private lateinit var adapter: outFood3ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_food3)

        // 본 화면에선 1. 리싸이클러뷰의 레이아웃 매니저 할당, 2. 어댑터에 데이터 저장, 3. 데이터가 저장된 어댑터를 리싸이클러뷰의 어댑터에 저장하여 보여줄 수 있도록
        // 이 세 단계를 거친다.
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager // 1. 리싸이클러뷰의 레이아웃 매니저 할당

        val adapter = outFood3ReviewAdapter() // (어댑터 생성)

        adapter.items.add(outFood3Review("0", "익명", 4, "매운 떡볶이 땡기는 날엔 강추합니다! 근데 실내가 너무 덥습니다."))
        adapter.items.add(outFood3Review("1", "익명", 5, "굉장히 맛있어요! 사장님도 정말 착하십니다!"))
        adapter.items.add(outFood3Review("2", "익명", 3, "알바생이 부족해보입니다. 웨이팅도 길었구요. 맛은 소문대로 맛있었습니다."))
        adapter.items.add(outFood3Review("3", "익명", 4, "친구랑 갔는데 둘 다 너무 만족했습니다! 다음에 또 오고 싶어요! 특히 치즈가 맛있습니다~"))
        // 2. 어댑터에 데이터 저장
        // 익명

        recyclerView.adapter = adapter
        // 3. 데이터가 담긴 어댑터를 리싸이클러뷰에 할당

//        outFood3ReviewBtn.setOnClickListener {
//            val intent = Intent(this, outFood3ReviewWrite::class.java)
//            startActivity(intent)
//            println("버튼 잘 작동")
//        }

        // outfood3pager
        outFood3Pager.adapter = PagerAdapter(this)
        outFood3Pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        outFood3Pager.offscreenPageLimit = 4

        outFood3Indicator.setViewPager(outFood3Pager);
        outFood3Indicator.createIndicators(4,0);

        outFood3Pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                println("${position + 1} 페이지 선택됨")

                outFood3Indicator.animatePageSelected(position)
            }
        })

        outFood3ReviewWriteSave.setOnClickListener {
            val name = outFood3ReviewWriteName.text.toString()
            val rating = outFoodStarInput.rating.toLong()
            val contents = outFood3ReviewWriteCont.text.toString()

            saveComment(name, rating ,contents)
            println("저장한 데이터 : ${name}, $rating, $contents}")
        }

        databaseRef = FirebaseDatabase.getInstance().reference

    }

    fun saveComment (name:String, rating:Long, contents:String) {
        val key: String? = databaseRef.child("comments").push().key
        val comment = outFood3Review(key!!, name, rating, contents)
        // 책에서 쓴 objectid는 객체의 key를 의미하는 것이었음. 우리도 객체를 구분할 키값을 담을 데이터 인수를 하나 더 설정해야 함
        // 그래서 setitem에서도 objectid에 대한 언급이 없었던 것.
        val commentValues: HashMap<String?, Any?> = comment.toMap()

        val childUpdates: MutableMap<String, Any> = HashMap()
        childUpdates["/comments/$key"] = commentValues

        databaseRef.updateChildren(childUpdates)

        databaseRef.orderByKey().limitToFirst(10)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    loadCommentList(dataSnapshot)
                }

                override fun onCancelled(databaseerror: DatabaseError) {
                    println("loadItem:onCancelled : ${databaseerror.toException()}")
                }
            })


    }


    fun loadCommentList(dataSnapshot: DataSnapshot) {
        val collectionIterator = dataSnapshot.children.iterator()
        if (collectionIterator.hasNext()) {
            adapter.items.clear()

            val comments = collectionIterator.next()
            val itemsIterator = comments.children.iterator()
            while (itemsIterator.hasNext()) {
                val currentItem = itemsIterator.next()

                val key = currentItem.key

                val map = currentItem.getValue() as HashMap<String, Any>
                val objectId = map.get("objectId") as String
                val name = map.get("name") as String
                val rating = map.get("rating") as Long
                val contents = map.get("contetns") as String

                adapter.items.add(outFood3Review(objectId, name, rating, contents))
            }

            adapter.notifyDataSetChanged()
            println("파이어베이스에서 가져옴")
        }
    }



    inner class PagerAdapter : FragmentStateAdapter {
        constructor(activity: FragmentActivity) : super(activity)

        override fun getItemCount() = 4

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> {
                    return Fragment1()
                }
                1 -> {
                    return Fragment2()
                }
                2 -> {
                    return Fragment3()
                }
                3 -> {
                    return Fragment4()
                }
            }
            return Fragment1()
        }
    }
}
