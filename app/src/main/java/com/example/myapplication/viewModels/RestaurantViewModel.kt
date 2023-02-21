package com.example.myapplication.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.singletonData.DataStore
import com.example.myapplication.singletonData.Restaurant

class RestaurantViewModel: ViewModel() {
    val listOfRestaurant: LiveData<MutableList<Restaurant>>
        get() = DataStore.restaurantData
}