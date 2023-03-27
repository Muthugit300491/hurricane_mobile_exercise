package com.example.productapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BankProducts(
    @Json(name = "product-name")
    var product_name: String? = "",
    @Json(name = "product-description")
    var product_description: String? = "",
    @Json(name = "product-url")
    var product_url: String? = "",
    @Json(name = "product-image-landscape")
    var product_image_landscape: String? = "",
    @Json(name = "cta-label-primary")
    var cta_label_primary: String? = "",
    @Json(name = "cta-url-primary")
    var cta_url_primary: String? = "",
    @Json(name = "benefits-title")
    var benefits_title: String? = "",
    @Json(name = "benefits")
    var benefits: List<Benefits> = listOf(),
    @Json(name = "features-title")
    var features_title: String? = "",
    @Json(name = "features")
    var features: List<Features> = listOf(),
    @Json(name = "product-code")
    var product_code: String? = "",
    @Json(name = "category-name")
    var category_name: String? = "",
    @Json(name = "category-code")
    var category_code: String? = "",
    @Json(name = "promotions")
    var promotions: List<Promotions> = listOf()

)
