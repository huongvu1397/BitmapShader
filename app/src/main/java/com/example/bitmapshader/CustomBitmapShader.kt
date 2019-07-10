package com.example.bitmapshader

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.R.attr.bitmap
import android.graphics.*


class CustomBitmapShader : View {
    private var mWidth = 0
    private var mHeight = 0
    private var bmpShader: BitmapShader? = null
    private var mPaint: Paint? = null
    private var bmpRect: RectF? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        bmpRect = RectF(0f, 0f, mWidth.toFloat(), mHeight.toFloat())
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.pattern)
        bmpShader = BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.shader = bmpShader
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(bmpRect!!,mPaint!!)
    }
}