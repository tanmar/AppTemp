package com.nomtek.switchdxb.presentation.di.scopes

import android.app.Activity
import com.tanmar.MainActivity
import com.tanmar.presentation.di.FragmentsModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @Module
    internal interface MainActivityModule {
        @Binds
        fun bindMainActivity(activity: MainActivity): Activity
    }

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}