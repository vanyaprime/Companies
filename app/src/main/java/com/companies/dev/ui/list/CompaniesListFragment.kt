package com.companies.dev.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.companies.dev.R
import com.companies.dev.domain.entity.Company
import com.companies.dev.presentation.companies.list.CompaniesListState
import com.companies.dev.presentation.companies.list.CompaniesListViewModel
import com.companies.dev.presentation.companies.list.StateContent
import com.companies.dev.presentation.companies.list.StateError
import com.companies.dev.ui.BaseFragment
import com.companies.dev.ui.COMPANY_ID
import kotlinx.android.synthetic.main.companies_fragment.*
import kotlinx.android.synthetic.main.error_layout.*


class CompaniesListFragment : BaseFragment() {

    private var adapter: CompaniesAdapter? = null

    private val viewModel: CompaniesListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CompaniesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.companies_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(getString(R.string.app_name))
        initListeners()
        initSearch()
        initCompaniesList()
        observe()
    }

    private fun initListeners() {
        errorRetryButton.setOnClickListener {
            errorRetryButton.isEnabled = false
            viewModel.load()
        }
    }

    private fun initSearch() {
        viewModel.addSearch()
        companySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty()) {
                        viewModel.search(it)
                    } else {
                        viewModel.load()
                    }
                }
                return false
            }

        })
        companySearchView.setOnCloseListener {
            viewModel.load()
            false
        }
    }

    private fun initCompaniesList() {
        adapter = CompaniesAdapter { viewModel.companySelect(it) }

        companiesRecyclerView.layoutManager = LinearLayoutManager(context)
        companiesRecyclerView.adapter = adapter
    }

    private fun observe() {
        viewModel.companiesLiveData.observe(
            viewLifecycleOwner,
            Observer(::showCompanies)
        )

        viewModel.selectedCompanyIdLiveData
            .observe(viewLifecycleOwner,
                Observer {
                    it?.let {
                        val b = Bundle()
                        b.putLong(COMPANY_ID, it)
                        findNavController().navigate(R.id.action_to_details, b)
                    }
                }
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

    private fun showCompanies(companiesState: CompaniesListState) {
        when (companiesState) {
            is StateContent -> showContent(companiesState.companies)
            is StateError -> showError()
        }
    }

    private fun showContent(companies: List<Company>) {
        errorLayout.visibility = View.GONE
        companiesContent.visibility = View.VISIBLE
        adapter?.setItems(companies)
    }

    private fun showError() {
        errorRetryButton.isEnabled = true
        companiesContent.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
    }
}
