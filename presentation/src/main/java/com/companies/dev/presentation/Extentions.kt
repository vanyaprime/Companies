package com.companies.dev.presentation

import androidx.lifecycle.MutableLiveData

/**
 * @author Ivan Prokopyev
 */
fun <T> MutableLiveData<T>.postEvent(value: T) {
    this.value = value
    this.value = null
}