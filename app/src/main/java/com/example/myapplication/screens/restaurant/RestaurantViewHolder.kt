package com.example.myapplication.screens.restaurant

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.RestaurantItemViewBinding
import com.example.myapplication.singletonData.DataStore
import com.example.myapplication.singletonData.Restaurant

class RestaurantViewHolder(private val binding: RestaurantItemViewBinding, private val setList: (MutableList<Restaurant>) -> Unit): RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentRestaurantData: Restaurant
    init {
        binding.root.setOnClickListener{ showDialog(currentRestaurantData) }
    }

    fun bindData(restaurant: Restaurant) {
        currentRestaurantData = restaurant
        binding.restaurantData = restaurant
        Glide.with(binding.root.context).load(restaurant.avatar).centerCrop().into(binding.restaurantAvt)
    }

    private fun showDialog(restaurant: Restaurant) {

        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(binding.root.context)
        alertDialog.setTitle("Confirm")
        alertDialog.setIcon(R.mipmap.ic_launcher)
        alertDialog.setMessage("Do you want to delete this restaurant?")

        alertDialog.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            val newList: MutableList<Restaurant> = mutableListOf()
            DataStore.restaurantData.value?.let { newList.addAll(it) }
            newList.remove(restaurant)
            setList(newList)
        }
        alertDialog.setNegativeButton("No"){ _: DialogInterface, _: Int ->

        }

        alertDialog.show()
    }
    companion object {
        fun from(parent: ViewGroup, setList: (MutableList<Restaurant>) -> Unit): RestaurantViewHolder {
            val binding: RestaurantItemViewBinding = RestaurantItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RestaurantViewHolder(binding) { setList(it) }
        }

    }
}