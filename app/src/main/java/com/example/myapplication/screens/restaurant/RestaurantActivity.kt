package com.example.myapplication.screens.restaurant

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRestaurantBinding
import com.example.myapplication.viewModels.RestaurantViewModel

class RestaurantActivity : AppCompatActivity() {
    lateinit var binding: ActivityRestaurantBinding
    lateinit var viewModel: RestaurantViewModel
    lateinit var adapter: RestaurantAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant)
        viewModel =ViewModelProvider(this)[RestaurantViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.listOfRestaurant.observe(this) { data ->
            run {
                adapter.submitList(data)
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvRestaurant.layoutManager = LinearLayoutManager(this)
        adapter = RestaurantAdapter()
        binding.rvRestaurant.adapter = adapter
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