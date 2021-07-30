package com.example.outfood1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_out_food3_reviewwrite.*

class outFood3ReviewWrite : AppCompatActivity() {

    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_food3_reviewwrite)

        outFood3ReviewWriteSave.setOnClickListener {
            val name = outFood3ReviewWriteName.text.toString()
            val rating = outFoodStar.rating.toString()
            val contents = outFood3ReviewWriteCont.text.toString()

            saveComment(name,rating,contents)
        }

        fun saveComment (name:String, rating:Long,contents:String) {
            val key:String? = databaseRef.child("comments").push().getKey()
            val comment = FoodComment(key!!, name, rating, contents)
            val commentValues: HashMap<String, Any> = comment.toMap()

            val childUpdates: MutableMap<String, Any> = HashMap()
            childUpdates["/comments/$key"] = commentValues

            databaseRef.updateChildren(childUpdates)

        databaseRef = FirebaseDatabase.getInstance().reference




    }



    }
}