package com.example.bitmapshader

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView

class PainterCanvasH2:ImageView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    private var radius = 60f
    private var paint: Paint?=null
    private var dx = 0f
    private var dy = 0f
    private var shouldDrawOpening = false


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        shouldDrawOpening = action == MotionEvent.ACTION_DOWN|| action == MotionEvent.ACTION_MOVE
        Log.e("HVV1312","is ---------------- $shouldDrawOpening")
        dx = event.x
        dy = event.y
        //mPath!!.moveTo(event.x, event.y)

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        if(paint == null){
            var original:Bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
            var originalCanvas = Canvas(original)
            super.onDraw(originalCanvas) // ImageView
            var shader:Shader = BitmapShader(original, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
            paint = Paint()
            paint?.shader = shader
        }
        canvas.drawColor(Color.GRAY)
        if(shouldDrawOpening){
            Log.e("HVV1312","hello draw")
            canvas.drawCircle(dx,dy-radius,radius,paint!!)
        }
    }
}