package com.example.nitaioanmadalinassessment.ui.articlefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.nitaioanmadalinassessment.R
import com.example.nitaioanmadalinassessment.ui.data.models.articles.Article

class ArticleFragment : Fragment() {

    private val TAG = this::class.java.simpleName
    private val args: ArticleFragmentArgs by navArgs()
    private var currentArticle: Article ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentArticle = args.currentArticle
        Log.d(TAG,"Article selected: $currentArticle")
    }

    companion object {
        @JvmStatic
        fun newInstance() = ArticleFragment()
    }
}