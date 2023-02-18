package com.example.mapspin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mapspin.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val places = arrayListOf(
        Location(
            "Ibira",
            LatLng(-23.588353, -46.659091),
            "Vila Mariana, São Paulo - SP, 04094-050",
            4.8f
        ),
        Location(
            "Estadão",
            LatLng(-23.5488665673903, -46.6426534582181),
            "R. Maj. Quedinho, 107 - Centro Histórico de São Paulo, São Paulo - SP, 01050-030",
            4.8f
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment =
            supportFragmentManager.findFragmentById(binding.mapFragment.id) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            makerPlace(googleMap)

            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()

                places.forEach {
                    bounds.include(it.latLong)

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 200))
                }
            }
        }
    }

    private fun makerPlace(googleMap: GoogleMap) {
        places.forEach { place ->
            val maker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .snippet(place.address)
                    .position(place.latLong)
            )
        }
    }
}