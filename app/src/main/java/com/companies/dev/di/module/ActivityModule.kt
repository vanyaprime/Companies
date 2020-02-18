package com.companies.dev.di.module

import com.companies.dev.di.qualifiers.ActivityScope
import com.companies.dev.di.qualifiers.AppScope
import com.companies.dev.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Ivan Prokopyev
 */
@Module
interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun contributeActivity(): MainActivity
}