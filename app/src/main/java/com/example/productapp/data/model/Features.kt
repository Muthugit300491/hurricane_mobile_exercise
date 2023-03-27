package com.example.productapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Features (

  @Json(name="title")
  var title : String? = "",
  @Json(name="data")
  var data  : String? = "",
  @Json(name="icon")
  var icon  : String? = ""

)