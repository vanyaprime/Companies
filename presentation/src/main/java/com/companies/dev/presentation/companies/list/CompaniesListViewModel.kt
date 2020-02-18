package com.companies.dev.presentation.companies.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.companies.dev.domain.entity.Company
import com.companies.dev.domain.usecase.GetCompaniesUseCase
import com.companies.dev.domain.usecase.SearchCompanyScenario
import com.companies.dev.presentation.BaseViewModel
import com.companies.dev.presentation.postEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CompaniesListViewModel @Inject constructor(
    private val getCompaniesUseCase: GetCompaniesUseCase,
    private val searchCompanyScenario: SearchCompanyScenario
) : BaseViewModel() {

    private val _companiesLiveData = MutableLiveData<CompaniesListState>()
    val companiesLiveData: LiveData<CompaniesListState> = _companiesLiveData

    private val _selectedCompanyIdLiveData = MutableLiveData<Long>()
    val selectedCompanyIdLiveData: LiveData<Long> = _selectedCompanyIdLiveData

    init {
        loadCompanies()
    }

    private fun loadCompanies() {
        getCompaniesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::doOnSuccess, ::doOnError)
            .addToDisposable()
    }

    fun addSearch() {
        searchCompanyScenario.searchObservable
            .observeOn(Schedulers.io())
            .flatMap { searchCompanyScenario(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::doOnSuccess)
            .addToDisposable()
    }


    private fun doOnSuccess(companies: List<Company>) {
        _companiesLiveData.value = StateContent(companies)
    }

    private fun doOnError(throwable: Throwable) {
        Log.i("${this.javaClass}", throwable.localizedMessage)
        _companiesLiveData.value = StateError(throwable.localizedMessage)
    }

    fun companySelect(company: Company) {
        _selectedCompanyIdLiveData.postEvent(company.id)
    }

    fun search(query: String) {
        searchCompanyScenario.search(query)
    }

    fun load() {
        loadCompanies()
    }
}
