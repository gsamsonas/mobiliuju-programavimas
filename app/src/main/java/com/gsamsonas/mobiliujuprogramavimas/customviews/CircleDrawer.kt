package com.gsamsonas.mobiliujuprogramavimas.customviews

import android.content.Context
import android.graphics.*
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.gsamsonas.mobiliujuprogramavimas.R
import java.lang.Integer.*
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

class CircleDrawer(context: Context, attr: AttributeSet) : View(context, attr) {

    private val paint = Paint().apply {
        color = Color.BLUE
    }

    private var singlePressStart: PointF? = null
    private var drawShape: Shape? = null

    private var firstFinger: PointF? = null
    private var secondFinger: PointF? = null

    private fun getCenterAndRadius(): Circle? {
        val first = firstFinger ?: return null
        val second = secondFinger ?: return null
        val x = abs(first.x + second.x) / 2
        val y = abs(first.y + second.y) / 2
        val r =  first.distanceTo(second) / 2
        return Circle(x, y, r)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false

        when (event.action) {
            MotionEvent.ACTION_UP -> {
                firstFinger = null
            }
            MotionEvent.ACTION_DOWN -> {
                firstFinger = PointF(event.x, event.y)
                if (singlePressStart == null) singlePressStart = firstFinger
            }
        }

        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> {
                secondFinger = PointF(event.getX(event.actionIndex), event.getY(event.actionIndex))
            }
            MotionEvent.ACTION_MOVE -> {
                if (event.pointerCount >= 1) {
                    firstFinger = PointF(event.x, event.y)
                }
                if (event.pointerCount >= 2) {
                    secondFinger = PointF(event.getX(1), event.getY(1))
                }
                if (secondFinger != null) singlePressStart = null
                if (singlePressStart?.distanceTo(firstFinger!!) ?: 0F >= 100) {
                    drawShape = Shape.getRandom()
                    singlePressStart = null
                }
            }
            MotionEvent.ACTION_POINTER_UP -> {
                getCenterAndRadius()?.let {
                    val collides = drawShape?.collides(it) ?: return@let
                    if (collides) {
                        MediaPlayer.create(context, R.raw.you_good).start()
                    } else {
                        MediaPlayer.create(context, R.raw.swag).start()
                    }
                    Toast.makeText(context, collides.toString(), Toast.LENGTH_SHORT).show()
                }
                secondFinger = null
            }
            MotionEvent.ACTION_CANCEL -> {
                secondFinger = null
            }
        }

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        getCenterAndRadius()?.let {
            val (x, y, r) = it
            canvas.drawCircle(x, y, r, paint)
        }

        drawShape?.draw(canvas)
    }

    data class Circle(
        val x: Float,
        val y: Float,
        val r: Float
    )

    sealed class Shape (private val paint: Paint) {
        class Rectangle(val rect: Rect) : Shape (getRandomPaint()) {
            companion object {
                fun getRandom(): Rectangle {
                    val (side1, side2) = Random.nextInt(0, 1000) to Random.nextInt(0, 1000)
                    val (left, right) = min(side1, side2) to max(side1, side2)
                    val (side3, side4) = Random.nextInt(0, 1900) to Random.nextInt(0, 1900)
                    val (top, bottom) = min(side3, side4) to max(side3, side4)
                    return Rectangle(Rect(left, top, right, bottom))
                }
            }
        }
        class Circle(val circle: CircleDrawer.Circle) : Shape (getRandomPaint()) {
            companion object {
                fun getRandom(): Circle {
                    val r = Random.nextFloat() * 300
                    val x = Random.nextFloat() * (1000 - 2 * r) + r
                    val y = Random.nextFloat() * (1900 - 2 * r) + r
                    return Circle(CircleDrawer.Circle(x, y, r))
                }
            }
        }

        fun draw(canvas: Canvas) {
            when (this) {
                is Rectangle -> canvas.drawRect(rect, paint)
                is Circle -> canvas.drawCircle(
                    circle.x,
                    circle.y,
                    circle.r,
                    paint
                )
            }
        }

        fun collides(with: CircleDrawer.Circle): Boolean {
            return when (this) {
                is Rectangle -> {
                    val distX = min(abs(with.x - rect.left), abs(with.x - rect.right))
                    val horizontalCollides = distX <= with.r
                    val distY = min(abs(with.y - rect.top), abs(with.y - rect.bottom))
                    val verticalCollides = distY <= with.r
                    horizontalCollides || verticalCollides
                }
                is Circle -> {
                    val dist = PointF(circle.x, circle.y).distanceTo(PointF(with.x, with.y))
                    dist <= circle.r + with.r
                }
            }
        }

        companion object {
            fun getRandom(): Shape {
                return when (Random.nextInt(0, 2)) {
                    0 -> {
                        Rectangle.getRandom()
                    }
                    1 -> {
                        Circle.getRandom()
                    }
                    else -> Rectangle.getRandom()
                }
            }

            private fun getRandomPaint(): Paint {
                val paintColor = when (Random.nextInt(0, 6)) {
                    1 -> Color.CYAN
                    2 -> Color.GREEN
                    3 -> Color.RED
                    4 -> Color.GRAY
                    5 -> Color.YELLOW
                    else -> Color.MAGENTA
                }
                return Paint().apply { color = paintColor }
            }
        }
    }

}

internal fun PointF.distanceTo(another: PointF) = sqrt(abs(x - another.x).pow(2) + abs(y - another.y).pow(2))