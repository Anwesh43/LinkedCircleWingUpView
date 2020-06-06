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
val strokeFactor : Float = 90f
val delay : Long = 20

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()


fun Canvas.drawWing(i : Int, size : Float, sf1 : Float, sf2 : Float, paint : Paint) {
    val si : Float = 1f - 2 * i
    save()
    translate(size * si, 0f)
    rotate(-rot * si * sf2)
    drawLine(0f, 0f, size * sf1, 0f, paint)
    restore()
}

fun Canvas.drawBallWingsUp(scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / sizeFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    val sf4 : Float = sf.divideScale(3, parts)
    save()
    translate(0f, (h / 2 - size /2) * (1f - sf4))
    drawCircle(0f, 0f, size * sf1, paint)
    for (j in 0..1) {
        drawWing(j, size, sf2, sf3, paint)
    }
    restore()
}

fun Canvas.drawBWUNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = Color.parseColor(colors[i])
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(w / 2, h / 2)
    drawBallWingsUp(scale, w, h, paint)
    restore()
}

class BallWingsUpView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * dir
            if (Math.abs(scale - prevScale)  > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }

    data class Animator(var view : View, var animated : Boolean = false) {

        fun animate(cb : () -> Unit) {
            if (animated) {
                cb()
                try {
                    Thread.sleep(delay)
                    view.invalidate()
                } catch(ex : Exception) {

                }
            }
        }

        fun start() {
            if (!animated) {
                animated = true
                view.postInvalidate()
            }
        }

        fun stop() {
            if (animated) {
                animated = false
            }
        }
    }
}