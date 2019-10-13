package com.sembozdemir.revoluttest.core.di

import android.content.Context
import com.sembozdemir.revoluttest.App
import com.sembozdemir.revoluttest.core.network.NetworkModule
import com.sembozdemir.revoluttest.core.util.ErrorHandler
import com.sembozdemir.revoluttest.core.util.ErrorHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    fun provideErrorHandler(context: Context): ErrorHandler = ErrorHandlerImpl(context)
}