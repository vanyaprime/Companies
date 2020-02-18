package com.companies.dev.data.datasource

import com.companies.dev.data.database.CompaniesDatabaseAccessor
import com.companies.dev.domain.entity.Company
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
interface CompaniesLocalDataSource {

    fun getCompaniesByName(name: String): List<Company>

    fun saveCompanies(companies: List<Company>)

    fun getCompanies(): List<Company>
}

class CompaniesLocalDataSourceImpl @Inject constructor(
    private val dbAccessor: CompaniesDatabaseAccessor
) : CompaniesLocalDataSource {

    override fun getCompaniesByName(name: String): List<Company> =
        dbAccessor.selectByName(name)


    override fun saveCompanies(companies: List<Company>) =
        dbAccessor.insertAll(companies)

    override fun getCompanies(): List<Company> =
        dbAccessor.selectAll()
}