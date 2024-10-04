package com.example.shopapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.model.CategoryModel
import com.example.shopapp.model.ItemsModel
import com.example.shopapp.model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    val banner: LiveData<List<SliderModel>> = _banner

    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    val category: LiveData<MutableList<CategoryModel>> = _category

    private val _recommend = MutableLiveData<MutableList<ItemsModel>>()
    val recommend :LiveData<MutableList<ItemsModel>> = _recommend

    fun loadFiltered(id:String) {
        val ref = firebaseDatabase.getReference("Items")
        val query = ref.orderByChild("categoryId").equalTo(id)
        query.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (child in snapshot.children) {
                    val item = child.getValue(ItemsModel::class.java)
                    if (item != null) lists.add(item)
                }
                _recommend.postValue(lists) // Sử dụng postValue nếu không ở main thread
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Failed to load recommended: ${error.message}")
            }

        })
    }

    fun loadRecommend() {
        val ref = firebaseDatabase.getReference("Items")
        val query = ref.orderByChild("showRecommended").equalTo(true)
        query.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (child in snapshot.children) {
                    val item = child.getValue(ItemsModel::class.java)
                    if (item != null) lists.add(item)
                }
                _recommend.postValue(lists) // Sử dụng postValue nếu không ở main thread
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Failed to load recommended: ${error.message}")
            }

        })
    }

    fun loadCategory() {
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val lists = mutableListOf<CategoryModel>()
                    for (child in snapshot.children) {
                        val item = child.getValue(CategoryModel::class.java)
                        if (item != null) lists.add(item)
                    }
                    _category.postValue(lists) // Sử dụng postValue nếu không ở main thread
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Failed to load category: ${error.message}")
            }
        })
    }

    fun loadBanners() {
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val lists = mutableListOf<SliderModel>()
                    for (child in snapshot.children) {
                        val item = child.getValue(SliderModel::class.java)
                        if (item != null) lists.add(item)
                    }
                    _banner.postValue(lists) // Sử dụng postValue nếu không ở main thread
                } else {
                    _banner.postValue(emptyList()) // Khi snapshot rỗng
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Failed to load banners: ${error.message}")
            }
        })
    }


}