package com.example.myapplication.screens.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.RestaurantItemViewBinding
import com.example.myapplication.singletonData.Restaurant

class RestaurantViewHolder(private val binding: RestaurantItemViewBinding ): RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentRestaurantData: Restaurant
    private lateinit var callback: OnRestaurantItemClick
    init {
        binding.root.setOnClickListener{ callback.onClickItem(binding.root, currentRestaurantData) }
        binding.root.setOnLongClickListener {
            callback.onLongClick(binding.root)
            true
        }
    }

    fun bindData(restaurant: Restaurant, callback: OnRestaurantItemClick) {
        currentRestaurantData = restaurant
        this.callback = callback
        binding.restaurantData = restaurant
        Glide.with(binding.root.context).load(restaurant.avatar).centerCrop().into(binding.restaurantAvt)
    }

    companion object {
        fun from(parent: ViewGroup): RestaurantViewHolder {
            val binding: RestaurantItemViewBinding = RestaurantItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RestaurantViewHolder(binding)
        }

    }
}