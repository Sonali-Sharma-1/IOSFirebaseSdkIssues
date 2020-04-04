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
import com.example.firebasesdkproject.ItemClickListener
import com.example.firebasesdkproject.MainActivity
import com.example.firebasesdkproject.R
import com.example.firebasesdkproject.adapter.IssueListAdapter
import com.example.firebasesdkproject.model.IssueModelItem
import com.example.firebasesdkproject.viewmodel.IssueListingViewModel


class IssueListFragment : Fragment {
    private lateinit var issueListAdapter: IssueListAdapter
    private var issueListingViewModel: IssueListingViewModel? = null


    constructor() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = LayoutInflater.from(context).inflate(R.layout.fragment_issue_list, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.issue_list_recycler_view)
        issueListAdapter = IssueListAdapter(itemClickListener)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = issueListAdapter
        return root
    }

    private val itemClickListener: ItemClickListener = object : ItemClickListener {
        override fun onClick(issue: IssueModelItem) {
            (activity as MainActivity).detailView(issue)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        issueListingViewModel = ViewModelProviders.of(this).get(IssueListingViewModel::class.java)
        issueListingViewModel!!.getAllList.observe(this, Observer { list ->
            issueListAdapter?.setIssueList(list as List<IssueModelItem?>?)
        })
    }
}