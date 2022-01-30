package com.project.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.myapplication.R

class LoginActivity:AppCompatActivity() {

    lateinit var cl_submit:ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

        cl_submit.setOnClickListener {

            startActivity(Intent(this@LoginActivity, SaleActivity::class.java))
            finish()
        }



    }

    private fun init()
    {
        cl_submit=findViewById(R.id.cl_submit)

    }


}