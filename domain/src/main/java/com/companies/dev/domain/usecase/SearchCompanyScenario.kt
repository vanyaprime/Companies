package com.companies.dev.domain.usecase

import com.companies.dev.domain.entity.Company
import com.companies.dev.domain.repository.CompaniesRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
class SearchCompanyScenario @Inject constructor(
    private val repository: CompaniesRepository
) {

    private companion object {
        const val DEBOUNCE_TIME = 200L
    }

    private val searchSubject: PublishSubject<String> = PublishSubject.create()

    val searchObservable: Observable<String> = searchSubject
        .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())

    fun search(query: String) {
        searchSubject.onNext(query)
    }

    operator fun invoke(query: String): Observable<List<Company>> =
        Observable.just(repository.getByName(query))
}