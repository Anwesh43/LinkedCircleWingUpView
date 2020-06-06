package com.anwesh.uiprojects.linkedballwingsupview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.anwesh.uiprojects.ballwingsupview.BallWingsUpView

/**
 * Created by anweshmishra on 06/06/20.
 */

class MainActivity: AppCompatActivity() {

    override fun onCreate(instance : Bundle?) {
        super.onCreate(instance)
        BallWingsUpView.create(this)
        fullScreen()
    }

    fun MainActivity.fullScreen() {
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}