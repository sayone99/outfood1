package com.example.outfood1

import android.os.Bundle
import android.view.View
import androidx.activity.R
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
import com.example.outfood1.databinding.ActivityInFood2SingleYoutubeBinding
import com.example.outfood1.youtube.search.data.SearchListResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_in_food2_single_youtube.*
import kotlinx.android.synthetic.main.activity_out_food3.*
import java.net.URLEncoder

class inFood2Single : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityInFood2SingleYoutubeBinding

    companion object {
        var requestQueue : RequestQueue? = null
    }

    var adapter:inFood2YoutubeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInFood2SingleYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)






        requestQueue = Volley.newRequestQueue(applicationContext)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

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

        binding.inFood2SingleRefreshBtn.setOnClickListener {
            requestSearch()
        }




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
            val youtubeVideo = inFood2YoutubeData(it.id?.videoId,
                it.snippet?.publishedAt, it.snippet?.title, it.snippet?.description, it.snippet?.thumbnails?.medium?.url)

            adapter?.items?.add(youtubeVideo)
        }

        adapter?.notifyDataSetChanged()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}