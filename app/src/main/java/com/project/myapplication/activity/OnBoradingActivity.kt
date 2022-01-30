package com.project.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.project.myapplication.R
import com.project.myapplication.Utilz.Constants
import com.project.myapplication.adapter.PageAdapter
import com.project.myapplication.interfaces.InterfaceOnboarding


class OnBoradingActivity:BaseActivity(),InterfaceOnboarding {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        Constants.interfaceOnboard=this@OnBoradingActivity



//
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = PageAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)






    }

    override fun jumbToReg() {
        startActivity(Intent(this@OnBoradingActivity, RegActivity::class.java))
//        //customType(this@SplashActivity, "fadein-to-fadeout")
     //   finish()
    }

    override fun jumbToLogin() {
        startActivity(Intent(this@OnBoradingActivity, LoginActivity::class.java))
//        //customType(this@SplashActivity, "fadein-to-fadeout")
     //   finish()
    }


}