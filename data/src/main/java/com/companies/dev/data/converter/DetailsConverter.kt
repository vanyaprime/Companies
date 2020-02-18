package com.companies.dev.data.converter

import com.companies.dev.data.entity.DetailsModel
import com.companies.dev.domain.entity.Details
import javax.inject.Inject
import javax.inject.Named

/**
 * @author Ivan Prokopyev
 */
class DetailsConverter @Inject constructor(
    @Named("BaseUrl") private val baseUrl: String
) {

    fun convert(from: List<DetailsModel>): Details =
        with(from.first()) {
            Details(
                id = id,
                name = name,
                img = baseUrl + img,
                description = description,
                lat = if (lat == 0.toDouble()) null else lat,
                lon = if (lon == 0.toDouble()) null else lon,
                companyUrl = if (www.isEmpty()) null else www,
                phone = if (phone.isEmpty()) null else phone
            )
        }
}