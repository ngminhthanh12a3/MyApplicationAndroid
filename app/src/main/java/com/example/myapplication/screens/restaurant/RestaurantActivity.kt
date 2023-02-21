package com.example.myapplication.screens.restaurant

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRestaurantBinding
import com.example.myapplication.singletonData.DataStore
import com.example.myapplication.viewModels.RestaurantViewModel

class RestaurantActivity : AppCompatActivity() {
    lateinit var binding: ActivityRestaurantBinding
    lateinit var adapter: RestaurantAdapter
    lateinit var viewModel: RestaurantViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]

        setUpRecyclerView()
        setUpViewModelObservers()
    }

    private fun setUpViewModelObservers() {
        viewModel.menuLayoutType.observe(this){
            binding.rvRestaurant.layoutManager = viewModel.getLayout(this)
        }
    }

    private fun setUpRecyclerView() {
        binding.rvRestaurant.layoutManager = viewModel.getLayout(this)
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
        viewModel.setLayout(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}