package com.example.myapplication.screens.restaurant

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRestaurantBinding
import com.example.myapplication.singletonData.DataStore

class RestaurantActivity : AppCompatActivity() {
    lateinit var binding: ActivityRestaurantBinding
    lateinit var adapter: RestaurantAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant)
        binding.lifecycleOwner = this

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvRestaurant.layoutManager = LinearLayoutManager(this)
        adapter = RestaurantAdapter()
        binding.rvRestaurant.adapter = adapter

        run {
            adapter.submitList(DataStore.restaurantData.value)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_restaurant, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.menu_item_linear -> binding.rvRestaurant.layoutManager = LinearLayoutManager(this)
            R.id.menu_item_grid -> binding.rvRestaurant.layoutManager = GridLayoutManager(this, 2)
        }

        return super.onOptionsItemSelected(item)
    }
}