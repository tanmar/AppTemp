package com.tanmar.presentation

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out BaseApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

}