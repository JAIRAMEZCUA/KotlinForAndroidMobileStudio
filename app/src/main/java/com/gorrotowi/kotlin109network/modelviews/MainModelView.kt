package com.gorrotowi.kotlin109network.modelviews

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gorrotowi.kotlin109network.network.ApiBitso
import com.gorrotowi.kotlin109network.network.ResultRetrofit
import com.gorrotowi.kotlin109network.network.entitys.ResponseTicker
import kotlinx.coroutines.*
import org.json.JSONObject
import java.math.BigInteger
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.coroutines.CoroutineContext

class MainModelView(application: Application) : AndroidViewModel(application) {

    val tickerBooks = MutableLiveData<ResponseTicker>()
    val accountStatus = MutableLiveData<JSONObject>()
    val errorResponse = MutableLiveData<String?>()

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    fun getTickerBooks() {
        ApiBitso.getTicker(success = { responseTicker ->
            tickerBooks.value = responseTicker
            errorResponse.value = null
        }, error = { code, error ->
            Log.e("TickerError", "${error.message}")
            errorResponse.value = error.message
        })
    }

    fun getTickerBooksResult() {
        ApiBitso.getBookTickerResult { result ->
            when (result) {
                is ResultRetrofit.Success -> {
                    tickerBooks.value = result.data
                    errorResponse.value = null
                }
                is ResultRetrofit.Error -> {
                    errorResponse.value = result.error.message
                }
            }
        }
    }

    fun getTickerByCoroutine() {
        scope.launch {
            when (val result = ApiBitso.getTickerByCoroutine()) {
                is ResultRetrofit.Success -> {
                    tickerBooks.postValue(result.data)
                    errorResponse.postValue(null)
                }
                is ResultRetrofit.Error -> {
                    launch(Dispatchers.Main) {
                        errorResponse.value = result.error.message
                    }
                }
            }
        }
    }

    fun getAccountStatus() {
        val header = generateAuthHeader("GET", "/v3/account_status/")
        ApiBitso.getAccountStatus(header, success = { response ->
            accountStatus.value = response
            errorResponse.value = null
        }, error = { code, error ->
            errorResponse.value = error.message
        })
    }

    fun registerPhoneNumber(number: String) {
        val jsonPayload = JSONObject()
        val jsonPhone = JSONObject()
        jsonPhone.put("phone_number", number)
        jsonPayload.put("payload", jsonPhone)
        Log.e("JSONPayload", "$jsonPayload")
        val header = generateAuthHeader("POST", "/v3/phone_number/", jsonPayload.toString())
        ApiBitso.postRegisterNumber(header, jsonPhone,
            success = { response ->
                accountStatus.value = response
                errorResponse.value = null
            }, error = { code, error ->
                errorResponse.value = error.message
            })
    }

    fun cancelRequests() {
        coroutineContext.cancel()
    }

    private fun generateAuthHeader(method: String, requestPath: String, JSONPayload: String = ""): String {
        val bitsoKey = ""
        val bitsoSecret = ""
        val nonce = System.currentTimeMillis()

        Log.e("payload ---->", "$JSONPayload")
        // Create the signature
        val message = nonce.toString() + method + requestPath + JSONPayload
        var signature = ""
        val secretBytes = bitsoSecret.toByteArray()
        val localMac = SecretKeySpec(secretBytes, "HmacSHA256")
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(localMac)
        val arrayOfByte = mac.doFinal(message.toByteArray())
        val localBigInteger = BigInteger(1, arrayOfByte)
        signature = String.format("%0" + (arrayOfByte.size shl 1) + "x", localBigInteger)
        return String.format("Bitso %s:%s:%s", bitsoKey, nonce, signature)
    }

}