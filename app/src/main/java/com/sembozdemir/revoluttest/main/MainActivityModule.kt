package com.sembozdemir.revoluttest.main

import android.app.Activity
import com.sembozdemir.revoluttest.core.di.ActivityScope
import com.sembozdemir.revoluttest.core.di.BaseActivityModule
import dagger.Binds
import dagger.Module

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract fun activity(homeActivity: MainActivity): Activity
}