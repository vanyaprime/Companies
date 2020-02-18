package com.companies.dev.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.companies.dev.domain.entity.Details
import com.companies.dev.presentation.details.DetailsState
import com.companies.dev.presentation.details.DetailsViewModel
import com.companies.dev.presentation.details.StateContent
import com.companies.dev.presentation.details.StateError
import com.companies.dev.ui.BaseFragment
import com.companies.dev.ui.COMPANY_ID
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.company_details_fragment.*
import kotlinx.android.synthetic.main.error_layout.*


/**
 * @author Ivan Prokopyev
 */
class DetailsFragment : BaseFragment(), OnMapReadyCallback {

    private companion object {
        const val DEFAULT_MAP_ZOOM = 15F
    }

    private var map: GoogleMap? = null

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        viewModel.loadDetails(arguments?.get(COMPANY_ID) as Long)
        map?.uiSettings?.isScrollGesturesEnabled = false
    }

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(com.companies.dev.R.layout.company_details_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailsLiveData.observe(
            viewLifecycleOwner,
            Observer(::showDetails)
        )
        initListeners()
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    private fun initListeners() {
        errorRetryButton.setOnClickListener {
            errorRetryButton.isEnabled = false
            viewModel.load()
        }
    }

    private fun showDetails(detailsState: DetailsState) {
        when (detailsState) {
            is StateContent -> showContent(detailsState.details)
            is StateError -> showError()
        }
    }

    private fun showContent(details: Details) {
        errorLayout.visibility = View.GONE
        detailsContent.visibility = View.VISIBLE

        setupToolbar(details.name)
        name.text = details.name
        description.text = details.description
        details.phone?.let {
            phone.text = it
            phone.visibility = View.VISIBLE

        }
        details.companyUrl?.let {
            site.text = it
            site.visibility = View.VISIBLE
        }
        image.setImageURI(details.img)

        if (details.lat != null && details.lon != null) {
            val position = LatLng(details.lat!!, details.lon!!)

            map?.addMarker(MarkerOptions().position(position))
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(position, DEFAULT_MAP_ZOOM))
            mapView.visibility = View.VISIBLE
        }
    }

    private fun showError() {
        errorRetryButton.isEnabled = true
        detailsContent.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}