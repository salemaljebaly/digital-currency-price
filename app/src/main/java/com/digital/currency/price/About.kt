package com.digital.currency.price

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }

    // Share app
    fun shareApp(view:View){
        val shareLink = getString(R.string.app_url)
        val share =Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT,shareLink)
        startActivity(share)
    }

    // Send email to developer

    // store email in array
    private var To = arrayOf(getString(R.string.developer_email))
    fun mailToD(view:View){
        Log.d("mailToD ", "Pressed :)")
        val sendEmail =Intent(Intent.ACTION_SEND)
        sendEmail.data = Uri.parse("mailto:")
        sendEmail.type = "text/plain"
        sendEmail.putExtra(Intent.EXTRA_EMAIL,To)
        sendEmail.putExtra(Intent.EXTRA_SUBJECT,R.string.app_name)
        sendEmail.putExtra(Intent.EXTRA_TITLE,R.string.app_name)
        sendEmail.putExtra(Intent.EXTRA_TEXT,"")
        startActivity(sendEmail)
    }

    // Open developer app account
    fun appStore(view:View){
        val developer =Intent(Intent.ACTION_VIEW)
        developer.data = Uri.parse(getString(R.string.developer_url))
        startActivity(developer)
    }


    // Init menu in activity
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Do some thing when press on item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.back) {
            val backIntent = Intent(this,MainActivity::class.java)
            startActivity(backIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
