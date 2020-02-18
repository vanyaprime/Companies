package com.companies.dev.data.repository

import com.companies.dev.data.datasource.CompaniesDataSource
import com.companies.dev.data.datasource.CompaniesLocalDataSource
import com.companies.dev.domain.entity.Company
import com.companies.dev.domain.repository.CompaniesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
class CompaniesRepositoryImpl @Inject constructor(
    private val dataSource: CompaniesDataSource,
    private val localDataSource: CompaniesLocalDataSource
) : CompaniesRepository {

    override fun get(): Single<List<Company>> =
        dataSource.get()
            .map {
                localDataSource.saveCompanies(it)
                it
            }

    override fun getByName(query: String): List<Company> =
        localDataSource.getCompaniesByName(query)
}