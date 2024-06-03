package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.maps.model.LatLng

class Restaurant {
    private lateinit var context : Context
    private lateinit var name : String
    private lateinit var hours : String
    private lateinit var location : LatLng
    private var rating : Float = 0f

    constructor (context : Context, name : String, hours : String, location : LatLng) {
        this.name = name
        this.hours = hours
        this.location = location
    }

    fun getName () : String {
        return name
    }

    fun getHours () : String {
        return hours
    }

    fun getLocation () : LatLng {
        return location
    }

    fun getRating (index : Int) : Float {
        var pref : SharedPreferences = context.getSharedPreferences("XYZ", Context.MODE_PRIVATE)
        return pref.getFloat("RATING" + index, -1f)
    }

    fun setRating (rating : Float, index : Int) {
        this.rating = rating
        var pref : SharedPreferences = context.getSharedPreferences("XYZ", Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = pref.edit()
        editor.putFloat("RATING" + index, rating)
        editor.commit()
    }
}