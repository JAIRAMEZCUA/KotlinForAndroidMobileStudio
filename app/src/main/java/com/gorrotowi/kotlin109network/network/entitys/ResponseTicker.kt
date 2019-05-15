package com.gorrotowi.kotlin109network.network.entitys

import com.google.gson.annotations.SerializedName

data class ResponseTicker(

    @field:SerializedName("payload")
    val payload: List<PayloadItemTicker?>? = null,

    @field:SerializedName("success")
    val success: Boolean? = null
)

data class PayloadItemTicker(

    @field:SerializedName("volume")
    val volume: String? = null,

    @field:SerializedName("high")
    val high: String? = null,

    @field:SerializedName("last")
    val last: String? = null,

    @field:SerializedName("low")
    val low: String? = null,

    @field:SerializedName("book")
    val book: String? = null,

    @field:SerializedName("vwap")
    val vwap: String? = null,

    @field:SerializedName("ask")
    val ask: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("bid")
    val bid: String? = null,

    @field:SerializedName("change_24")
    val change24: String? = null
)