package com.example.nitaioanmadalinassessment.ui.unrditemsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.nitaioanmadalinassessment.R
import com.example.nitaioanmadalinassessment.data.api.ApiHelper
import com.example.nitaioanmadalinassessment.data.api.RetrofitBuilder
import com.example.nitaioanmadalinassessment.data.utils.CallStatus
import com.example.nitaioanmadalinassessment.ui.listfragment.ListFragmentViewModel
import com.example.nitaioanmadalinassessment.ui.listfragment.ListFragmentViewModelFactory
import com.example.nitaioanmadalinassessment.ui.main.SharedViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_unrd_items.*


class UnrdItemsFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val viewModel: UnrdFragmentViewModel by viewModels {
        UnrdFragmentViewModelFactory(
            ApiHelper(RetrofitBuilder.apiUnrdService)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_unrd_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.showBackArrowAsToolbar.postValue(true)
        viewModel.getUnrdItemsNow()
        observeUnrdItems()
    }

    private fun observeUnrdItems(){
        viewModel.unrdItemsResponse.observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    CallStatus.SUCCESS -> {
                        receiver_textview.text = it.toString()
                        progressBar.visibility = View.GONE
                        progress_textView.visibility = View.GONE
                    }
                    CallStatus.ERROR -> {
                        progressBar.visibility = View.GONE
                        progress_textView.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        receiver_textview.text = it.message
                    }
                    CallStatus.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        progress_textView.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = UnrdItemsFragment()
    }
}