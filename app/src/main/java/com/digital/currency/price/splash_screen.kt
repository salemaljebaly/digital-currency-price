package com.digital.currency.price

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class splash_screen : AppCompatActivity() {

    var animFromTop:Animation?= null
    var animFromBottom:Animation?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var thread = Thread(){
            run {
                try {
                    Thread.sleep(3000)
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (ex:Exception){
                    ex.printStackTrace()
                }
            }
        }
        thread.start()
        // Logo come from top in nice transition
        animFromTop = AnimationUtils.loadAnimation(this, R.anim.logo_from_top)
        logo.animation = animFromTop
        // apName come from bottom in nice transition
        animFromBottom = AnimationUtils.loadAnimation(this,R.anim.app_name_bottom)
        apName.animation = animFromBottom

    }
}
