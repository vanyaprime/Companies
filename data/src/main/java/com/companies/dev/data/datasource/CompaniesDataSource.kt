package com.companies.dev.data.datasource

import com.companies.dev.data.api.Api
import com.companies.dev.data.converter.CompanyConverter
import com.companies.dev.domain.entity.Company
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
interface CompaniesDataSource {

    fun get(): Single<List<Company>>
}

class CompaniesDataSourceImpl @Inject constructor(
    private val api: Api,
    private val converter: CompanyConverter
) : CompaniesDataSource {

    override fun get(): Single<List<Company>> =
        api.getCompanies()
            .map { companyModels ->
                val companies = mutableListOf<Company>()
                companyModels.forEach {
                    companies.add(converter.convert(it))
                }
                companies.toList()
            }
            .subscribeOn(Schedulers.io())
}