package com.example.nitaioanmadalinassessment.ui.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nitaioanmadalinassessment.R
import com.example.nitaioanmadalinassessment.data.api.ApiHelper
import com.example.nitaioanmadalinassessment.data.api.RetrofitBuilder
import com.example.nitaioanmadalinassessment.data.models.articles.Article
import com.example.nitaioanmadalinassessment.data.utils.CallStatus
import com.example.nitaioanmadalinassessment.ui.listfragment.adapter.ItemClickedCallback
import com.example.nitaioanmadalinassessment.ui.listfragment.adapter.ListArticlesAdapter
import com.example.nitaioanmadalinassessment.ui.main.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

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

        sharedViewModel.showBackArrowAsToolbar.postValue(false)
        viewModel.getAllArticlesNow("bitcoin")
        sharedViewModel.newCategory.observe(viewLifecycleOwner, {
            viewModel.getAllArticlesNow(it)
        })
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

        list_container.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(linearLayoutManager.findFirstVisibleItemPosition() >= 1) {
                    sharedViewModel.recyclerViewIsScrolled.postValue(true)
                } else {
                    sharedViewModel.recyclerViewIsScrolled.postValue(false)
                }
            }
        })

    }

    private fun setupObserver() {
        viewModel.articlesResponse.observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    CallStatus.SUCCESS -> {
                        shimmer_view_container.stopShimmer()
                        list_container.visibility = View.VISIBLE
                        shimmer_view_container.visibility = View.GONE
                        resource.data?.let { articleResponse -> retrieveList(articleResponse.articles) }
                    }
                    CallStatus.ERROR -> {
                        shimmer_view_container.stopShimmer()
                        list_container.visibility = View.VISIBLE
                        shimmer_view_container.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    CallStatus.LOADING -> {
                        list_container.visibility = View.GONE
                        shimmer_view_container.visibility = View.VISIBLE
                        shimmer_view_container.startShimmer()
                    }
                }
            }
        })
    }

    private fun retrieveList(articles: List<Article>) {
        list_container.smoothScrollToPosition(0)
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