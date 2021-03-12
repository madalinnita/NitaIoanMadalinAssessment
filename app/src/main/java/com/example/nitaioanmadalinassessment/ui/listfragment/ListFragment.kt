package com.example.nitaioanmadalinassessment.ui.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nitaioanmadalinassessment.R
import com.example.nitaioanmadalinassessment.ui.articlefragment.ArticleFragment
import com.example.nitaioanmadalinassessment.ui.data.api.ApiHelper
import com.example.nitaioanmadalinassessment.ui.data.api.RetrofitBuilder
import com.example.nitaioanmadalinassessment.ui.data.models.articles.Article
import com.example.nitaioanmadalinassessment.ui.data.utils.CallStatus
import com.example.nitaioanmadalinassessment.ui.listfragment.adapter.ItemClickedCallback
import com.example.nitaioanmadalinassessment.ui.listfragment.adapter.ListArticlesAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val viewModel: ListFragmentViewModel by viewModels {
        ListFragmentViewModelFactory(
            ApiHelper(RetrofitBuilder.apiService)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        setupObserver()
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(context)
        list_container.layoutManager = linearLayoutManager
        list_container.addItemDecoration(
            DividerItemDecoration(
                list_container.context,
                (list_container.layoutManager as LinearLayoutManager).orientation
            ).apply {
                setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.layer_divider)!!)
            }
        )
        list_container.adapter =
            ListArticlesAdapter(requireContext(), emptyList(), object : ItemClickedCallback {
                override fun selectedArticle(article: Article) {
                    val action = ListFragmentDirections.actionListFragmentToArticleFragment(article)
                    findNavController().navigate(action);
                }
            })
    }

    private fun setupObserver() {
        viewModel.getAllArticles().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    CallStatus.SUCCESS -> {
                        shimmer_view_container.stopShimmer()
                        shimmer_view_container.visibility = View.GONE
                        resource.data?.let { articleResponse -> retrieveList(articleResponse.articles) }
                    }
                    CallStatus.ERROR -> {
                        shimmer_view_container.stopShimmer()
                        shimmer_view_container.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    CallStatus.LOADING -> {
                        shimmer_view_container.visibility = View.VISIBLE
                        shimmer_view_container.startShimmer()
                    }
                }
            }
        })
    }

    private fun retrieveList(articles: List<Article>) {
        (list_container.adapter as ListArticlesAdapter).updateList(
            articles.sortedWith(
                Comparator.comparing(
                    Article::publishedAt
                )
            ).reversed()
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}