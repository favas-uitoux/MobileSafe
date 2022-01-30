package com.project.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.myapplication.R


class FragmentScreen1:Fragment() {
    lateinit var view1:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1=inflater.inflate(R.layout.fragment_screen1, container, false);
        return view1;
    }

}