package com.example.ahorraaguaapp

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.random.Random

class JuegoView(context: Context) : View(context) {
    private var valdeBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.valde)
    private var backgroundBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ciudad_lluviosa)
    private val valdePaint = Paint().apply { color = Color.WHITE }
    private val gotaPaint = Paint().apply { color = Color.CYAN }
    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 40f
        isAntiAlias = true
    }
    private val boxPaint = Paint().apply {
        color = Color.parseColor("#ADD8E6")  // Light blue color
        style = Paint.Style.FILL
    }
    private val borderPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }
    private val boldTextPaint = Paint().apply {
        color = Color.BLACK
        textSize = 40f
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    private val italicTextPaint = Paint().apply {
        color = Color.BLACK
        textSize = 36f
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
    }
    private val buttonTextPaint = Paint().apply {
        color = Color.BLACK
        textSize = 34f
    }
    private val buttonPaint = Paint().apply {
        color = Color.parseColor("#B76E79")  // Rose gold color
        style = Paint.Style.FILL
    }
    private val buttonBorderPaint = Paint().apply {
        color = Color.DKGRAY
        style = Paint.Style.STROKE
        strokeWidth = 3f
    }
    private val valdeWidth = 200
    private val valdeHeight = 100
    private val gotaradio = 20
    private var valdeX = 0f
    private var valdeY = 0f
    private var bordevaldeY = 0f
    private var gotaX = width / 2f
    private var gotaY = 0f
    private var gotavelocidad = 2f
    private var racha = 0
    private var puntajealto = 0
    private var juegoterminado = false
    private val handler = Handler(Looper.getMainLooper())
    private var primeragota = true
    private fun resetgota() {
        if (primeragota) {
            gotaX = width / 2f
            primeragota = false
        } else {
            gotaX = Random.nextFloat() * (width - 2 * gotaradio) + gotaradio
        }
        gotaY = 0f
    }

    private fun resizeBitmap(source: Bitmap): Bitmap {
        val fixedWidth = valdeWidth
        val fixedHeight = valdeHeight
        return Bitmap.createScaledBitmap(source, fixedWidth, fixedHeight, true)
    }

    init {
        valdeBitmap = resizeBitmap(valdeBitmap)
        resetgota()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Resize the background to fill the view
        backgroundBitmap = Bitmap.createScaledBitmap(backgroundBitmap, w, h, true)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the background image first
        canvas.drawBitmap(backgroundBitmap, 0f, 0f, null)

        valdeY = height.toFloat()
        bordevaldeY = height - valdeHeight.toFloat()
        canvas.drawBitmap(valdeBitmap, valdeX, valdeY - valdeBitmap.height, null)
        canvas.drawRect(valdeX, valdeY, valdeX + valdeWidth, valdeY + valdeHeight, valdePaint)
        canvas.drawCircle(gotaX, gotaY, gotaradio.toFloat(), gotaPaint)
        canvas.drawText("Puntaje: $racha", 10f, 40f, textPaint)

        if (!juegoterminado) {
            gotaY += gotavelocidad
            if (gotaY >= bordevaldeY - gotaradio && gotaX >= valdeX && gotaX <= valdeX + valdeWidth) {
                racha++
                puntajealto = maxOf(racha, puntajealto)
                aumentarvelocidad()
                resetgota()
            } else if (gotaY > height) {
                juegoterminado = true
                handler.removeCallbacksAndMessages(null)
            }
            handler.postDelayed({ invalidate() }, 16)
        } else {
            drawGameOverScreen(canvas)
        }
    }
    private fun aumentarvelocidad() {
        gotavelocidad += 1f
    }
    private fun drawGameOverScreen(canvas: Canvas) {
        val boxLeft = 10f
        val boxTop = 100f
        val boxRight = width - 10f
        val boxBottom = 400f
        canvas.drawRect(boxLeft, boxTop, boxRight, boxBottom, boxPaint)
        canvas.drawRect(boxLeft, boxTop, boxRight, boxBottom, borderPaint)
        canvas.drawText("Juego terminado", boxLeft + 20, boxTop + 50, boldTextPaint)
        canvas.drawText("Recuerda no desperdiciar agua. Es un recurso vital para la supervivencia.", boxLeft + 20, boxTop + 100, italicTextPaint)
        canvas.drawText("Puntaje: $racha", boxLeft + 20, boxTop + 150, textPaint)
        val buttonTop = boxTop + 180
        val buttonBottom = buttonTop + 60
        canvas.drawRect(boxLeft + 50, buttonTop, boxRight - 50, buttonBottom, buttonPaint)
        canvas.drawRect(boxLeft + 50, buttonTop, boxRight - 50, buttonBottom, buttonBorderPaint)
        canvas.drawText("Click para re-iniciar", boxLeft + 70, buttonTop + 40, buttonTextPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (juegoterminado && event.action == MotionEvent.ACTION_DOWN) {
            if (event.y in 300f..360f && event.x in 70f..(width - 70f)) {
                racha = 0
                juegoterminado = false
                primeragota = true
                resetgota()
                invalidate()
                return true
            }
        }
        if (event.action == MotionEvent.ACTION_MOVE) {
            valdeX = event.x - valdeWidth / 2f
            invalidate()
        }
        if (event.action == MotionEvent.ACTION_UP) {
            performClick()
        }
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valdeBitmap.recycle()
    }

}

@Composable
fun JuegoViewPreview(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    AndroidView(
        factory = { JuegoView(context) },
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun PreviewJuegoView() {
    JuegoViewPreview()
}
