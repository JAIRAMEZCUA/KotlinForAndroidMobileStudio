package com.gorrotowi.kotlin109network.network.entitys

import com.google.gson.annotations.SerializedName

data class ResponseBookTicker(

    @field:SerializedName("payload")
    val payload: PayloadItemTicker? = null,

    @field:SerializedName("success")
    val success: Boolean? = null
)