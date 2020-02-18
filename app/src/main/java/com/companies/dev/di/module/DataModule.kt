package com.companies.dev.di.module

import android.content.Context
import androidx.room.Room
import com.companies.dev.R
import com.companies.dev.data.database.CompaniesDatabase
import com.companies.dev.data.database.CompaniesDatabaseAccessor
import com.companies.dev.data.datasource.*
import com.companies.dev.data.repository.CompaniesRepositoryImpl
import com.companies.dev.data.repository.DetailsRepositoryImpl
import com.companies.dev.di.qualifiers.AppScope
import com.companies.dev.domain.repository.CompaniesRepository
import com.companies.dev.domain.repository.DetailsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @author Ivan Prokopyev
 */
@Module
abstract class DataModule {

    @Module
    companion object {


        @AppScope
        @Provides
        @JvmStatic
        fun bindRoom(context: Context): CompaniesDatabase =
            Room.databaseBuilder(
                context,
                CompaniesDatabase::class.java,
                context.getString(R.string.database_name)
            )
                .build()

        @Provides
        @AppScope
        @JvmStatic
        fun provideAccessor(database: CompaniesDatabase): CompaniesDatabaseAccessor =
            database.accessor()
    }

    @AppScope
    @Binds
    abstract fun bindCompaniesDataSource(impl: CompaniesDataSourceImpl): CompaniesDataSource

    @AppScope
    @Binds
    abstract fun bindLocalCompaniesDataSource(impl: CompaniesLocalDataSourceImpl): CompaniesLocalDataSource

    @AppScope
    @Binds
    abstract fun bindDetailsDataSource(imp: DetailsDataSourceImpl): DetailsDataSource

    @AppScope
    @Binds
    abstract fun bindDetailsRepository(imp: DetailsRepositoryImpl): DetailsRepository

    @AppScope
    @Binds
    abstract fun bindCompaniesRepository(impl: CompaniesRepositoryImpl): CompaniesRepository
}