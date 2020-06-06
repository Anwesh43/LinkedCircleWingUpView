package com.anwesh.uiprojects.ballwingsupview

/**
 * Created by anweshmishra on 06/06/20.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Color

val colors : Array<String> = arrayOf("#3F51B5", "#009688", "#03A9F4", "#4CAF50", "#F44336")
val parts : Int = 5
val scGap : Float = 0.02f / parts
val backColor : Int = Color.parseColor("#BDBDBD")
val rot : Float = 45f
val sizeFactor : Float = 8f
