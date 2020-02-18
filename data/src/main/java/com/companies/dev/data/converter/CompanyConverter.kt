package com.companies.dev.data.converter

import com.companies.dev.data.entity.CompanyModel
import com.companies.dev.domain.entity.Company
import javax.inject.Inject
import javax.inject.Named

/**
 * @author Ivan Prokopyev
 */
class CompanyConverter @Inject constructor(
    @Named("BaseUrl") private val baseUrl: String
) {

    fun convert(from: CompanyModel): Company =
        Company(
            id = from.id,
            name = from.name,
            imgUrl = baseUrl + from.img
        )
}