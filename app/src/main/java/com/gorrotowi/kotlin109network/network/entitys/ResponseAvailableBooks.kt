package com.gorrotowi.kotlin109network.network.entitys

import com.google.gson.annotations.SerializedName

data class ResponseAvailableBooks(

    @field:SerializedName("payload")
    val payload: List<PayloadItemAvailableBooks?>? = null,

    @field:SerializedName("success")
    val success: Boolean? = null
)

data class PayloadItemAvailableBooks(

    @field:SerializedName("minimum_price")
    val minimumPrice: String? = null,

    @field:SerializedName("maximum_price")
    val maximumPrice: String? = null,

    @field:SerializedName("book")
    val book: String? = null,

    @field:SerializedName("minimum_value")
    val minimumValue: String? = null,

    @field:SerializedName("maximum_amount")
    val maximumAmount: String? = null,

    @field:SerializedName("maximum_value")
    val maximumValue: String? = null,

    @field:SerializedName("minimum_amount")
    val minimumAmount: String? = null
)