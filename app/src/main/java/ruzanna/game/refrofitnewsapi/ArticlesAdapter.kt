package ruzanna.game.refrofitnewsapi

import android.util.Log
import kotlinx.android.synthetic.main.row.view.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    var articlesList:List<Article> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.init(articlesList[position])
        holder.itemView.setOnClickListener {
            Log.i("test", "tsaghjk")
        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun init(article: Article){
            itemView.apply {
                titleTV.text = article.title
                descriptionTV.text = article.description
                Glide.with(context)
                        .load(article.urlToImage)
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .centerCrop()
                        .into(imageView)
            }
        }

    }
}