package com.example.shopapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopapp.R
import com.example.shopapp.adapter.ListItemsAdapter
import com.example.shopapp.databinding.ActivityListItemsBinding
import com.example.shopapp.viewmodel.MainViewModel

class ListItemsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListItemsBinding
    private val viewModel: MainViewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()
    }

    private fun getBundle(){
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!
        binding.categoryTxt.text = title
    }

    private fun initList(){
        binding.apply {
            progressBarList.visibility = View.VISIBLE
            backBtn.setOnClickListener {
                finish()
            }
            viewModel.recommend.observe(this@ListItemsActivity){
                viewList.layoutManager = GridLayoutManager(this@ListItemsActivity,2)
                viewList.adapter = ListItemsAdapter(it)
                progressBarList.visibility = View.GONE
            }
            viewModel.loadFiltered(id)
        }
    }

}