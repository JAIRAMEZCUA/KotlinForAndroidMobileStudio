package com.gorrotowi.kotlin109network.network

import android.util.Log
import com.gorrotowi.kotlin109network.BuildConfig
import com.gorrotowi.kotlin109network.network.entitys.ResponseAvailableBooks
import com.gorrotowi.kotlin109network.network.entitys.ResponseBookTicker
import com.gorrotowi.kotlin109network.network.entitys.ResponseTicker
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiBitso {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.bitso.com/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(createOkhttpClient())
        .build()

    val bitsoEndpoints = retrofit.create<Endpoints>()

    private fun createInterceptor() = Interceptor { chain ->
        val request = chain.request().newBuilder()
        request.addHeader("Content-Type", "application/json")
        request.addHeader("Accept", "application/json")
        chain.proceed(request.build())
    }

    private fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    private fun createOkhttpClient() = OkHttpClient.Builder().apply {
        interceptors().add(createInterceptor())
        interceptors().add(createLoggingInterceptor())
        callTimeout(10, TimeUnit.SECONDS)
    }.build()

    fun getAvailableBooks(
        success: (ResponseAvailableBooks?) -> Unit,
        error: (Int, Throwable) -> Unit
    ): Call<ResponseAvailableBooks> {
        Log.e("asdf", "afafd")
        val callBooks = bitsoEndpoints.getAvailableBooks()

        callBooks.enqueue(object : Callback<ResponseAvailableBooks> {
            override fun onFailure(call: Call<ResponseAvailableBooks>, t: Throwable) {
                Log.e("Error", "adsfklafjashfahf")
                error(Int.MIN_VALUE, t)
            }

            override fun onResponse(call: Call<ResponseAvailableBooks>, response: Response<ResponseAvailableBooks>) {
                Log.e("response", "${response?.toString()}")
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        success(response.body())
                    } else {
                        error(response.code(), Throwable("${response.code()} ${response.message()}"))
                    }
                } else {
                    error(response.code(), Throwable("${response.code()} ${response.message()}"))
                }
            }
        })

        return callBooks
    }

    fun getTicker(
        success: (ResponseTicker?) -> Unit,
        error: (Int, Throwable) -> Unit
    ) {
        val callTicker = bitsoEndpoints.getTicker()

        callTicker.enqueue(object : Callback<ResponseTicker> {
            override fun onFailure(call: Call<ResponseTicker>, t: Throwable) {
                error(Int.MIN_VALUE, t)
            }

            override fun onResponse(call: Call<ResponseTicker>, response: Response<ResponseTicker>) {
                if (response.isSuccessful && response.code() == 200) {
                    success(response.body())
                } else {
                    error(response.code(), Throwable(response.message()))
                }
            }

        })
    }

    fun getBookTicker(
        book: String,
        success: (ResponseBookTicker?) -> Unit,
        error: (Int, Throwable) -> Unit
    ) {
        val callBookTicker = bitsoEndpoints.getBookTicker(book)

        callBookTicker.enqueue(object : Callback<ResponseBookTicker> {
            override fun onFailure(call: Call<ResponseBookTicker>, t: Throwable) {
                error(Int.MIN_VALUE, t)
            }

            override fun onResponse(call: Call<ResponseBookTicker>, response: Response<ResponseBookTicker>) {
                if (response.isSuccessful && response.code() == 200) {
                    success(response.body())
                } else {
                    error(response.code(), Throwable(response.message()))
                }
            }

        })
    }

    fun getBookTickerResult(
        resultCall: (ResultRetrofit<ResponseTicker>) -> Unit
    ) {
        val callBookTicker = bitsoEndpoints.getTicker()

        callBookTicker.enqueue(object : Callback<ResponseTicker> {
            override fun onFailure(call: Call<ResponseTicker>, t: Throwable) {
                resultCall(ResultRetrofit.Error(t))
            }

            override fun onResponse(call: Call<ResponseTicker>, response: Response<ResponseTicker>) {
                if (response.isSuccessful && response.code() == 200) {
                    val body = response.body()
                    if (body != null) {
                        resultCall(ResultRetrofit.Success(body))
                    } else {
                        resultCall(ResultRetrofit.Error(Throwable("No data available")))
                    }
                } else {
                    resultCall(ResultRetrofit.Error(Throwable("Error to fetch API")))
                }
            }

        })
    }

    suspend fun getTickerByCoroutine(): ResultRetrofit<ResponseTicker> {
        val resultResponse = bitsoEndpoints.getTickerCoroutines().await()
        return if (resultResponse.isSuccessful) {
            val resultData = resultResponse.body()
            if (resultData != null) {
                ResultRetrofit.Success(resultData)
            } else {
                ResultRetrofit.Error(Throwable("Error to fetch response"))
            }
        } else {
            ResultRetrofit.Error(Throwable("Error to fetch TICKER API"))
        }
    }

    fun getAccountStatus(
        authHeader: String,
        success: (JSONObject?) -> Unit,
        error: (Int, Throwable) -> Unit
    ) {
        val callAccountStatus = bitsoEndpoints.getAccountStatus(authHeader)

        callAccountStatus.enqueue(object : Callback<JSONObject> {
            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                error(Int.MIN_VALUE, t)
            }

            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                if (response.isSuccessful && response.code() == 200) {
                    success(response.body())
                } else {
                    error(response.code(), Throwable(response.message()))
                }
            }

        })
    }

    fun postRegisterNumber(
        header: String,
        number: JSONObject,
        success: (JSONObject?) -> Unit,
        error: (Int, Throwable) -> Unit
    ) {

        val callPhone = bitsoEndpoints.registerNumber(header, number)

        callPhone.enqueue(object : Callback<JSONObject> {
            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                error(Int.MIN_VALUE, t)
            }

            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                if (response.isSuccessful && response.code() == 200) {
                    success(response.body())
                } else {
                    error(response.code(), Throwable(response.message()))
                }
            }

        })

    }

}

sealed class ResultRetrofit<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultRetrofit<T>()
    data class Error(val error: Throwable) : ResultRetrofit<Nothing>()
}