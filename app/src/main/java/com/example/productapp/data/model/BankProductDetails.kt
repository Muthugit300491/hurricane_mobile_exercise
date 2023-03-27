package com.example.productapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BankProductDetails(
    @Json(name = "lang" ) var lang : String? = "",
    @Json(name="data" ) var data : Data?   = Data()

)
