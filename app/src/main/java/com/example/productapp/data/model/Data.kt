package com.example.productapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data (

  @Json(name="products" ) var products : List<BankProducts> = listOf()

)