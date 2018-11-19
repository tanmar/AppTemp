package com.tanmar.presentation.di

import com.tanmar.presentation.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector()
    abstract fun provideHomeFragment(): HomeFragment

}