package com.tanmar

import android.net.Uri
import android.os.Bundle
import com.tanmar.presentation.BaseActivity
import com.tanmar.presentation.home.HomeFragment

class MainActivity : BaseActivity(), HomeFragment.OnFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
