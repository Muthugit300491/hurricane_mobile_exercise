package com.aezion.aerouting.driver.data.repository


import com.aezion.aerouting.driver.data.api.ApiHelper
import com.example.productapp.data.model.ProductDetails
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    fun getProduct() =  apiHelper.getProductDetils()
    fun getBankProductDetails(url:String)=apiHelper.getBankProductetails(url)

}