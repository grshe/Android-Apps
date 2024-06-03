package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.maps.model.LatLng

class Restaurants {
    private lateinit var context : Context
    private var restaurants : Array<Restaurant> = Array<Restaurant>(23, {i -> Restaurant(names[i], hours[i], locations[i])})
    private var names : Array<String> = arrayOf("The Board and Brew", "GrillMarx Steakhouse & Raw Bar", "The Hall CP", "Franklin’s", "Burtons Grill & Bar", "Trattoria Da Lina", "Roots Natural Kitchen", "Busboys and Poets", "Ritchie’s Colombian Restaurant", "Taqueria Habanero", "Onikama Ramen Bar", "Hot Lola’s", "LàTAO", "Pennyroyal Station", "SEOULSPICE", "The Great Greek Mediterranean Grill", "Iron Pig", "Riviera Tapas Bar", "Sardi’s Pollo A La Brasa", "taim mediterranean kitchen", "Hook & Reel Cajun Seafood & Bar", "Huncho House", "Mr. Fries Man", "2Fifty Texas BBQ")
    private var hours : Array<String> = arrayOf("9:00 AM - 9:00 PM", "10:00 AM - 10:00 PM", "12:00 PM - 10:00 PM", "12:00 PM - 9:00 PM", "11:00 AM - 9:00 PM", "11:00 AM - 8:30 PM", "9:00 AM - 9:00 PM", "10:00 AM - 10:00 PM", "12:00 PM - 10:00 PM", "12:00 PM - 9:00 PM", "11:00 AM - 9:00 PM", "11:00 AM - 8:30 PM", "9:00 AM - 9:00 PM", "10:00 AM - 10:00 PM", "12:00 PM - 10:00 PM", "12:00 PM - 9:00 PM", "11:00 AM - 9:00 PM", "11:00 AM - 8:30 PM", "9:00 AM - 9:00 PM", "10:00 AM - 10:00 PM", "12:00 PM - 10:00 PM", "12:00 PM - 9:00 PM", "11:00 AM - 9:00 PM", "11:00 AM - 8:30 PM")
    private var locations : Array<LatLng> = arrayOf(
        LatLng(38.991745376766644, -76.93381754638234), LatLng(38.98712486913905, -76.9349598184892), LatLng(38.98636520348463, -76.93364019056965), LatLng(38.952585757501275, -76.94027131150563), LatLng(38.96996889771636, -76.9372817279275), LatLng(38.97476272025047, -77.01242961609141), LatLng(38.97843396516889, -76.93837395307908), LatLng(38.95521674108233, -76.9398385462715), LatLng(38.980075991960234, -76.93769961615787), LatLng(38.99118858735049, -76.9324594109364), LatLng(38.98452503515121, -76.94980089667327),
        LatLng(38.97050553626127, -76.93677408138551), LatLng(38.99737052510775, -76.93198769497165), LatLng(38.936373673241555, -76.96152294436529), LatLng(38.9803338358779, -76.94272784360433), LatLng(38.99872647030902, -76.93187800037994), LatLng(38.996900930612945, -76.90898031152759), LatLng(38.96287286936821, -76.93576613236242), LatLng(39.02706468684825, -76.9179179666316), LatLng(38.98099034636703, -76.93762229472607), LatLng(38.99759527172412, -76.90740149817441), LatLng(38.96759817147069, -76.95194646363818), LatLng(38.99143634613877, -76.93284728473238), LatLng(38.96198016273836, -76.93492219489997))

    constructor (context : Context) {
        this.context = context
    }

    private fun index (day : Int, month : Int) : Int {
        return (day + month).mod(24)
    }

    fun getRestaurant (day : Int, month : Int) : String {
        var index : Int = index(day, month)
        return restaurants[index].getName()
    }

    fun getHours (day : Int, month : Int) : String {
        var index : Int = index(day, month)
        return restaurants[index].getHours()
    }

    fun getCoords (day : Int, month : Int) : LatLng {
        var index : Int = index(day, month)
        return restaurants[index].getLocation()
    }

    fun getRating (day : Int, month : Int) : Float {
        var index : Int = (day + month).mod(24)
        var pref : SharedPreferences = context.getSharedPreferences("XYZ", Context.MODE_PRIVATE)
        return pref.getFloat("RATIN" + index, -1f)
    }

    fun setRating (rating : Float, day : Int, month : Int) {
        var index : Int = index(day, month)
        var pref : SharedPreferences = context.getSharedPreferences("XYZ", Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = pref.edit()
        editor.putFloat("RATIN" + index, rating)
        editor.commit()
    }
}