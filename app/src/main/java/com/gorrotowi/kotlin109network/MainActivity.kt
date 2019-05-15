package com.gorrotowi.kotlin109network

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gorrotowi.kotlin109network.adapters.AdapterBooks
import com.gorrotowi.kotlin109network.modelviews.MainModelView
import com.gorrotowi.kotlin109network.network.ApiBitso
import com.gorrotowi.kotlin109network.network.entitys.ResponseAvailableBooks
import com.gorrotowi.kotlin109network.utils.convertToItemBookList
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    var requestAvailableBooks: Call<ResponseAvailableBooks>? = null

    val modelView: MainModelView by lazy {
        ViewModelProviders.of(this@MainActivity).get(MainModelView::class.java)
    }

    val adapterBooks: AdapterBooks by lazy {
        AdapterBooks()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        setUpObservables()

        setUpListeners()

        showRefresh()
//        modelView.getTickerBooks()
//        modelView.getTickerBooksResult()
        modelView.getTickerByCoroutine()
//        modelView.getAccountStatus()
//        modelView.registerPhoneNumber("5520692080")
//        ugglyRequest()

    }

    private fun ugglyRequest() {
        requestAvailableBooks = ApiBitso.getAvailableBooks(
            success = { response ->
                Log.v("ResponseRq", "${response?.toString()}")
            }, error = { responseCode, errorMessage ->
                Log.e("ErrorResponse", "Code $responseCode ErrorMessage ${errorMessage.message}")
            })

        ApiBitso.getTicker(success = { response ->
            Log.v("RESPONSE", "${response?.toString()}")
        }, error = { code, error ->
            Log.e(
                "ErrorResponse",
                "code $code message ${error.message}"
            )
        })

        ApiBitso.getBookTicker("btc_mxn", success = { response ->
            Log.v("Reponse", "$response")
        }, error = { code, error ->
            Log.e("ErrorResponse", "code $code, error ${error.message}")
        })
    }

    private fun initViews() {

        rcTicker?.layoutManager = LinearLayoutManager(this)
        rcTicker?.adapter = adapterBooks

    }

    private fun setUpListeners() {
        swipeTicker.setOnRefreshListener {
            //            modelView.getTickerBooksResult()
            modelView.getTickerByCoroutine()
        }
    }

    private fun showRefresh() {
        swipeTicker?.isRefreshing = true
    }

    private fun hideRefresh() {
        swipeTicker?.isRefreshing = false
    }

    private fun setUpObservables() {
        modelView.tickerBooks.observe(this@MainActivity, Observer { responseTicker ->
            hideRefresh()
            val itemBookList = responseTicker?.convertToItemBookList()
            itemBookList?.let { itemBookNotNull ->
                adapterBooks.sourceData = itemBookNotNull
            }
        })

        modelView.accountStatus.observe(this, Observer {
            Log.v("ResponseAccount", "$it")
        })

        modelView.errorResponse.observe(this, Observer { errorResponse ->
            if (errorResponse != null) {
                hideRefresh()
                Toast.makeText(this, errorResponse, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        modelView.cancelRequests()
        requestAvailableBooks?.cancel()
    }

}
