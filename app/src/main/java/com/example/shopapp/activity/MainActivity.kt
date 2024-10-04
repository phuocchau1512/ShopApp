package com.example.shopapp.activity

import SliderAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.shopapp.adapter.CategoryAdapter
import com.example.shopapp.adapter.RecommendedAdapter
import com.example.shopapp.databinding.ActivityMainBinding
import com.example.shopapp.model.SliderModel
import com.example.shopapp.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()
        initCategory()
        initRecommended()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.tvCart.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    CartActivity::class.java
                )
            )
        }

    }

    private fun initRecommended() {
        binding.progressRecommendation.visibility = View.VISIBLE
        viewModel.recommend.observe(this) {
            binding.recyclerRecommendation.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.recyclerRecommendation.adapter = RecommendedAdapter(it)
            binding.progressRecommendation.visibility = View.GONE
        }
        viewModel.loadRecommend()
    }

    private fun initCategory() {
        binding.progressBarCategories.visibility = View.VISIBLE
        viewModel.category.observe(this) {
            binding.recyclerViewCategories.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewCategories.adapter = CategoryAdapter(it)
            binding.progressBarCategories.visibility = View.GONE
        }
        viewModel.loadCategory()
    }

    private fun initBanner(){
        binding.progressSlider.visibility = View.VISIBLE
        viewModel.banner.observe(this) {
            banners(it)
            binding.progressSlider.visibility = View.GONE
        }
        viewModel.loadBanners()
    }

    private fun banners(image:List<SliderModel>){
        binding.viewPager2.adapter = SliderAdapter(image,binding.viewPager2)
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPager2.setPageTransformer(compositePageTransformer)

        if ( image.size > 1 ){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPager2)
        }

    }

}