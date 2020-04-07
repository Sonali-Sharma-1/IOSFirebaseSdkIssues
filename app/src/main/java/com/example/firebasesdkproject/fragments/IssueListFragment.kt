package com.example.firebasesdkproject.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
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
import com.example.firebasesdkproject.database.RoomDb
import com.example.firebasesdkproject.model.IssueModelItem
import com.example.firebasesdkproject.viewmodel.IssueListingViewModel
import java.lang.Exception


class IssueListFragment : Fragment {
    private lateinit var issueListAdapter: IssueListAdapter
    private var issueListingViewModel: IssueListingViewModel? = null
    val roomDb: RoomDb = RoomDb.getInstance()

    constructor() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            LayoutInflater.from(context).inflate(R.layout.fragment_issue_list, container, false)
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
        if (isNetworkAvailable()) {
            issueListingViewModel =
                ViewModelProviders.of(this).get(IssueListingViewModel::class.java)
            issueListingViewModel!!.getAllList.observe(this, Observer { list ->
                issueListAdapter.setIssueList(list as List<IssueModelItem?>?)
            })
        } else {
            var list: List<IssueModelItem>?  = fetchIssueListingFromDb()
            issueListAdapter.setIssueList(list as List<IssueModelItem?>?)
        }
    }

    fun isNetworkAvailable(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val n = cm.activeNetwork
            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)
                //It will check for both wifi and cellular network
                return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )
            }
            return false
        } else {
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }

    fun fetchIssueListingFromDb(): List<IssueModelItem>? {
        var list: List<IssueModelItem> ?= null
        try {
            AsyncTask.execute {
                list = roomDb.issueDao().getAllIssuesListing()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}