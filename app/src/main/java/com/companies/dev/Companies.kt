package com.companies.dev

import com.companies.dev.di.DaggerCompaniesComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * @author Ivan Prokopyev
 */
class Companies : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerCompaniesComponent
            .builder()
            .create(this)

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}