package com.project.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.myapplication.R

class RegActivity:AppCompatActivity() {

    lateinit var cl_submit: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        init()

        cl_submit.setOnClickListener {

            startActivity(Intent(this@RegActivity, SaleActivity::class.java))
            finish()
        }


    }


    private fun init()
    {
        cl_submit=findViewById(R.id.cl_submit)

    }

}