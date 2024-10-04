package com.example.shopapp.adapter

import ManagmentCart
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.databinding.ViewholderCartBinding
import com.example.shopapp.helper.ChangeNumberItemsListener
import com.example.shopapp.model.ItemsModel

class CartAdapter(
    private val listItems: ArrayList<ItemsModel>,
    var context: Context,
    var changeNumberListener: ChangeNumberItemsListener
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val managerCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]

        holder.binding.title.text = item.title
        holder.binding.feeEachTime.text = "$${item.price}"
        holder.binding.totalFeeTv.text = "$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.itemImg)

        holder.binding.plusTxt.setOnClickListener{
            managerCart.plusItem(listItems,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberListener.onChanged()
                }
            })
        }

        holder.binding.minusTxt.setOnClickListener{
            managerCart.plusItem(listItems,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberListener.onChanged()
                }
            })
        }

    }

    class ViewHolder(val binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root)

}