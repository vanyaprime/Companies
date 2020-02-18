package com.companies.dev.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Ivan Prokopyev
 */
abstract class BaseViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    fun Disposable.addToDisposable() {
        disposable.add(this)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}