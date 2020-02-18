package com.companies.dev.data.entity

/**
 * @author Ivan Prokopyev
 */
data class DetailsModel(
    val id: Long,
    val name: String,
    val img: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val www: String,
    val phone: String
)