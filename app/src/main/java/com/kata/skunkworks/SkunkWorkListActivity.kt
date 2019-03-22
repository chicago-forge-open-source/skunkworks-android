package com.kata.skunkworks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class SkunkWorkListActivity : AppCompatActivity() {
    private val appContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        show_add_skunk_work.setOnClickListener {
            val intent = Intent(appContext, SkunkWorkDetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val interactor = SkunkWorkInteractor(SkunkWorkRepository(appContext))
        skunk_works_list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(appContext)
        skunk_works_list.adapter = SkunkWorkListAdapter(appContext, interactor.findAllSkunkWork())
    }
}
