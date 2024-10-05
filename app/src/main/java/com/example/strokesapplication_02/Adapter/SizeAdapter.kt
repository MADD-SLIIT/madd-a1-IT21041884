package com.example.strokesapplication_02.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.strokesapplication_02.Model.BrandModel
import com.example.strokesapplication_02.R
import com.example.strokesapplication_02.databinding.ActivityMainBinding
import com.example.strokesapplication_02.databinding.ViewholderBrandBinding
import com.example.strokesapplication_02.databinding.ViewholderCopyBinding
import com.example.strokesapplication_02.databinding.ViewholderSizeBinding

class SizeAdapter(val items:MutableList<String>): RecyclerView.Adapter<SizeAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderSizeBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.ViewHolder {
        context=parent.context
        val binding=ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeAdapter.ViewHolder, position: Int) {

        holder.binding.copiesTxt.text = items[position]

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition==position){

            holder.binding.copyLayout.setBackgroundResource(R.drawable.button_darkblue)
            holder.binding.copiesTxt.setTextColor(context.resources.getColor(R.color.white))

    } else{
            holder.binding.copyLayout.setBackgroundResource(R.drawable.button_lightblue)
            holder.binding.copiesTxt.setTextColor(context.resources.getColor(R.color.darkBlue))

        }
    }
    override fun getItemCount(): Int = items.size
}