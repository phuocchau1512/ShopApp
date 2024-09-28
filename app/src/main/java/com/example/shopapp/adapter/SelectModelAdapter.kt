package com.example.shopapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.databinding.ViewholderModelBinding

class SelectModelAdapter(val items: MutableList<String>):
    RecyclerView.Adapter<SelectModelAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderModelBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.modelTxt.text = items[position]

        if ( selectedPosition == position ){
            holder.binding.modelLayout.setBackgroundResource(R.drawable.green_bg_seleteced)
            val greenColor = ContextCompat.getColor(holder.itemView.context, R.color.green)
            holder.binding.modelTxt.setTextColor(greenColor)
        }
        else {
            holder.binding.modelLayout.setBackgroundResource(R.drawable.grey_bg)
            val blackColor = ContextCompat.getColor(holder.itemView.context, R.color.black)
            holder.binding.modelTxt.setTextColor(blackColor)
        }

    }

    inner class ViewHolder(val binding:ViewholderModelBinding): RecyclerView.ViewHolder(binding.root){
        init{
            binding.root.setOnClickListener {
                val position = adapterPosition
                if ( position != RecyclerView.NO_POSITION ){
                    lastSelectedPosition = selectedPosition
                    selectedPosition = position
                    notifyItemChanged(lastSelectedPosition)
                    notifyItemChanged(selectedPosition)
                }
            }
        }
    }

}