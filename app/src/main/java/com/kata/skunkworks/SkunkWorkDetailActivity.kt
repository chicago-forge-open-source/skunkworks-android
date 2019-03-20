package com.kata.skunkworks

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_skunk_work_detail.*

class SkunkWorkDetailActivity : AppCompatActivity() {
    private val appContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skunk_work_detail)

        sw_title_edit_text.setText(intent.extras?.getString("title") ?: "")


        sw_save_btn.setOnClickListener {
            val interactor = SkunkWorkInteractor(SkunkWorkRepository(appContext))
            val skunkWorksTitle = sw_title_edit_text.text.toString()

            if(!skunkWorksTitle.equals("")) {
                interactor.addSkunkWork(skunkWorksTitle.trim())
                finish()
            }
        }
    }
}
