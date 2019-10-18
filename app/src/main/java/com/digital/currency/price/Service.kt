package com.digital.currency.retrofitcoin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.ArrayList

/**
 * Created by lionnufilia on 10/26/2017.
 */
interface Service {
    @GET("{coin}")
    fun method(@Path("coin") coin:String ) : Call<ArrayList<Response>>

    companion object {
        val BASE_URL = "https://api.coinmarketcap.com/v1/ticker/"
    }
}