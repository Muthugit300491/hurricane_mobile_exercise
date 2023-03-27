package com.example.productapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Promotions(
    @Json(name="campaign-code" )
    var campaign_code : String? = ""
)
