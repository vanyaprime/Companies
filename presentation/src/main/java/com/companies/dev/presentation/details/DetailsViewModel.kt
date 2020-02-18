package com.companies.dev.presentation.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.companies.dev.domain.entity.Details
import com.companies.dev.domain.usecase.GetDetailsUseCase
import com.companies.dev.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : BaseViewModel() {

    private val _detailsLiveData = MutableLiveData<DetailsState>()
    val detailsLiveData: LiveData<DetailsState> = _detailsLiveData

    private var id: Long = -1L

    fun loadDetails(id: Long) {
        this.id = id
        getDetailsUseCase(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::doOnSuccess, ::doOnError)
            .addToDisposable()
    }

    private fun doOnSuccess(details: Details) {
        _detailsLiveData.value = StateContent(details)
    }

    private fun doOnError(throwable: Throwable) {
        Log.i("${this.javaClass}", throwable.localizedMessage)
        _detailsLiveData.value = StateError(throwable.localizedMessage)
    }

    fun load() {
        loadDetails(id)
    }
}