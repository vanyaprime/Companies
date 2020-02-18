package com.companies.dev.domain.usecase

import com.companies.dev.domain.entity.Company
import com.companies.dev.domain.repository.CompaniesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
class GetCompaniesUseCase @Inject constructor(
    private val repository: CompaniesRepository
) {
    operator fun invoke(): Single<List<Company>> = repository.get()
}