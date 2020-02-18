package com.companies.dev.di.module

import com.companies.dev.di.qualifiers.FragmentScope
import com.companies.dev.ui.details.DetailsFragment
import com.companies.dev.ui.list.CompaniesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Ivan Prokopyev
 */
@Module
interface FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [CompaniesModule::class])
    fun contributeCompaniesFragment(): CompaniesListFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailsModule::class])
    fun contributeDetailsFragment(): DetailsFragment
}