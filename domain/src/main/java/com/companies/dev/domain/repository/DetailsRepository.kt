package com.companies.dev.domain.repository

import com.companies.dev.domain.entity.Details
import io.reactivex.Single

/**
 * @author Ivan Prokopyev
 */
interface DetailsRepository {

    fun getDetails(id: Long): Single<Details>
}