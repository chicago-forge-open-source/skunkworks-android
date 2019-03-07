package com.kata.skunkworks

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_skunk_work.*

class AddSkunkWorkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_skunk_work)

        sw_save_btn.setOnClickListener {
            val interactor = SkunkWorkInteractor(SkunkWorkRepository(this))
            val skunkWorksTitle = sw_title_edit_text.text.toString()

            if(!skunkWorksTitle.equals("")) {
                interactor.addSkunkWork(skunkWorksTitle.trim())
                finish()
            }
        }
    }
}
