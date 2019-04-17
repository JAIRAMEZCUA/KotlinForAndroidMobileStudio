package com.gorrotowi.kotlin107locationandmaps

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver

class AddressResultReceiver(handler: Handler) : ResultReceiver(handler) {

    lateinit var onFetchAddress: (String) -> Unit

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        super.onReceiveResult(resultCode, resultData)
        val addressData = resultData?.getString(RESULT_DATA_KEY) ?: ""
        if (resultCode == SUCCESS_DATA_RESULT) {
            onFetchAddress(addressData)
        }
    }

    fun setonFetchAddressListener(block: (String) -> Unit) {
        onFetchAddress = block
    }

}