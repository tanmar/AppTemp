package com.tanmar.presentation.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.tanmar.presentation.BaseApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun application(application: BaseApplication): Application = application

    @Provides
    fun resource(application: BaseApplication): Resources = application.resources

    @Provides
    fun context(application: BaseApplication): Context = application

}