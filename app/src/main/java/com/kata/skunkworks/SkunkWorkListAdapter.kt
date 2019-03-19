package com.kata.skunkworks

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.skunk_work_list_item.view.*

class SkunkWorkListAdapter(val appContext: Context, var skunkWorkList: MutableList<SkunkWork>) : RecyclerView.Adapter<SkunkWorkListAdapter.SkunkWorkListItem>() {

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

    fun delete(position: Int) {
        this.skunkWorkList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, getItemCount() - position)
    }

    override fun onBindViewHolder(skunkWorkListItem: SkunkWorkListItem, position: Int) {
        skunkWorkListItem.bindTitle(skunkWorkList[position].title)
        skunkWorkListItem.deleteSkunkWork(position, this)
    }

    class SkunkWorkListItem(private val skunkwork: View) : RecyclerView.ViewHolder(skunkwork) {

        fun bindTitle(title: String) {
            skunkwork.skunk_work_title.text = title
        }

        fun deleteSkunkWork(position: Int, adapter: SkunkWorkListAdapter) {
            skunkwork.remove_skunkwork.setOnClickListener {
                val interactor = SkunkWorkInteractor(SkunkWorkRepository(adapter.appContext))
                interactor.deleteSkunkWork(position)
                adapter.delete(position)
            }

        }
    }

}