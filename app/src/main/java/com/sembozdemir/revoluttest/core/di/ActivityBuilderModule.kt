package com.sembozdemir.revoluttest.core.di

import android.app.Application
import com.sembozdemir.revoluttest.App
import com.sembozdemir.revoluttest.main.MainActivity
import com.sembozdemir.revoluttest.main.MainActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuilderModule {

    @Binds
    @Singleton
    abstract fun application(app: App): Application

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}