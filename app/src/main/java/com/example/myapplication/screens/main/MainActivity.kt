package com.example.myapplication.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.screens.onboading.OnboadingItemsAdapter
import com.example.myapplication.screens.onboading.OnboardingItem
import com.example.myapplication.screens.welcome.Welcome

class MainActivity : AppCompatActivity() {
    private lateinit var onboadingItemsAdapter: OnboadingItemsAdapter
    private lateinit var textViewTitleArray:Array<String>
    private lateinit var indicatorContainer:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnboardingItems()
    {
        textViewTitleArray = resources.getStringArray(R.array.text_view_title)
        onboadingItemsAdapter = OnboadingItemsAdapter(listOf(
            OnboardingItem(R.drawable.pic_onboarding_1, textViewTitleArray[0]),
            OnboardingItem(R.drawable.pic_onboarding_2, textViewTitleArray[1]),
            OnboardingItem(R.drawable.pic_onboarding_3, textViewTitleArray[2])
        ))
        val onboardingViewPager = findViewById<ViewPager2>(R.id.viewPager2)
        //
        var index = 0
        var previousState = ViewPager2.SCROLL_STATE_IDLE

        onboardingViewPager.adapter = onboadingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                index = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if ((index >= onboadingItemsAdapter.itemCount - 1)
                    && previousState == ViewPager2.SCROLL_STATE_DRAGGING
                    && state == ViewPager2.SCROLL_STATE_IDLE
                )
                    navigateToWelcomeActivity()

                previousState = state
            }
        })
//        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if(onboardingViewPager.currentItem + 1 < onboadingItemsAdapter.itemCount)
            {
                onboardingViewPager.currentItem += 1
            }
            else
                navigateToWelcomeActivity()
        }
    }

    private fun navigateToWelcomeActivity()
    {
        startActivity(Intent(applicationContext, Welcome::class.java))
        finish()
    }

    private fun setupIndicators()
    {
        indicatorContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboadingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                    R.drawable.indicator_inactive_background
                ))
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position:Int)
    {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount)
        {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if(i == position)
            {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                    R.drawable.indicator_active_background
                ))
            }
            else
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                    R.drawable.indicator_inactive_background
                ))
        }
    }
}