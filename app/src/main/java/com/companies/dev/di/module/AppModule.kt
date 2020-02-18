package com.companies.dev.di.module

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.companies.dev.Companies
import com.companies.dev.di.qualifiers.ActivityScope
import com.companies.dev.di.qualifiers.AppScope
import com.companies.dev.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * @author Ivan Prokopyev
 */
@Module(
    includes = [
        ApiModule::class,
        DataModule::class,
        ActivityModule::class
    ]
)
abstract class AppModule {

    @Module
    companion object {

        @AppScope
        @JvmStatic
        @Provides
        fun provideContext(app: Companies) = app.applicationContext
    }

    @AppScope
    @Binds
    abstract fun app(app: Companies): Application

    @Reusable
    @Binds
    abstract fun bindsViewModuleFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}