package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gsamsonas.mobiliujuprogramavimas.R
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentMapBinding
import com.gsamsonas.mobiliujuprogramavimas.viewmodels.MapViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: MapViewModel by viewModels()
    private lateinit var binding: FragmentMapBinding
    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        (childFragmentManager.findFragmentById(R.id.fragmentMap) as? SupportMapFragment)
            ?.getMapAsync(this)

        viewModel.markerList.observe(viewLifecycleOwner) { markerList ->
            googleMap?.clear()
            markerList.forEach {
                googleMap?.addMarker(
                    MarkerOptions()
                        .title(it.title)
                        .position(LatLng(it.latitude, it.longitude))
                )
            }
        }
        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.setOnMarkerClickListener { marker ->
            viewModel.selectMarker(marker.title)
            false
        }
        viewModel.markerList.value?.forEach {
            googleMap.addMarker(
                MarkerOptions()
                    .title(it.title)
                    .position(LatLng(it.latitude, it.longitude))
            )
        }
    }
}
