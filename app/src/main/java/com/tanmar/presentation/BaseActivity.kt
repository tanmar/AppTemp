package com.tanmar.presentation

import android.widget.Toolbar
import androidx.annotation.Nullable
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Nullable
    private var toolbar: Toolbar? = null


    fun getToolbar(): Toolbar {
        if (toolbar == null) {
            setupToolbar()
        }
        return toolbar!!
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        if (toolbar != null) {
            setActionBar(toolbar)
            actionBar!!.setDisplayShowTitleEnabled(false)
            toolbar!!.title = null
        }
    }
}