package com.example.myapplication.screens.restaurant

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.singletonData.Restaurant

interface OnRestaurantItemClick{
    fun onClickItem(item: View, restaurant: Restaurant)

    fun onLongClick(item: View)
}
class RestaurantAdapter(private val callback: OnRestaurantItemClick): ListAdapter<Restaurant, RestaurantViewHolder>(RestaurantDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val idol = getItem(position)
        holder.bindData(idol, callback)
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

    }
}