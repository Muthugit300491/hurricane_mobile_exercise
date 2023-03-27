package com.example.productapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aezion.aerouting.driver.data.api.APIcallClient
import com.aezion.aerouting.driver.data.repository.Resource
import com.aezion.aerouting.driver.data.repository.StatusCalled
import com.example.productapp.R
import com.example.productapp.data.model.BankProductDetails
import com.example.productapp.data.model.BankProducts
import com.example.productapp.data.model.ProductDetails
import com.example.productapp.databinding.ActivityProductDetailsBinding
import com.example.productapp.utils.getPdialog
import com.example.productapp.utils.setRecyclerView
import com.example.productapp.utils.showToast
import com.example.productapp.view.adapter.ProuctDetailsAdapter
import com.example.productapp.viewodel.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var pDialog: AlertDialog
    private lateinit var binding: ActivityProductDetailsBinding
    private val productDetailsViewModel:ProductDetailsViewModel by viewModels()
    private lateinit var productDetailsAdapter: ProuctDetailsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature( Window.FEATURE_ACTION_BAR)
        supportActionBar?.hide()
        binding= ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pDialog = getPdialog()
        pDialog.dismiss()

        setAdapter()

        binding.ivBack.setOnClickListener { finish() }

       val url= intent.getStringExtra("product_url")?.let {
          it.replace("https://av.sc.com/","")
       }

        Log.e("Proucturl", url!!)
            getBankPrductDetails(url)

    }

    fun getBankPrductDetails(url:String){
        val mLiveData = MutableLiveData<Resource<BankProductDetails>>()
        productDetailsViewModel.getBankProductDetails(url,mLiveData).observe(this, Observer {

            when (it.status) {
                StatusCalled.SUCCESS -> {
                    pDialog.dismiss()
                    if(it.data?.data?.products?.size!!>0){
                        renderList(it.data.data?.products!!)
                    }else{
//                        categoryAdapter?.addItems(arrayListOf())
                        showToast("No Product Details  found")
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

    fun renderList(products:List<BankProducts>){
        productDetailsAdapter.addItems(products)
        Log.e("products",""+products.size)
    }

    fun setAdapter()
    {
        productDetailsAdapter= ProuctDetailsAdapter(this@ProductDetailsActivity)
        setRecyclerView(binding.productDetailsId)
        binding.productDetailsId.adapter=productDetailsAdapter
    }
}