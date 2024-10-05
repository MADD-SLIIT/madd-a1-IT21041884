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

class BrandAdapter(val items:MutableList<BrandModel>): RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderBrandBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.ViewHolder {
        context=parent.context
        val binding=ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picURL)
            .into(holder.binding.profilepic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
        if(selectedPosition==position){
            holder.binding.profilepic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.button_darkblue)
            ImageViewCompat.setImageTintList(holder.binding.profilepic, ColorStateList.valueOf(context.getColor(R.color.white)))

            holder.binding.title.visibility = View.VISIBLE
    } else{
            holder.binding.profilepic.setBackgroundResource(R.drawable.button_lightblue)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.profilepic, ColorStateList.valueOf(context.getColor(R.color.black)))

            holder.binding.title.visibility = View.GONE
        }
    }
    override fun getItemCount(): Int = items.size
}