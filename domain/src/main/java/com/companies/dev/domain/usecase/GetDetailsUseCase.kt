package com.companies.dev.domain.usecase

import com.companies.dev.domain.entity.Details
import com.companies.dev.domain.repository.DetailsRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
class GetDetailsUseCase @Inject constructor(
    private val repository: DetailsRepository
) {
    operator fun invoke(id: Long): Single<Details> = repository.getDetails(id)
}