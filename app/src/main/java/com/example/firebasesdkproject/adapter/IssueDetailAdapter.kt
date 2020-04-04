package com.example.firebasesdkproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasesdkproject.R
import com.example.firebasesdkproject.databinding.CommentIssueListBinding
import com.example.firebasesdkproject.databinding.IssueListItemBinding
import com.example.firebasesdkproject.model.CommentModelItem
import com.example.firebasesdkproject.model.IssueModelItem

class IssueDetailAdapter : RecyclerView.Adapter<IssueDetailAdapter.DetailViewHolder>() {
    private var commentList: List<CommentModelItem?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueDetailAdapter.DetailViewHolder {
        val binding: CommentIssueListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.comment_issue_list, parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueDetailAdapter.DetailViewHolder, position: Int) {
        val currentList = commentList!![position]
        holder.binding.setCommentModel(currentList)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (commentList == null) 0 else commentList!!.size
    }

    class DetailViewHolder(binding: CommentIssueListBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        val binding: CommentIssueListBinding

        init {
            this.binding = binding
        }
    }

    fun setCommentList(list: List<CommentModelItem?>?) {
        this.commentList = list
        notifyDataSetChanged()

    }

}