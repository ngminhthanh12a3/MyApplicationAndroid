package com.example.myapplication.viewModels

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.singletonData.DataStore
import com.example.myapplication.singletonData.Restaurant

class RestaurantViewModel : ViewModel() {

    private val _newList: MutableLiveData<MutableList<Restaurant>> = MutableLiveData()
    val newList: LiveData<MutableList<Restaurant>>
        get() = _newList

    private val _menuLayoutType: MutableLiveData<Int> = MutableLiveData(R.id.menu_item_linear)
    val menuLayoutType: LiveData<Int>
        get() = _menuLayoutType

    fun getLayout(restaurantActivity: Activity): RecyclerView.LayoutManager {
        return when (_menuLayoutType.value) {
            R.id.menu_item_linear -> LinearLayoutManager(restaurantActivity)
            else -> {GridLayoutManager(restaurantActivity, 2)}
        }
    }

    fun setLayout(itemId: Int) {
        _menuLayoutType.postValue(itemId)
    }

    fun handleItemWhenClicked(item: View, restaurant: Restaurant) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(item.context)
        alertDialog.setTitle("Confirm")
        alertDialog.setIcon(R.mipmap.ic_launcher)
        alertDialog.setMessage("Do you want to delete this restaurant?")

        alertDialog.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            val newList: MutableList<Restaurant> = mutableListOf()
            DataStore.restaurantData.value?.let { newList.addAll(it) }
            newList.remove(restaurant)
            _newList.postValue(newList)
        }
        alertDialog.setNegativeButton("No"){ _: DialogInterface, _: Int ->

        }

        alertDialog.show()
    }

    fun handleItemWhenLongClicked(item: View) {
        /// TODO
    }

}