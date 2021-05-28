package br.com.cotemig.socialcotemig.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.model.Post
import br.com.cotemig.socialcotemig.model.Stories
import br.com.cotemig.socialcotemig.services.RetrofitInitializer
import br.com.cotemig.socialcotemig.ui.adapters.FeedAdapter
import br.com.cotemig.socialcotemig.ui.adapters.StoriesAdapter
import kotlinx.android.synthetic.main.fragment_feed.*
import retrofit2.Call
import retrofit2.Response

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_feed, container, false)

        getFeed(view)
        getStories(view)

        return view
    }

   companion object{
       fun newInstance() : FeedFragment{
           return FeedFragment()
       }
   }

    fun getFeed(view : View) {
        var s = RetrofitInitializer().serviceFeed()
        var call = s.getFeed()

        call.enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(context, "Feed deu ruim", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        showFeed(view, it)
                    }
                }
            }
        })
    }

    fun getStories(view: View){
        var s = RetrofitInitializer().serviceStories()
        var call = s.getStories()

        call.enqueue(object: retrofit2.Callback<List<Stories>>{
            override fun onResponse(call: Call<List<Stories>>, response: Response<List<Stories>>) {
                if(response.code() == 200){
                    response.body()?.let{
                        showStories(view, it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Stories>>, t: Throwable) {
                Toast.makeText(context, "Stories deu ruim", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showStories(view: View, list: List<Stories>){
        recyclerViewStories.adapter = StoriesAdapter(context!!, list)
        recyclerViewStories.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
    }

    fun showFeed(view : View, list: List<Post>) {
        recyclerViewFeed.adapter = FeedAdapter(context!!, list)
        recyclerViewFeed.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }
}