package br.com.cotemig.socialcotemig.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.model.Post
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedAdapter (var context : Context, var list : List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostHolder).bind(context, list[position])
    }

    class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, post: Post) {

            var user_photo = itemView.findViewById<ImageView>(R.id.user_photo)
            user_photo.load(post.avatar) {
                transformations(RoundedCornersTransformation(50f))
            }

            //Glide.with(context).load(itemView.user_photo).into(user_photo)

            var user_name = itemView.findViewById<TextView>(R.id.user_name)
            user_name.text = post.user

            var time_post = itemView.findViewById<TextView>(R.id.time_post)
            time_post.text = post.date

            var string_location = itemView.findViewById<TextView>(R.id.string_location)
            string_location.text = post.local

            var gallery = itemView.findViewById<RecyclerView>(R.id.gallery)
            gallery.adapter = GalleryAdapter(context, post.gallery)
            gallery.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            var snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(gallery)

            gallery.onFlingListener = null

            var like_number = itemView.findViewById<TextView>(R.id.like_number)
            like_number.text = post.likes.size.toString()

            var string_post = itemView.findViewById<TextView>(R.id.string_post)
            string_post.text = post.description

        }
    }
}