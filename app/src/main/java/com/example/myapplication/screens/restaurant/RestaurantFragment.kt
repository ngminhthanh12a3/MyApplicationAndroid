package com.example.myapplication.screens.restaurant

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRestaurantBinding
import com.example.myapplication.singletonData.DataStore
import com.example.myapplication.singletonData.Restaurant
import com.example.myapplication.viewModels.RestaurantViewModel
/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantFragment : Fragment() {
    lateinit var binding: FragmentRestaurantBinding
    lateinit var adapter: RestaurantAdapter
    lateinit var viewModel: RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]

        setUpRecyclerView()
        setUpViewModelObservers()
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "My restaurants"
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity?)!!.supportActionBar?.hide()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestaurantFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun setUpViewModelObservers() {
        viewModel.menuLayoutType.observe(viewLifecycleOwner){
            binding.rvRestaurant.layoutManager = activity?.let { it1 -> viewModel.getLayout(it1) }
        }

        viewModel.newList.observe(viewLifecycleOwner){
            setList(it)
        }
    }

    private fun setUpRecyclerView() {
        binding.rvRestaurant.layoutManager = activity?.let { viewModel.getLayout(it) }
        adapter = RestaurantAdapter(onRestaurantItemClick)
        binding.rvRestaurant.adapter = adapter

        run {
            adapter.submitList(DataStore.restaurantData.value)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_restaurant, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_item_linear -> viewModel.setLayout(item.itemId)
            R.id.menu_item_grid -> viewModel.setLayout(item.itemId)
            R.id.menu_item_avatar -> {
                val controller = findNavController()
                controller.navigate(R.id.action_restaurantFragment_to_profileFragment)
                (activity as AppCompatActivity?)!!.supportActionBar?.hide()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private val onRestaurantItemClick = object : OnRestaurantItemClick{
        override fun onClickItem(item: View, restaurant: Restaurant) {
            viewModel.handleItemWhenClicked(item, restaurant)
        }

        override fun onLongClick(item: View) {
            viewModel.handleItemWhenLongClicked(item)
        }
    }

    private fun setList(newList: MutableList<Restaurant>) {
        DataStore.restaurantData.value?.let {
            RestaurantAdapter.RestaurantDiffUtilCallback(
                it,
                newList
            )
        }
            ?.let { DiffUtil.calculateDiff(it).dispatchUpdatesTo(adapter) }
        DataStore.restaurantData.value?.clear()
        DataStore.restaurantData.value?.addAll(newList)
    }
}