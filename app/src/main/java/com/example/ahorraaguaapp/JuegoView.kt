package com.example.ahorraaguaapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.random.Random

class GameView(context: Context) : View(context) {
    private val bucketPaint = Paint().apply { color = Color.BLUE }
    private val dropPaint = Paint().apply { color = Color.CYAN }
    private val bucketWidth = 200
    private val bucketHeight = 50
    private val dropRadius = 20
    private var bucketX = 0f
    private var bucketY = 0f
    private var dropX = 0f
    private var dropY = 0f
    private var dropSpeed = 5f
    private var streak = 0
    private var highScore = 0
    private val handler = Handler(Looper.getMainLooper())

    init {
        resetDrop()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw bucket
        bucketY = height - bucketHeight.toFloat()
        canvas.drawRect(bucketX, bucketY, bucketX + bucketWidth, bucketY + bucketHeight, bucketPaint)

        // Draw drop
        canvas.drawCircle(dropX, dropY, dropRadius.toFloat(), dropPaint)

        // Update drop position
        dropY += dropSpeed

        // Check for collision
        if (dropY >= bucketY - dropRadius && dropX >= bucketX && dropX <= bucketX + bucketWidth) {
            streak++
            highScore = maxOf(streak, highScore)
            resetDrop()
        }

        // Check for miss
        if (dropY > height) {
            streak = 0
            resetDrop()
        }

        // Update speed
        dropSpeed += 0.01f

        // Redraw
        handler.postDelayed({ invalidate() }, 16)
    }

    private fun resetDrop() {
        dropX = Random.nextFloat() * (width - 2 * dropRadius) + dropRadius
        dropY = 0f
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_MOVE) {
            bucketX = event.x - bucketWidth / 2f
            invalidate() // Ensure the view is redrawn
        }
        // Call performClick() to handle accessibility events properly
        if (event.action == MotionEvent.ACTION_UP) {
            performClick()
        }
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}

// Composable function to preview GameView in Jetpack Compose
@Composable
fun GameViewPreview() {
    val context = LocalContext.current
    AndroidView(factory = { GameView(context) })
}

@Preview
@Composable
fun PreviewGameView() {
    GameViewPreview()
}