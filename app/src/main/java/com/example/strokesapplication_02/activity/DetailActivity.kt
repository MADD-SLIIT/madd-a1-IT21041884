package com.example.strokesapplication_02.activity

import android.content.Intent
import android.os.Bundle
import android.util.Size
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1762.Helper.ManagementCart
import com.example.strokesapplication_02.Adapter.SizeAdapter
import com.example.strokesapplication_02.Adapter.SliderAdapter
import com.example.strokesapplication_02.Model.ItemsModel
import com.example.strokesapplication_02.Model.SliderModel
import com.example.strokesapplication_02.R
import com.example.strokesapplication_02.databinding.ActivityDetailBinding
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import org.junit.internal.management.ManagementFactory

class DetailActivity : BaseActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOrder = 1
    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)

        getBundle()
        banners()
        initLists()

    }

    private fun initLists() {
        val sizeList = ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }


        binding.copiesList.adapter = SizeAdapter(sizeList)
        binding.copiesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageURL in item.picURL){
            colorList.add(imageURL)
        }

        binding.copiesList.adapter = SizeAdapter(colorList)
        binding.copiesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun banners() {
        val sliderItems = ArrayList<SliderModel>()
        for(imageURL in item.picURL){
            sliderItems.add(SliderModel(imageURL))
        }

        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)
        binding.slider.clipToPadding = true
        binding.slider.clipChildren = true
        binding.slider.offscreenPageLimit = 3


        if(sliderItems.size > 1){
            binding.dotsindicator.visibility = View.VISIBLE
            binding.dotsindicator.attachTo(binding.slider)
        }
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.textView6.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            managementCart.insertFood(item)
        }
        binding.backBtn.setOnClickListener{finish()}
        binding.cartButton.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CartActivity::class.java))
        }
    }
}