package com.companies.dev.data.datasource

import com.companies.dev.data.api.Api
import com.companies.dev.data.converter.DetailsConverter
import com.companies.dev.domain.entity.Details
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
interface DetailsDataSource {

    fun get(id: Long): Single<Details>
}

class DetailsDataSourceImpl @Inject constructor(
    private val api: Api,
    private val converter: DetailsConverter
) : DetailsDataSource {

    override fun get(id: Long): Single<Details> =
        api.getCompanyDetails(id)
            .map(converter::convert)
            .subscribeOn(Schedulers.io())
}