package com.companies.dev.presentation.details

import com.companies.dev.domain.entity.Details

/**
 * @author Ivan Prokopyev
 */
sealed class DetailsState

data class StateContent(val details: Details) : DetailsState()
data class StateError(val message: String) : DetailsState()