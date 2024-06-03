package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import android.widget.RatingBar
import android.widget.TextView
import com.example.finalproject.R
import org.w3c.dom.Text
import java.util.Calendar

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        var calendar: CalendarView? = findViewById(R.id.calendar)
        var date: TextView? = findViewById(R.id.date)
        var title: TextView? = findViewById(R.id.title)
        var ratingBar:RatingBar = findViewById(R.id.ratingsBar)
        var cal : Calendar = Calendar.getInstance()
        var day : Int = cal.get(Calendar.DAY_OF_MONTH)
        var month : Int = cal.get(Calendar.MONTH)
        var year : Int = cal.get(Calendar.YEAR)

        val dateT = (month + 1).toString() + "/" + day + "/" + year
        date!!.text = dateT
        title!!.text = MainActivity.restaurants.getRestaurant(day, month)
        ratingBar.rating = MainActivity.restaurants.getRating(day, month)

        calendar!!.setOnDateChangeListener { view, year, month, day ->
            val dateText = (month + 1).toString() + "/" + day + "/" + year
            date!!.text = dateText

            title!!.text = MainActivity.restaurants.getRestaurant(day, month)
            ratingBar.rating = MainActivity.restaurants.getRating(day, month)
        }
    }

    fun home (v : View) {
        finish()
    }
}