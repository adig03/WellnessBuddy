package com.example.wellnessbuddy.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.wellnessbuddy.Onboarding.models.OnboardingItem
import com.example.wellnessbuddy.R
import com.example.wellnessbuddy.auth.AuthActivity
import com.google.android.material.button.MaterialButton

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var onBoardingItemAdapter: OnBoardingItemAdapter
    private lateinit var indicatorsContainer: LinearLayout
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private val autoSlideDelay: Long = 4000 // Set the delay (in milliseconds)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicators(0)
        startAutoSlide()
    }

    private fun setOnboardingItems() {
        onBoardingItemAdapter = OnBoardingItemAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.therapist,
                    title = "Welcome to Wellness Buddy",
                    description = "Your go-to app for mental wellness, combining therapy, relaxation tools, and community support. " +
                            "Wellness Buddy empowers you to prioritize mental health, one mindful moment at a time."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.meditation,
                    title = "Schedule your meditation",
                    description = "Create a meditation routine that fits your lifestyle with easy scheduling." +
                            "Stay consistent, relax deeply, and make mindfulness a daily habit."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.community,
                    title = "Mindful connections",
                    description = "Connect with like-minded individuals on their wellness journey." +
                            "Share experiences, find support, and grow together in a safe, encouraging space."
                )
            )
        )

        val onboardingViewPager = findViewById<ViewPager2>(R.id.onBoardingViewPager)
        onboardingViewPager.adapter = onBoardingItemAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
                currentPage = position // Update currentPage on manual swipe
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onBoardingItemAdapter.itemCount) {
                onboardingViewPager.currentItem += 1
            } else {
                navigationToAuthActivity()
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigationToAuthActivity()
        }
        findViewById<MaterialButton>(R.id.btnGetStarted).setOnClickListener {
            navigationToAuthActivity()
        }
    }

    private fun navigationToAuthActivity() {
        startActivity(Intent(applicationContext, AuthActivity::class.java))
        finish()
    }

    private fun setupIndicators() {
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onBoardingItemAdapter.itemCount)

        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicators(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

    private fun startAutoSlide() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                val onboardingViewPager = findViewById<ViewPager2>(R.id.onBoardingViewPager)
                if (currentPage == onBoardingItemAdapter.itemCount - 1) {
                    currentPage = 0 // Reset to first item
                } else {
                    currentPage++ // Move to next item
                }
                onboardingViewPager.setCurrentItem(currentPage, true) // Smooth scroll
                handler.postDelayed(this, autoSlideDelay) // Repeat after delay
            }
        }, autoSlideDelay)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Stop the auto slide when activity is destroyed
    }
}
