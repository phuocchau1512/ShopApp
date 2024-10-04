package com.example.shopapp.activity

import ManagmentCart
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.adapter.PicAdapter
import com.example.shopapp.adapter.SelectModelAdapter
import com.example.shopapp.databinding.ActivityDetailBinding
import com.example.shopapp.model.ItemsModel

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        getBundle()

        initList()



    }

    private fun initList() {
        val modelList = ArrayList<String>()
        for(model in item.model){
            modelList.add(model)
        }

        binding.modelList.adapter = SelectModelAdapter(modelList)
        binding.modelList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val picList = ArrayList<String>()
        for( url in item.picUrl ){
            picList.add(url)
        }

        Glide.with(this).load(picList[0]).into(binding.img)

        binding.picList.adapter = PicAdapter(picList){selectedImage->
                Glide.with(this)
                    .load(selectedImage)
                    .into(binding.img)
        }
        binding.picList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$"+item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.addCartBtn.setOnClickListener{
            item.numberInCart = numberOrder
            managmentCart.insertItem(item)
        }
        binding.backBtn.setOnClickListener{finish()}
        binding.carBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity,CartActivity::class.java))
        }
    }


}