package com.companies.dev.data.api

import com.companies.dev.data.entity.CompanyModel
import com.companies.dev.data.entity.DetailsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Ivan Prokopyev
 */
interface Api {

    @GET("test.php")
    fun getCompanies(): Single<List<CompanyModel>>

    @GET("test.php?")
    fun getCompanyDetails(@Query("id") id: Long): Single<List<DetailsModel>>
}