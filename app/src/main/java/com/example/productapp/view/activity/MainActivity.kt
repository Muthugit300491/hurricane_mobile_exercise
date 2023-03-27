package com.example.productapp.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aezion.aerouting.driver.data.repository.Resource
import com.aezion.aerouting.driver.data.repository.StatusCalled
import com.example.productapp.R
import com.example.productapp.data.model.Categories
import com.example.productapp.data.model.ProductDetails
import com.example.productapp.databinding.ActivityMainBinding
import com.example.productapp.utils.getPdialog
import com.example.productapp.utils.setRecyclerView
import com.example.productapp.utils.showToast
import com.example.productapp.view.adapter.CategoryAdapter
import com.example.productapp.viewodel.ProductViewModel
import com.kotlin.employee.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var pDialog: AlertDialog
    private lateinit var  activityMainBinding: ActivityMainBinding
    private  val productViewModel:ProductViewModel by viewModels()
    private var categoryAdapter:CategoryAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature( Window.FEATURE_ACTION_BAR)
        supportActionBar?.hide()
        activityMainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        pDialog = getPdialog()
        pDialog.dismiss()
        setAdapter()
        val sharedPreferences =
            this.getSharedPreferences(Utils.PRODUCTPREFERENCE, Context.MODE_PRIVATE)
        activityMainBinding.tvLogout.setOnClickListener {
            val prefs= sharedPreferences.edit()
            prefs.remove("username")
            prefs.apply()
           val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

       getProuctDetails()
    }

    fun getProuctDetails(){
        val mLiveData = MutableLiveData<Resource<ProductDetails>>()
        productViewModel.getProductDetails(mLiveData).observe(this, Observer {

            when (it.status) {
                StatusCalled.SUCCESS -> {
                    pDialog.dismiss()
                   if(it.data?.categories?.size!!>0){
                        renderList(it.data.categories)
                   }else{
                       categoryAdapter?.addItems(arrayListOf())
                       showToast("No category and products found")
                   }
                }
                StatusCalled.LOADING -> {
                    pDialog.show()
                }
                StatusCalled.ERROR -> {
                    //Handle Error
                    pDialog.dismiss()
                    showToast(it.message)
                }
            }

        })
    }

    fun renderList(categroies:List<Categories>){
       categoryAdapter?.addItems(categroies)
    }

    fun setAdapter(){
         categoryAdapter= CategoryAdapter(this)
        setRecyclerView(activityMainBinding.categoryListId)
        activityMainBinding.categoryListId.adapter=categoryAdapter

    }
}