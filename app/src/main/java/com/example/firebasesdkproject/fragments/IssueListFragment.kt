package com.example.firebasesdkproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasesdkproject.R
import com.example.firebasesdkproject.adapter.IssueListAdapter
import com.example.firebasesdkproject.model.IssueModelItem
import com.example.firebasesdkproject.viewmodel.MainViewModel


class IssueListFragment : Fragment {
    private lateinit var issueListAdapter: IssueListAdapter
    private var mainViewModel: MainViewModel? = null


    constructor() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = LayoutInflater.from(context).inflate(R.layout.fragment_issue_list, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.issue_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        issueListAdapter = IssueListAdapter()
        recyclerView.adapter = issueListAdapter
        return root
    }

//    private val recipeClickListener: ItemClickListener<RecipeEntity> =
//        object : ItemClickListener<RecipeEntity?>() {
//            fun onClick(recipe: RecipeEntity?) {
//                recipeListViewModel.stopStreaming()
//                (activity as MainActivity?).show(recipe)
//            }
//        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel!!.getAllList.observe(this, Observer { list ->
            issueListAdapter?.setIssueList(list as List<IssueModelItem?>?)
        })
    }
}