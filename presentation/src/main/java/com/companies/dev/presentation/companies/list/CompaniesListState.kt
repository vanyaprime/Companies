package com.companies.dev.presentation.companies.list

import com.companies.dev.domain.entity.Company

/**
 * @author Ivan Prokopyev
 */
sealed class CompaniesListState

data class StateContent(val companies: List<Company>) : CompaniesListState()
data class StateError(val message: String) : CompaniesListState()