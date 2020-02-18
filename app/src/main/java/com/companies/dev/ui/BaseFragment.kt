package com.companies.dev.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.companies.dev.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

/**
 * @author Ivan Prokopyev
 */
abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar("")
    }

    protected fun setupToolbar(title: String) {
        activity?.toolbar?.title = title
    }
}