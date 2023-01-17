package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnboadingItemsAdapter(private val onboadingItems: List<OnboardingItem>):RecyclerView.Adapter<OnboadingItemsAdapter.OnboardingItemViewHolder>() {
    inner class OnboardingItemViewHolder(view:View): RecyclerView.ViewHolder(view){
        private val imageOnboarding = view.findViewById<ImageView>(R.id.imageViewOnboard)
        private val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)

        fun bind(onboardingItem: OnboardingItem)
        {
            imageOnboarding.setImageResource(onboardingItem.onboardingImage)
            textViewTitle.text = onboardingItem.textViewTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item_container,parent,false)
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboadingItems[position])
    }

    override fun getItemCount(): Int {
        return onboadingItems.size
    }
}