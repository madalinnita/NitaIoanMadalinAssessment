package com.example.nitaioanmadalinassessment.ui.articlefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.nitaioanmadalinassessment.R
import com.example.nitaioanmadalinassessment.data.models.articles.Article
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment() {

    private val TAG = this::class.java.simpleName
    private val args: ArticleFragmentArgs by navArgs()
    private var currentArticle: Article? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(args.currentArticle)
        Log.d(TAG, "Article selected: $currentArticle")
    }

    private fun setupUi(article: Article) {
        Glide.with(requireContext())
            .load(article.urlToImage)
            .into(imageView)
        country_and_domain_textView.text =
            getString(R.string.country_and_domain, article.author, article.source.name)
        title_textView.text = article.title
        description_textView.text = article.description
    }

    companion object {
        @JvmStatic
        fun newInstance() = ArticleFragment()
    }
}