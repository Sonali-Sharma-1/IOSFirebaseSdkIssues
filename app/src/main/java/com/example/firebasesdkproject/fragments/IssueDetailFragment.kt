package com.example.firebasesdkproject.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasesdkproject.R
import com.example.firebasesdkproject.adapter.IssueDetailAdapter
import com.example.firebasesdkproject.database.RoomDb
import com.example.firebasesdkproject.model.CommentModelItem
import com.example.firebasesdkproject.model.IssueModelItem
import com.example.firebasesdkproject.viewmodel.DetailListingViewModel
import kotlinx.android.synthetic.main.fragment_detail_issue.*
import java.lang.Exception

class IssueDetailFragment : Fragment {
    private lateinit var detailListAdapter: IssueDetailAdapter
    private var detailListViewModel: DetailListingViewModel? = null
    val roomDb: RoomDb = RoomDb.getInstance()

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
        progress_bar.visibility = VISIBLE
        val factory = arguments?.getString(COMMENTS_URL)?.let {
            DetailListingViewModel.Factory(it, arguments?.getInt(ISSUE_ID_KEY))
        }

        if (isNetworkAvailable()) {
            detailListViewModel =
                ViewModelProviders.of(this, factory).get(DetailListingViewModel::class.java)
            detailListViewModel!!.getCommentList.observe(this, Observer { list ->
                if (list.size > 0) {
                    progress_bar.visibility = GONE
                    detail_list_recycler_view.visibility = VISIBLE
                    detailListAdapter.setCommentList(list as List<CommentModelItem?>?)
                } else {
                    progress_bar.visibility = GONE
                    detail_list_recycler_view.visibility = GONE
                    no_data_found_layout.visibility = VISIBLE
                }
            })
        } else {
            var list: LiveData<List<CommentModelItem>>? = fetchCommentsFromDb()
            if (list != null) {
                progress_bar.visibility = GONE
                detail_list_recycler_view.visibility = VISIBLE
                detailListAdapter.setCommentList(list as List<CommentModelItem?>?)
            } else {
                progress_bar.visibility = GONE
                detail_list_recycler_view.visibility = GONE
                no_data_found_layout.visibility = VISIBLE
            }
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

    companion object {
        private val ISSUE_ID_KEY = "issue_id"
        private val COMMENTS_URL = "COMMENTS_URL"
        @JvmStatic
        fun bundleData(id: Int?, commentURL: String?): IssueDetailFragment {
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

    fun fetchCommentsFromDb(): LiveData<List<CommentModelItem>>? {
        var list: LiveData<List<CommentModelItem>>? = null
        try {
            AsyncTask.execute {
                list = roomDb.issueDao().getCommentListing()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}