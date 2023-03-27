package com.example.productapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Benefits(
    @Json(name="summary-title")
    var summary_title:String? = "",
    @Json(name="description")
    var description:String? = "",
    @Json(name="image")
    var image:String? = ""
)
