package com.companies.dev.di.module

import androidx.lifecycle.ViewModel
import com.companies.dev.di.qualifiers.FragmentScope
import com.companies.dev.di.qualifiers.ViewModelKey
import com.companies.dev.presentation.companies.list.CompaniesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Ivan Prokopyev
 */
@Module
interface CompaniesModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(CompaniesListViewModel::class)
    fun bindViewModule(vm: CompaniesListViewModel): ViewModel
}