package com.example.shopapp.activity

import ManagmentCart
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shopapp.R
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
        for(model in modelList){
            modelList.add(model)
        }

        
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
        binding.carBtn.setOnClickListener {  }
    }


}