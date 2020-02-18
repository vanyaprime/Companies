package com.companies.dev.data.repository

import com.companies.dev.data.datasource.DetailsDataSource
import com.companies.dev.domain.entity.Details
import com.companies.dev.domain.repository.DetailsRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
class DetailsRepositoryImpl @Inject constructor(
    private val dataSource: DetailsDataSource
) : DetailsRepository {

    override fun getDetails(id: Long): Single<Details> =
        dataSource.get(id)
}