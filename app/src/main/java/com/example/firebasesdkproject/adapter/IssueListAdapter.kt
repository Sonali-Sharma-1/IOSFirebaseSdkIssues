package com.example.firebasesdkproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasesdkproject.ItemClickListener
import com.example.firebasesdkproject.R
import com.example.firebasesdkproject.databinding.IssueListItemBinding
import com.example.firebasesdkproject.model.IssueModel
import com.example.firebasesdkproject.model.IssueModelItem

 class IssueListAdapter(private val clickListener: ItemClickListener) : RecyclerView.Adapter<IssueListAdapter.IssueViewHolder>() {
    private var issuesList: List<IssueModelItem?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueListAdapter.IssueViewHolder {
        val binding: IssueListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.issue_list_item, parent, false)
        binding.setItemClickListener(clickListener)
        return IssueViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return if (issuesList == null) 0 else issuesList!!.size
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val currentList = issuesList!![position]
        holder.binding.setIssue(currentList)
        holder.binding.executePendingBindings()
    }

    class IssueViewHolder(binding: IssueListItemBinding) : RecyclerView.ViewHolder(binding.getRoot()) {
        val binding: IssueListItemBinding
        init {
            this.binding = binding
        }
    }

    fun setIssueList(list: List<IssueModelItem?>?){
        this.issuesList = list
        notifyDataSetChanged()

    }
}