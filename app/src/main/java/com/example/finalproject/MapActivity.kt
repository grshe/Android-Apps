package com.example.finalproject

import android.app.ActivityOptions
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Calendar

class MapActivity: AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map : GoogleMap
    private lateinit var camera: CameraUpdate
    private var day : Int = 0
    private var month : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        var calendar : Calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)

        var fragment : SupportMapFragment =
            supportFragmentManager.findFragmentById( R.id.map ) as SupportMapFragment
        fragment.getMapAsync( this )
    }

    ///Addresss
    //GEOcodeig
    //Display Camera
    override fun onMapReady(p0: GoogleMap) {
        map = p0
        var displayedLoc: LatLng = MainActivity.restaurants.getCoords(day, month)
        var title:String = MainActivity.restaurants.getRestaurant(day, month)

        //Add Marker to Location
        map.addMarker(MarkerOptions().position(displayedLoc).title(title).snippet(""))
        var camera : CameraUpdate = CameraUpdateFactory.newLatLngZoom( displayedLoc, 16.0f )
        map.moveCamera( camera )
        //At this point, will have the Location we'll be displaying on the map
    }

    fun home (v : View) {
        finish()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    inner class GeocodeHandler : Geocoder.GeocodeListener {
        override fun onGeocode(p0: MutableList<Address>) {
            if( p0 != null && p0.size > 0 ) {
                var firstResult : Address = p0.get( 0 )
                var lat : Double = firstResult.latitude
                var long : Double = firstResult.longitude
            }
        }

        fun getLocation(): LatLng {
            return LatLng(0.0,0.0);
        }

        override fun onError(errorMessage: String?) {
            super.onError(errorMessage)
            Log.w( "MainActivity", "error: " + errorMessage )
        }
    }

    //This changes the location of he map to the restaurant after geocoding
    inner class UpdateGui : Runnable {
        override fun run() {
            Log.w( "MainActivity", "Inside UpdateGui:run" )
            // var camera : CameraUpdate = CameraUpdateFactory.newLatLngZoom( this@MainActivity.latLng, 16.0f )
            map.moveCamera( camera )
        }
    }
}
