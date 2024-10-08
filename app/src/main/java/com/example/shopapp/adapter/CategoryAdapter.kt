package com.example.shopapp.adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.activity.ListItemsActivity
import com.example.shopapp.databinding.ViewholderCategoryBinding
import com.example.shopapp.model.CategoryModel

class CategoryAdapter(private val items: MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

        
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleTxt.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.imgDescription)

        if ( selectedPosition == position ){
            holder.binding.imgDescription.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.green_button_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.imgDescription,
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.white
                    )
                )
            )

            holder.binding.titleTxt.visibility = View.VISIBLE
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
        }
        else{
            holder.binding.imgDescription.setBackgroundResource(R.drawable.green_button_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.imgDescription,
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.black
                    )
                )
            )

            holder.binding.titleTxt.visibility = View.GONE
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )
        }

        holder.binding.root.setOnClickListener {
                lastSelectedPosition = selectedPosition
                selectedPosition = holder.adapterPosition
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(holder.itemView.context,ListItemsActivity::class.java).apply {
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)
                }
                ContextCompat.startActivity(holder.itemView.context,intent,null)
            },300)
        }

    }

    inner class ViewHolder(val binding: ViewholderCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}