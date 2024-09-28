package com.example.shopapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.databinding.ViewholderRecommendedBinding
import com.example.shopapp.model.ItemsModel

class RecommendedAdapter(private val items: MutableList<ItemsModel>) : RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderRecommendedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =  items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding){
            titleText.text = item.title
            tvPrice.text = "$${item.price}"
            tvStar.text = item.rating.toString()

            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .into(imgDescription)

            root.setOnClickListener {

            }
        }
    }

    class ViewHolder(val binding: ViewholderRecommendedBinding):RecyclerView.ViewHolder(binding.root)

}