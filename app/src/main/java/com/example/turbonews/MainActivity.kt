package com.example.turbonews

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var mAdapter: NewsListAdapter
    var url ="https://newsapi.org/v2/top-headlines?country=in&apiKey=ba37e8b185a24834837c12e258b4dfff"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter(this)
        recyclerView.adapter = mAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.newmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.about -> {
                val intent= Intent(this,AboutMe::class.java)
                startActivity(intent)
                true
            }
        else-> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchData() {
        //volley library
        //making a request
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>() //Array list me vo objects hai jo jo chahiye for ease
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i) //json array se json objectt get kia
                    val news = News( //fir alg se ek set variable me har part ke componenets daldi/e
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }

                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            }

        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

    fun all(view: View) {
        Toast.makeText(this,"You are in All Category Now",Toast.LENGTH_SHORT).show()
        url ="https://newsapi.org/v2/top-headlines?country=in&apiKey=ba37e8b185a24834837c12e258b4dfff"
        fetchData()
    }

    fun foreign(view: View) {
        Toast.makeText(this,"You are in Foreign Category Now",Toast.LENGTH_SHORT).show()
        url ="https://newsapi.org/v2/top-headlines?country=us&apiKey=ba37e8b185a24834837c12e258b4dfff"
        fetchData()
    }

    fun sports(view: View) {
        Toast.makeText(this,"You are in Sports Category Now",Toast.LENGTH_SHORT).show()
        url ="https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=eeb27aeb783b47eb9527ea7cf485f7c3"
        fetchData()
    }

    fun entertainment(view: View) {
        Toast.makeText(this,"You are in Entertainment Category Now",Toast.LENGTH_SHORT).show()
        url ="https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=eeb27aeb783b47eb9527ea7cf485f7c3"
        fetchData()
    }
    fun science(view: View) {
        Toast.makeText(this,"You are in Science Category Now",Toast.LENGTH_SHORT).show()
        url ="https://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=eeb27aeb783b47eb9527ea7cf485f7c3"
        fetchData()
    }
    fun tech(view: View) {
        Toast.makeText(this,"You are in Technology Category Now",Toast.LENGTH_SHORT).show()
        url ="https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=eeb27aeb783b47eb9527ea7cf485f7c3"
        fetchData()
    }
    fun health(view: View) {
        Toast.makeText(this,"You are in Health Category Now",Toast.LENGTH_SHORT).show()
        url ="https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=eeb27aeb783b47eb9527ea7cf485f7c3"
        fetchData()
    }
    fun business(view: View) {
        Toast.makeText(this,"You are in Business Category Now",Toast.LENGTH_SHORT).show()
        url ="https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=eeb27aeb783b47eb9527ea7cf485f7c3"
        fetchData()
    }
}