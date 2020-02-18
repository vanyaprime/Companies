package com.companies.dev.domain.repository

import com.companies.dev.domain.entity.Company
import io.reactivex.Single

/**
 * @author Ivan Prokopyev
 */
interface CompaniesRepository {

    fun get(): Single<List<Company>>

    fun getByName(query: String): List<Company>
}