package com.aezion.aerouting.driver.data.api


import com.example.productapp.data.model.BankProductDetails
import com.example.productapp.data.model.ProductDetails

import kotlinx.coroutines.flow.Flow


interface ApiHelper {

    fun getProductDetils(): Flow<ProductDetails>
    fun getBankProductetails(url:String):Flow<BankProductDetails>


}