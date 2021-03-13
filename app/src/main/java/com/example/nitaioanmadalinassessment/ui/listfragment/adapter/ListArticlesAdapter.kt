package com.example.nitaioanmadalinassessment.ui.listfragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nitaioanmadalinassessment.R
import com.example.nitaioanmadalinassessment.data.models.articles.Article
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ListArticlesAdapter(
    private val context: Context,
    private var articles: List<Article>,
    private val listener: ItemClickedCallback?
) : RecyclerView.Adapter<ListArticlesAdapter.ListViewHolder>() {

    private val FIRST_ITEM = 0
    private val OTHER_ITEMS = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = if (viewType == FIRST_ITEM) LayoutInflater.from(context)
            .inflate(R.layout.item_articles_first_item, parent, false) else LayoutInflater.from(
            context
        ).inflate(R.layout.item_articles, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = articles[position]
        holder.hour.text = getDate(article.publishedAt)
        holder.title.text = article.title

        Glide.with(context)
            .load(article.urlToImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun getItemViewType(position: Int): Int =
        if (position == 0) FIRST_ITEM else OTHER_ITEMS

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_container: ConstraintLayout = itemView.findViewById(R.id.item_container)
        val hour: TextView = itemView.findViewById(R.id.hours_textView)
        val title: TextView = itemView.findViewById(R.id.title_textView)
        val image: ImageView = itemView.findViewById(R.id.imageView)

        init {
            item_container.setOnClickListener {
                listener?.selectedArticle(articles[adapterPosition])
                notifyDataSetChanged()
            }
        }
    }

    fun updateList(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    private fun getDate(date: String): String {
        val publishedAt =
            SimpleDateFormat(context.getString(R.string.date_format), Locale.UK).parse(date)!!.time
        val hours =
            (TimeUnit.MILLISECONDS.toHours(getCurrentDateTime()) - TimeUnit.MILLISECONDS.toHours(
                publishedAt
            ))
        val days = (hours / 24).toInt()
        return if (hours > 24 && (hours - days * 24).toInt() != 0) {
            context.getString(R.string.header_hours_with_days, days, hours - days * 24)
        } else if (hours > 24) {
            context.getString(R.string.header_days, days)
        } else {
            context.getString(R.string.header_hours, hours)
        }
    }

    private fun getCurrentDateTime(): Long {
        return Calendar.getInstance().time.time
    }
}