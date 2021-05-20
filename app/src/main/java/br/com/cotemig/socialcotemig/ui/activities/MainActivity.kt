package br.com.cotemig.socialcotemig.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.model.Post
import br.com.cotemig.socialcotemig.model.Stories
import br.com.cotemig.socialcotemig.services.RetrofitInitializer
import br.com.cotemig.socialcotemig.ui.adapters.FeedAdapter
import br.com.cotemig.socialcotemig.ui.adapters.StoriesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFeed()
        getStories()
    }

    fun getFeed() {
        var s = RetrofitInitializer().serviceFeed()
        var call = s.getFeed()

        call.enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Feed deu ruim", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        showFeed(it)
                    }
                }
            }
        })
    }

    fun getStories(){
        var s = RetrofitInitializer().serviceStories()
        var call = s.getStories()

        call.enqueue(object: retrofit2.Callback<List<Stories>>{
            override fun onResponse(call: Call<List<Stories>>, response: Response<List<Stories>>) {
                if(response.code() == 200){
                    response.body()?.let{
                        showStories(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Stories>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Stories deu ruim", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showStories(list: List<Stories>){
        stories.adapter = StoriesAdapter(this, list)
        stories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun showFeed(list: List<Post>) {
        var recyclerViewFeed = findViewById<RecyclerView>(R.id.recyclerViewFeed)
        recyclerViewFeed.adapter = FeedAdapter(this, list)
        recyclerViewFeed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}