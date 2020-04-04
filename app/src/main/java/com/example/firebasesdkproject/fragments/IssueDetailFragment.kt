package com.example.firebasesdkproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasesdkproject.R
import com.example.firebasesdkproject.adapter.IssueDetailAdapter
import com.example.firebasesdkproject.model.CommentModelItem
import com.example.firebasesdkproject.viewmodel.DetailListingViewModel

class IssueDetailFragment : Fragment {
    private lateinit var detailListAdapter: IssueDetailAdapter
    private var detailListViewModel: DetailListingViewModel? = null

    constructor() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            LayoutInflater.from(context).inflate(R.layout.fragment_detail_issue, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.detail_list_recycler_view)
        detailListAdapter = IssueDetailAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = detailListAdapter
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = arguments?.getString(COMMENTS_URL)?.let { DetailListingViewModel.Factory(it) }
        detailListViewModel =
            ViewModelProviders.of(this, factory).get(DetailListingViewModel::class.java)
        detailListViewModel!!.getCommentList.observe(this, Observer { list ->
            detailListAdapter.setCommentList(list as List<CommentModelItem?>?)
        })
    }

    companion object {
        private val ISSUE_ID_KEY = "issue_id"
        private val COMMENTS_URL = "COMMENTS_URL"
        @JvmStatic
        fun bundleData(id: Int?, commentURL : String?): IssueDetailFragment {
            val fragment = IssueDetailFragment()
            val args = Bundle()
            if (id != null) {
                args.putInt(ISSUE_ID_KEY, id)
                args.putString(COMMENTS_URL, commentURL)
            }
            fragment.arguments = args
            return fragment

        }
    }
}