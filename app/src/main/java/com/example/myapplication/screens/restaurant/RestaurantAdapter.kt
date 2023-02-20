package com.example.myapplication.screens.restaurant

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.singletonData.Restaurant

class RestaurantAdapter: ListAdapter<Restaurant, RestaurantViewHolder>(RestaurantDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder.from(parent){ restaurants, position -> setList(restaurants, position) }
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val idol = getItem(position)
        holder.bindData(idol)
    }

    override fun getItemCount(): Int {
        return when (val count = super.getItemCount()) {
            0 -> 1
            else -> count
        }
    }

    class RestaurantDiffUtil: DiffUtil.ItemCallback<Restaurant>()   {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.resName == newItem.resName &&
                    oldItem.address == newItem.address &&
                    oldItem.avatar == newItem.avatar
        }

    }

    class RestaurantDiffUtilCallback(private val oldList: MutableList<Restaurant>, private val newList: MutableList<Restaurant>): DiffUtil.Callback()
    {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].avatar == newList[newItemPosition].avatar &&
                    oldList[oldItemPosition].resName == newList[newItemPosition].resName &&
                    oldList[oldItemPosition].address == newList[newItemPosition].address
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }

    }

    private fun setList(newList: MutableList<Restaurant>, position: Int) {
//        DataStore.restaurantData.value?.let { RestaurantDiffUtilCallback(it, newList) }
//            ?.let { DiffUtil.calculateDiff(it).dispatchUpdatesTo(this) }
////
//        DataStore.restaurantData.value?.clear()
//        DataStore.restaurantData.value?.addAll(newList)
        notifyItemRemoved(position)
    }
}