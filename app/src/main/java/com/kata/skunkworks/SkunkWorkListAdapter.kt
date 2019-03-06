package com.kata.skunkworks

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.skunk_work_list_item.view.*

class SkunkWorkListAdapter(val skunkWorkList: List<SkunkWork>) :
    RecyclerView.Adapter<SkunkWorkListAdapter.SkunkWorkListItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SkunkWorkListItem {
        return SkunkWorkListItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.skunk_work_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return skunkWorkList.size
    }

    override fun onBindViewHolder(holder: SkunkWorkListItem, position: Int) {
        holder.bindTitle(skunkWorkList[position].title)
    }

    class SkunkWorkListItem(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindTitle(title: String) {

        }
    }

}