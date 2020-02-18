package com.companies.dev.domain.entity

/**
 * @author Ivan Prokopyev
 */
data class Details(
    val id: Long,
    val name: String,
    val img: String,
    val description: String,
    val lat: Double?,
    val lon: Double?,
    val companyUrl: String?,
    val phone: String?
)