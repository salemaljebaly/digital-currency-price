package com.digital.currency.price

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.*
import android.widget.BaseAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.coin_details.view.*


class MainActivity : AppCompatActivity() {

    // Define adapter
    var adapter:CoinAdapter?=null
    var listOfCoins = ArrayList<Coin>()

    // define mAdveiw
    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Run function when app start
        loadCoins()

        // Fill adapter by coin array
        adapter= CoinAdapter(listOfCoins,this)

        // Fill grid view
        gvCoin.adapter = adapter

        MobileAds.initialize(this){}
        // Init add mob
        mAdView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    // Fill array by name and image coins
    fun loadCoins(){
        // Fill list array by coin class
        listOfCoins.add(Coin("btc","bitcoin",R.drawable.btc))
        listOfCoins.add(Coin("eth","ethereum",R.drawable.eth))
        listOfCoins.add(Coin("xmr","Monero",R.drawable.xmr))
        listOfCoins.add(Coin("dash","Dashcoin",R.drawable.dash))
        listOfCoins.add(Coin("ftc","feathercoin",R.drawable.ftc))
        listOfCoins.add(Coin("neo","NEO",R.drawable.neo))
        listOfCoins.add(Coin("kmd","Komodo",R.drawable.kmd))
        listOfCoins.add(Coin("kore","KoreCoin",R.drawable.kore))
        listOfCoins.add(Coin("ltc","Litecoin",R.drawable.ltc))
        listOfCoins.add(Coin("dcr","Decred",R.drawable.dcr))
        listOfCoins.add(Coin("dgb","DigiByte",R.drawable.dgb))
        listOfCoins.add(Coin("pasc","Pascal-coin",R.drawable.pasc))
        listOfCoins.add(Coin("sc","Siacoin",R.drawable.sia))
        listOfCoins.add(Coin("xrp","Ripple",R.drawable.xrp))
        listOfCoins.add(Coin("zec","ZCash",R.drawable.zec))
        listOfCoins.add(Coin("zeit","ZeitCoin",R.drawable.zeit))
        listOfCoins.add(Coin("etc","Ethereum-classic",R.drawable.etc))
        listOfCoins.add(Coin("rads","Radium",R.drawable.rads))
        listOfCoins.add(Coin("bcn","bytecoin-bcn",R.drawable.bcn))
        listOfCoins.add(Coin("pot","Spots",R.drawable.pot))
    }

    // Use adapter to show data in grid view container
    inner class CoinAdapter:BaseAdapter{

        var context:Context?=null
        // Define local array that passed from main activity
        var localListOfCoin = ArrayList<Coin>()
        constructor(localListOfCoin:ArrayList<Coin>, context:Context){
            this.localListOfCoin = localListOfCoin
            this.context = context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var coin = localListOfCoin[p0]
            val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var coinView = inflator.inflate(R.layout.coin_details,null)
            coinView.fullName.text = coin.coinFullName!!
            coinView.coinImage.setImageResource(coin.image!!)
            coinView.setOnClickListener{
                if(isNetworkConnected()){
                    var intent = Intent(context,coin_info::class.java)
                    intent.putExtra("name",coin.name!!)
                    intent.putExtra("coinFullName",coin.coinFullName!!)
                    intent.putExtra("image",coin.image!!)
                    context!!.startActivity(intent)
                }else {
                    AlertDialog.Builder(this@MainActivity).setTitle(R.string.alert_title)
                            .setMessage(R.string.alert_msg)
                            .setPositiveButton(android.R.string.ok){ _, _ -> }
                            .setIcon(android.R.drawable.ic_dialog_alert).show()
                }
            }

            return coinView
        }

        override fun getItem(p0: Int): Any {
            return localListOfCoin[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            // return index of array that will pass to getView fun
            return localListOfCoin.size
        }

    }

    // Check internet connection
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager// 1
        val networkInfo = connectivityManager.activeNetworkInfo // 2
        return networkInfo != null && networkInfo.isConnected // 3
    }

    // Init menu in activity
    override fun onCreateOptionsMenu(menu: Menu ): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Do some thing when press on item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.about -> {
                var aboutIntent = Intent(this,About::class.java)
                startActivity(aboutIntent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
