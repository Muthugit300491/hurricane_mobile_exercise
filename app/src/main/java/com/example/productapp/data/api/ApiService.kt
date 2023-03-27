package com.aezion.aerouting.driver.data.api


import com.example.productapp.data.model.BankProductDetails
import com.example.productapp.data.model.ProductDetails
import retrofit2.http.*

interface ApiService {

    /*Flow API call */

    @GET("sg/rtob/categories.json")
    suspend fun getProductChart(): ProductDetails

    @GET
    suspend fun getproductDetails(@Url  url:String):BankProductDetails



}