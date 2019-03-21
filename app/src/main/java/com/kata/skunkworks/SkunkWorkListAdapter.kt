package com.kata.skunkworks

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.skunk_work_list_item.view.*

class SkunkWorkListAdapter(val appContext: Context, var skunkWorkList: MutableList<SkunkWork>) : androidx.recyclerview.widget.RecyclerView.Adapter<SkunkWorkListAdapter.SkunkWorkListItem>() {

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

    override fun onBindViewHolder(skunkWorkListItem: SkunkWorkListItem, position: Int) {
        skunkWorkListItem.bindTitle(skunkWorkList[position].title)
    }

    class SkunkWorkListItem(private val skunkwork: View) : RecyclerView.ViewHolder(skunkwork), View.OnClickListener {
        init {
            skunkwork.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(skunkwork.context, SkunkWorkDetailActivity::class.java)
            intent.putExtra("title", skunkwork.skunk_work_title.text.toString())
            startActivity(skunkwork.context, intent, null)
        }

        fun bindTitle(title: String) {
            skunkwork.skunk_work_title.text = title
        }
    }

}