package com.companies.dev.di.module

import androidx.lifecycle.ViewModel
import com.companies.dev.di.qualifiers.FragmentScope
import com.companies.dev.di.qualifiers.ViewModelKey
import com.companies.dev.presentation.details.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Ivan Prokopyev
 */
@Module
interface DetailsModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindViewModule(vm: DetailsViewModel): ViewModel
}