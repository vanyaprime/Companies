package com.companies.dev.di.module

import android.content.Context
import com.companies.dev.R
import com.companies.dev.data.api.Api
import com.companies.dev.di.qualifiers.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


/**
 * @author Ivan Prokopyev
 */
@Module
class ApiModule {

    @AppScope
    @Provides
    fun provideRetrofit(
        context: Context,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.base_url))
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @AppScope
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        return httpClient.build()
    }

    @AppScope
    @Provides
    fun provideApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)

    @AppScope
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl(context: Context): String =
        context.getString(R.string.base_url)
}