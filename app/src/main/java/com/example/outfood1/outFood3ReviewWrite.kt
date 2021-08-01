package com.example.outfood1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_out_food3_reviewwrite.*

class outFood3ReviewWrite : AppCompatActivity() {

//    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_food3_reviewwrite)

//        outFood3ReviewWriteSave.setOnClickListener {
//            val name = outFood3ReviewWriteName.text.toString()
//            val rating = outFoodStar.rating.toString().toLong()
//            val contents = outFood3ReviewWriteCont.text.toString()
//
//            saveComment(name, rating ,contents)
//        }
//
//    }

//    fun saveComment (name:String, rating:Long, contents:String) {
//        val key:String? = databaseRef.child("comments").push().key
//        val comment = outFood3Review(key!!, name, rating, contents)
//        // 책에서 쓴 objectid는 객체의 key를 의미하는 것이었음. 우리도 객체를 구분할 키값을 담을 데이터 인수를 하나 더 설정해야 함
//        // 그래서 setitem에서도 objectid에 대한 언급이 없었던 것.
//        val commentValues: HashMap<String?, Any?> = comment.toMap()
//
//        val childUpdates: MutableMap<String, Any> = HashMap()
//        childUpdates["/comments/$key"] = commentValues
//
//        databaseRef.updateChildren(childUpdates)
//
//        databaseRef = FirebaseDatabase.getInstance().reference


//    }
    }
}