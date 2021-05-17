package br.com.cotemig.socialcotemig.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.model.Image
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_feed.view.*
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryAdapter (var context: Context, var list: List<Image>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false)
        return GalleryHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GalleryHolder).bind(context, list[position])
    }

    class GalleryHolder(var view : View) : RecyclerView.ViewHolder(view){
        fun bind(context: Context, postImage : Image){

            var image = view.findViewById<ImageView>(R.id.postImage)
            Glide.with(context).load(itemView.postImage).into(image)
        }
    }
}