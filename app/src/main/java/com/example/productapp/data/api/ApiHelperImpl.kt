package com.aezion.aerouting.driver.data.api


import com.example.productapp.data.model.BankProductDetails
import com.example.productapp.data.model.ProductDetails
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

//class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override fun getProductDetils()= flow {
        emit(apiService.getProductChart())
    }

    override fun getBankProductetails(url:String)=flow{
        emit(apiService.getproductDetails(url))
    }


}


