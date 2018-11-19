package com.tanmar.presentation.di

import com.tanmar.presentation.BaseApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidSupportInjectionModule::class
])

interface ApplicationComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>()

}