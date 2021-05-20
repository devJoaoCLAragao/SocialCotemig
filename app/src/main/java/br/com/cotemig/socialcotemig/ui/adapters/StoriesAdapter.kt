package br.com.cotemig.socialcotemig.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.persistableBundleOf
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.model.Stories
import coil.load
import coil.transform.RoundedCornersTransformation

class StoriesAdapter(var context: Context, var list: List<Stories>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0){
            var view = LayoutInflater.from(context).inflate(R.layout.item_stories, parent, false)
            return CameraStoriesHolder(view)
        }else{
            var view = LayoutInflater.from(context).inflate(R.layout.item_stories, parent, false)
            return OtherStoriesHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        } else {
            return 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as CameraStoriesHolder).bind()
        } else {
            (holder as OtherStoriesHolder).bind(context, list[position-1])
        }
    }

    class CameraStoriesHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {

            var photo_stories = view.findViewById<ImageView>(R.id.photo_stories)
            photo_stories.load(R.drawable.stories_camera) {
                transformations(RoundedCornersTransformation(50f))
            }
            photo_stories.setPadding(0)

            var live = view.findViewById<ImageView>(R.id.live)
            live.visibility = View.GONE

        }
    }

    class OtherStoriesHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(context: Context, stories: Stories) {

            var photo_stories = view.findViewById<ImageView>(R.id.photo_stories)
            photo_stories.load(stories.avatar) {
                transformations(RoundedCornersTransformation(50f))
            }

            var live = view.findViewById<ImageView>(R.id.live)
            if (stories.live == true){
                live.visibility = View.VISIBLE
            } else{
                live.visibility = View.GONE
            }
        }
    }
}