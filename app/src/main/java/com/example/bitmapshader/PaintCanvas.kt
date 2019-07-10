package com.example.bitmapshader

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PaintCanvas : View {
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private var mPaint: Paint? = null
    private var bitmapShader: Shader? = null
    private var linearGradient: Shader? = null
    private var composeShader: Shader? = null
    private fun init() {
        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.pattern)
        bitmapShader = BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)
        linearGradient = LinearGradient(
            0f,
            0f,
            bitmap.width.toFloat(),
            bitmap.height.toFloat(),
            Color.BLUE,
            Color.GREEN,
            Shader.TileMode.CLAMP
        )
        composeShader = ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY)
        mPaint = Paint()
        mPaint?.isAntiAlias = true
        val colorMatrix = ColorMatrix(
            floatArrayOf(
                0.5f, 0f, 0f, 0f, 0f,
                0f, 0.5f, 0f, 0f, 0f,
                0f, 0f, 0.5f, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            )
        )
        //mPaint?.colorFilter = ColorMatrixColorFilter(colorMatrix)
        //mPaint?.colorFilter = LightingColorFilter(Color.parseColor("#ffff00ff"),0x00000000)
        // mPaint?.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.DARKEN)
        //mPaint?.maskFilter = BlurMaskFilter(20f,BlurMaskFilter.Blur.SOLID)
    }

    override fun onDraw(canvas: Canvas) {
        mPaint?.shader = bitmapShader
        canvas.drawCircle(500f, 550f, 400f, mPaint)
    }
}