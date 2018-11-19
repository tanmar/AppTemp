package com.tanmar.presentation.di

import com.nomtek.switchdxb.presentation.di.scopes.ActivityBuilderModule
import com.tanmar.data.di.ApiModule
import com.tanmar.data.di.RepositoryModule
import com.tanmar.presentation.BaseApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    ApiModule::class,
    RepositoryModule::class
])
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>()

}