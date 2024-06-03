package com.example.finalproject

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import java.util.Calendar

class MainActivity : AppCompatActivity(), RatingBar.OnRatingBarChangeListener {
    private var day : Int = 0
    private var month : Int = 0
    private lateinit var restNameTV : TextView
    private lateinit var restHoursTV : TextView
    private lateinit var ratingLabelTV : TextView
    private lateinit var visitedTV : TextView
    private lateinit var ratingBar : RatingBar
    private var ad : InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restaurants = Restaurants(this)

        var calendar : Calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)

        restNameTV = findViewById(R.id.restName)
        restHoursTV = findViewById(R.id.restHours)
        ratingLabelTV = findViewById(R.id.ratingLabel)
        visitedTV = findViewById(R.id.visitedText)
        ratingBar = findViewById(R.id.ratingBar)

        restNameTV.text = restaurants.getRestaurant(day, month)
        restHoursTV.text = restaurants.getHours(day, month)
        var rating : Float = restaurants.getRating(day, month)
        if (rating != -1f) {
            ratingLabelTV.text = "Your rating:"
            ratingBar.rating = rating
            visitedTV.text = ""
        }

        ratingBar.setOnRatingBarChangeListener(this)
    }

    fun map (v : View) {
        var intent : Intent = Intent(this, MapActivity::class.java)
        val animationBundle = ActivityOptions.makeCustomAnimation(this, R.anim.fade_size,R.anim.slide_in).toBundle()
        startActivity(intent, animationBundle)
    }

    fun calendar (v : View) {
        var intent : Intent = Intent(this, CalendarActivity::class.java)
        val animationBundle = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in,R.anim.fade_in).toBundle()
        startActivity(intent, animationBundle)
    }

    override fun onRatingChanged (ratingBar : RatingBar?, rating : Float, fromUser : Boolean) {
        restaurants.setRating(rating, day, month)
        var adUnitId : String = "ca-app-pub-3940256099942544/1033173712"
        var adRequest : AdRequest = (AdRequest.Builder()).build()
        var adLoad : AdLoad = AdLoad()
        InterstitialAd.load(this, adUnitId, adRequest, adLoad)
    }

    inner class AdLoad : InterstitialAdLoadCallback() {
        override fun onAdFailedToLoad (p0: LoadAdError) {
            super.onAdFailedToLoad(p0)
            Log.w("MainActivity", "Ad failed to load")
        }

        override fun onAdLoaded (p0: InterstitialAd) {
            super.onAdLoaded(p0)

            ad = p0
            ad!!.show(this@MainActivity)

            var manageAd : ManageAdShowing = ManageAdShowing()
            ad!!.fullScreenContentCallback = manageAd
        }
    }

    inner class ManageAdShowing : FullScreenContentCallback() {
        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
        }

        override fun onAdImpression() {
            super.onAdImpression()
        }

        override fun onAdShowedFullScreenContent() {
            super.onAdShowedFullScreenContent()
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            super.onAdFailedToShowFullScreenContent(p0)
        }
    }

    companion object {
        lateinit var restaurants : Restaurants
    }
}

