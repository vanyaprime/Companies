package com.companies.dev.di

import com.companies.dev.Companies
import com.companies.dev.di.module.AppModule
import com.companies.dev.di.qualifiers.AppScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Ivan Prokopyev
 */
@AppScope
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface CompaniesComponent : AndroidInjector<Companies> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Companies>()

}