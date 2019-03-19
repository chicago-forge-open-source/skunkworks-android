package com.kata.skunkworks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val appContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        show_add_skunk_work.setOnClickListener {
            val intent = Intent(appContext, AddSkunkWorkActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val interactor = SkunkWorkInteractor(SkunkWorkRepository(appContext))
        skunk_works_list.layoutManager = LinearLayoutManager(appContext)
        skunk_works_list.adapter = SkunkWorkListAdapter(appContext, interactor.findAllSkunkWork())
    }
}
