package com.digital.currency.price

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import com.digital.currency.retrofitcoin.Response
import com.digital.currency.retrofitcoin.Service
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_coin_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class coin_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_info)

        // Check internet
        if(isNetworkConnected()){
            // Show progress
            aviProgress.show()
            // if there internet
            val bundle = intent.extras
            val name = bundle.getString("name")
            val coinFullName = bundle.getString("coinFullName")
            val image = bundle.getInt("image")

            fullNameInfo.text = coinFullName + " (" + name.toString().toUpperCase() + ")"

            coinImageInfo.setImageResource(image)
            // aviProgress.show()
            Log.d("symbol : ", coinFullName!!.toString().toLowerCase())
            // Retrofit instance
            val retrofit = Retrofit.Builder()
                    .baseUrl(Service.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(Gson())).build()

            val service = retrofit.create(Service::class.java)
            service.method(coinFullName.toString().toLowerCase()).enqueue(object : Callback<ArrayList<Response>>{
                override fun onResponse(call: Call<java.util.ArrayList<Response>>, response: retrofit2.Response<java.util.ArrayList<Response>>) {

                    coinPrice.text              =  response.body()!![0].priceUsd.toString() + " $"
                    coinPriceBTC.text           =  response.body()!![0].priceBtc.toString()
                    Market_Cap.text             =  response.body()!![0].marketCapUsd.toString() + " $"
                    Circulation_Supply.text     =  response.body()!![0].availableSupply.toString() + " $"
                    _24H_Volume.text            =  response.body()!![0]._24hVolumeUsd.toString() + " $"
                    Percent_Change1H.text       =  response.body()!![0].percentChange1h.toString()
                    Percent_Change24H.text      =  response.body()!![0].percentChange24h.toString()
                    Percent_Change7D.text       =  response.body()!![0].percentChange7d.toString()
                    // last_updated.text = response.body()!![0].lastUpdated!!.toInt().toLong()
                    // Hide progress
                    aviProgress.hide()

                }

                override fun onFailure(call: Call<java.util.ArrayList<Response>>, t: Throwable) {
                    Toast.makeText(this@coin_info, "Error :( ", Toast.LENGTH_SHORT).show()
                }
            })

        } else {
            AlertDialog.Builder(this).setTitle(R.string.alert_title)
                    .setMessage(R.string.alert_msg)
                    .setPositiveButton(android.R.string.ok){ _, _-> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

    }

    // Check internet connection
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager// 1
        val networkInfo = connectivityManager.activeNetworkInfo // 2
        return networkInfo != null && networkInfo.isConnected // 3
    }

    // Init menu in activity
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // join menu in activity
        menuInflater.inflate(R.menu.coin_info_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Do some thing when press on item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.backToM -> {



                // Back to maian activity
                val BackIntent = Intent(this,MainActivity::class.java)
                startActivity(BackIntent)


            }

        }
        return super.onOptionsItemSelected(item)
    }

}
