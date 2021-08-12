package com.example.outfood1
//사용하지 않음


import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_out_food3_reviewwrite.*

open class outFood3ReviewWrite : AppCompatActivity() {

    private lateinit var databaseRef: DatabaseReference
    private lateinit var adapter: outFood3ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_food3_review_write)

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
        val commentValues: java.util.HashMap<String?, Any?> = comment.toMap()

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
}