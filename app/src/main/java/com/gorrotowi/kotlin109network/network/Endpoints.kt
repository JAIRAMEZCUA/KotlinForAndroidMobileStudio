package com.gorrotowi.kotlin109network.network

import com.gorrotowi.kotlin109network.network.entitys.ResponseAvailableBooks
import com.gorrotowi.kotlin109network.network.entitys.ResponseBookTicker
import com.gorrotowi.kotlin109network.network.entitys.ResponseTicker
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Endpoints {

    @GET("available_books/")
    fun getAvailableBooks(): Call<ResponseAvailableBooks>

    @GET("ticker/")
    fun getTicker(): Call<ResponseTicker>

    @GET("ticker/")
    fun getTickerCoroutines(): Deferred<Response<ResponseTicker>>

    @GET("ticker/")
    fun getBookTicker(@Query("book") book: String): Call<ResponseBookTicker>

    @GET("account_status/")
    fun getAccountStatus(@Header("Authorization") authHeader: String): Call<JSONObject>

    @POST("phone_number/")
    fun registerNumber(@Header("Authorization") authHeader: String, @Body rqPhone: JSONObject): Call<JSONObject>

}