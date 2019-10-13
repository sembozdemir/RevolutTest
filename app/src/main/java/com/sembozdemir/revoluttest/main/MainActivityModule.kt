package com.sembozdemir.revoluttest.main

import android.app.Activity
import android.content.Context
import com.sembozdemir.revoluttest.core.di.ActivityScope
import com.sembozdemir.revoluttest.core.di.BaseActivityModule
import com.sembozdemir.revoluttest.core.util.ErrorHandler
import com.sembozdemir.revoluttest.core.util.ErrorHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract fun activity(homeActivity: MainActivity): Activity
}