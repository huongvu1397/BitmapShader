package com.example.bitmapshader

import android.content.Context
import android.graphics.*
import android.graphics.Paint.Cap
import android.graphics.Paint.Join
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class PainterCanvasH : View {


    private var mBgBitmap: Bitmap? = null
    private var mNewBitmap: Bitmap? = null
    private var mPath: Path? = null
    private var mPaint: Paint? = null
    private var mCanvas: Canvas? = null

    constructor(context: Context?) : super(context){init()}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){init()}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){init()}
    private fun init() {
        mPaint = Paint()
        mPaint!!.alpha = 0
        mPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)//设置混合模式为DST_IN
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = 50f
        mPaint!!.strokeJoin = Join.ROUND//Paint.Join.ROUND和Paint.Cap.ROUND设置Paint笔触和连接处更加圆滑一点
        mPaint!!.strokeCap = Cap.ROUND
        mBgBitmap = BitmapFactory.decodeResource(resources, R.drawable.pattern)//设置背景图
        mNewBitmap = Bitmap.createBitmap(mBgBitmap!!.width, mBgBitmap!!.height, Bitmap.Config.ARGB_8888) //设置遮罩图
        mCanvas = Canvas(mNewBitmap)
        mCanvas!!.drawColor(Color.GRAY)
        mPath = Path()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath!!.reset()
                mPath!!.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                mPath!!.lineTo(event.x, event.y)
                //mPath!!.setLastPoint(event.x,event.y)
            }
        }
        mCanvas?.drawPath(mPath, mPaint)
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mBgBitmap, 0f, 0f, null)
        canvas.drawBitmap(mNewBitmap, 0f, 0f, null)

    }
}