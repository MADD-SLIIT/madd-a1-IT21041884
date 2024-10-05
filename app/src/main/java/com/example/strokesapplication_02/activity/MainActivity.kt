package com.example.strokesapplication_02.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.strokesapplication_02.Adapter.BrandAdapter
import com.example.strokesapplication_02.Adapter.PopularAdapter
import com.example.strokesapplication_02.Model.SliderModel
import com.example.strokesapplication_02.R
import com.example.strokesapplication_02.Adapter.SliderAdapter
import com.example.strokesapplication_02.ViewModel.MainViewModel
import com.example.strokesapplication_02.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val viewModel=MainViewModel()
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        initBanner()
        initBrand()
        initPopular()
    //    initBottomMenu()

        }

    /*private fun initBottomMenu() {
        binding.cartButton.setOnClickListener { startActivity(Intent(this@MainActivity, CartActivity::class.java))}
    }
    */


    private fun initBanner() {
        binding.bannerProgressBar.visibility= View.VISIBLE
        viewModel.banners.observe(
            this,
            { items ->
                banners(items)
                binding.bannerProgressBar.visibility = View.GONE
            },
        )
    }

    private fun banners(images:List<SliderModel>){
        binding.viewpagerSlider.adapter= SliderAdapter(images,binding.viewpagerSlider)
        binding.viewpagerSlider.clipToPadding = false
        binding.viewpagerSlider.clipChildren = false
        binding.viewpagerSlider.offscreenPageLimit = 3
        binding.viewpagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewpagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1){
            binding.dotsindicator.visibility = View.VISIBLE
            binding.dotsindicator.attachTo(binding.viewpagerSlider)
        }
}

    private fun initBrand(){
        binding.coursesProgressBar.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer {
            binding.viewCourses.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewCourses.adapter = BrandAdapter(it)
            binding.coursesProgressBar.visibility = View.GONE
        })
        viewModel.loadBrand()
    }

    private fun initPopular(){
        binding.recommendedProgressBar.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.viewRecommended.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.viewRecommended.adapter = PopularAdapter(it)
            binding.recommendedProgressBar.visibility = View.GONE
        })
        viewModel.loadPopular()
    }



}