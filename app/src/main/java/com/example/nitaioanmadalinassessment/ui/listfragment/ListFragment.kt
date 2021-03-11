package com.example.nitaioanmadalinassessment.ui.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nitaioanmadalinassessment.R
import com.example.nitaioanmadalinassessment.ui.data.models.Article
import com.example.nitaioanmadalinassessment.ui.listfragment.adapter.ItemClickedCallback
import com.example.nitaioanmadalinassessment.ui.listfragment.adapter.ListArticlesAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(context)
        list_container.layoutManager = linearLayoutManager
        list_container.adapter =
            ListArticlesAdapter(requireContext(), emptyList(), object : ItemClickedCallback {
                override fun selectedArticle(article: Article) {

                }
            })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}