package br.com.cotemig.socialcotemig.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.model.Post
import br.com.cotemig.socialcotemig.model.Stories
import br.com.cotemig.socialcotemig.services.RetrofitInitializer
import br.com.cotemig.socialcotemig.ui.adapters.FeedAdapter
import br.com.cotemig.socialcotemig.ui.adapters.StoriesAdapter
import br.com.cotemig.socialcotemig.ui.fragment.FeedFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(FeedFragment.newInstance())

    }

    fun setFragment(fragment: Fragment) {
        var ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.content, fragment)
        ft.commit()
    }

}