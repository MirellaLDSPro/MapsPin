package com.example.mapspin

import com.google.android.gms.maps.model.LatLng

class Location(
    val name: String,
    val latLong: LatLng,
    val address: String,
    val rating: Float
)