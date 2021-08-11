package com.example.outfood1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.outfood1.databinding.ActivityInFood2Binding
import com.example.outfood1.youtube.search.data.SearchListResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_in_food2.*
import kotlinx.android.synthetic.main.activity_out_food3.*
import java.net.URLEncoder

class inFood2 : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityInFood2Binding
    companion object {
        var requestQueue : RequestQueue? = null
    }

    var adapter:inFood2YoutubeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInFood2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        val navController = findNavController(R.id.nav_host_fragment_content_in_food2)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        requestQueue = Volley.newRequestQueue(applicationContext)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        adapter?.listener = object : inFood2YoutubeDataListener {
            override fun onItemClick(
                holder: inFood2YoutubeAdapter.ViewHolder?,
                view: View?,
                position: Int
            ) {
                val name = adapter?.items?.get(position)
                println("아이템 선택됨 : $name")

            }
        }

        inFood2SingleRefreshBtn.setOnClickListener {
            requestSearch()
        }

    //        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }




    }

    fun requestSearch() {
        val input = "혼밥 요리"
        val queryString = URLEncoder.encode(input)
        val apiKey = "AIzaSyAfPfPTqI8lsmTRm2zIMRvEoGvkasUQBRY"
        val url = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyAfPfPTqI8lsmTRm2zIMRvEoGvkasUQBRY&q=${queryString}&type=video&regionCode=KR&videoDuration=short&part=snippet"

        val request = StringRequest(
            Request.Method.GET,
            url,
            {println("\n응답 -> ${println()}")

            processResponse(it)},
            {
                println("\n에러 -> ${println()}")
            }
        )

        request.setShouldCache(false)
        requestQueue?.add(request)
        println("\n유튜브 검색 요청함")

    }

    fun processResponse(response:String) {
        val gson = Gson()
        val searchList = gson.fromJson(response, SearchListResponse::class.java)
        println("검색 개수 : ${searchList.items.size}")

        adapter?.items?.clear()
        searchList.items.forEach {
            val youtubeVideo
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_in_food2)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}