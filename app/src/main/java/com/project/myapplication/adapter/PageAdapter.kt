package com.project.myapplication.adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.project.myapplication.fragment.FragmentScreen1
import com.project.myapplication.fragment.FragmentScreen2


open class PageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return FragmentScreen1();
            }


            else -> {
                return FragmentScreen2();
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return ""
            }
            1 -> {
                return ""
            }

        }
        return super.getPageTitle(position)
    }
}