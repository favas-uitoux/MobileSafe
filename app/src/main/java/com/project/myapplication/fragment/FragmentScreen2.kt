package com.project.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.project.myapplication.R
import com.project.myapplication.Utilz.Constants


class FragmentScreen2 : Fragment() {
    lateinit var view1: View
    lateinit var btnSignup: Button
    lateinit var btnLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_screen2, container, false);
        init();
        btnSignup.setOnClickListener {

           Constants.interfaceOnboard.jumbToReg()

        }

        btnLogin.setOnClickListener {

            Constants.interfaceOnboard.jumbToLogin()

        }





        return view1;
    }

    private fun init() {
        btnSignup = view1.findViewById(R.id.btnSignup)
        btnLogin = view1.findViewById(R.id.btnLogin)

    }


}