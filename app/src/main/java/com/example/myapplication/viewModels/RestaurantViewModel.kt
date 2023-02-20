package com.example.myapplication.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.singletonData.DataStore
import com.example.myapplication.singletonData.Restaurant
import kotlinx.coroutines.launch

class RestaurantViewModel: ViewModel() {
    val listOfRestaurant: LiveData<MutableList<Restaurant>>
        get() = DataStore.restaurantData

    fun loadData() {
        viewModelScope.launch {
//            val restaurantDataSet = DataStore.restaurantData.value?.subList(0, DataStore.restaurantData.value!!.size)
//            DataStore.restaurantData.postValue(restaurantDataSet)
        }
    }
}