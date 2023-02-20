package com.example.myapplication.screens.restaurant

import android.util.Log
import com.example.myapplication.singletonData.Restaurant

class RestaurantHandler {
    fun onClick (restaurant: Restaurant){
        Log.i("click", restaurant.toString())
    }
}