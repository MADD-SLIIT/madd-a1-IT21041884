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

class CopyAdapter(val items:MutableList<String>): RecyclerView.Adapter<CopyAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderCopyBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CopyAdapter.ViewHolder {
        context=parent.context
        val binding=ViewholderCopyBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CopyAdapter.ViewHolder, position: Int) {



        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition==position){

            holder.binding.copyLayout.setBackgroundResource(R.drawable.button_darkblue)

    } else{
            holder.binding.copyLayout.setBackgroundResource(R.drawable.button_lightblue)

        }
    }
    override fun getItemCount(): Int = items.size
}