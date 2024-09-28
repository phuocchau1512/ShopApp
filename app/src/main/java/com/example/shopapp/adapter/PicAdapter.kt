package com.example.shopapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.ViewholderPicBinding

class PicAdapter(
    private val items:MutableList<String>,
    private val onImageSelected: (String) -> Unit): RecyclerView.Adapter<PicAdapter.ViewHolder>() {


    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderPicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.pic.loadImage(item)



        if ( selectedPosition == position ){
            holder.binding.picLayout.setBackgroundResource(R.drawable.green_button_bg)
        }
        else {
            holder.binding.picLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    private fun ImageView.loadImage(url: String){
        Glide.with(this.context)
            .load(url)
            .into(this)
    }

    inner class ViewHolder(val binding: ViewholderPicBinding):RecyclerView.ViewHolder(binding.root){
        init{
            binding.root.setOnClickListener {
                val position = adapterPosition
                if ( position != RecyclerView.NO_POSITION ){
                    lastSelectedPosition = selectedPosition
                    selectedPosition = position
                    notifyItemChanged(lastSelectedPosition)
                    notifyItemChanged(selectedPosition)

                    onImageSelected(items[position])
                }
            }
        }
    }

}