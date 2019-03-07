package com.kata.skunkworks

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        show_add_skunk_work.setOnClickListener { view ->
            startActivity(Intent(this, AddSkunkWorkActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val interactor = SkunkWorkInteractor(SkunkWorkRepository(this))
        skunk_works_list.layoutManager = LinearLayoutManager(this)
        skunk_works_list.adapter = SkunkWorkListAdapter(interactor.findAllSkunkWork())
    }
}
