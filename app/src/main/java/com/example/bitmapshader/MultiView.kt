package com.example.bitmapshader

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import java.lang.reflect.Type

class MultiView :ImageView {
    private var type = TYPE_CIRCLE
    private var startRadius = 0
    private var angleCount = ANGLECOUNT
    private var currentAngle = CURRENTANGLE
    private var angles : IntArray? = null
    private var pointFList :ArrayList<PointF>?= arrayListOf()
    private var mBoderRadius = BODER_RADIUS_DEFAULT
    private var mBitmapPaint: Paint? = null
    private var mMatrix : Matrix? =null
    private var mBitmapShader:BitmapShader?=null
    private var mWidth = 0
    private var mRoundRect: RectF?=null
    private var mRadius =0
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context,attrs)
    }

    private fun init(context: Context?,attrs: AttributeSet?){
        mMatrix = Matrix()
        mBitmapPaint = Paint()
        mBitmapPaint?.isAntiAlias = true
        var typedArray = context?.obtainStyledAttributes(attrs,R.styleable.RoundImageView) as TypedArray
        mBoderRadius = typedArray.getDimensionPixelSize(
            R.styleable.RoundImageView_borderRadius,TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            BODER_RADIUS_DEFAULT.toFloat(),resources.displayMetrics).toInt())
        type = typedArray.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE)
        angleCount = typedArray.getInt(R.styleable.RoundImageView_angleCount, ANGLECOUNT)
        currentAngle = typedArray.getInt(R.styleable.RoundImageView_currentAngle,currentAngle)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if(type == TYPE_CIRCLE){
            mWidth = Math.min(measuredWidth,measuredHeight)
            mRadius = mWidth/2
            setMeasuredDimension(mWidth,mWidth)
        }
        if(type == TYPE_MULTI){
            mWidth = Math.min(measuredWidth,measuredHeight)
            setMeasuredDimension(mWidth,mWidth)
            angles = IntArray(angleCount)
            for(i in 0.. angleCount){
                var partOfAngle = 360 / angleCount
                angles!![i] = currentAngle + partOfAngle*i
                startRadius = mWidth/2
                var x:Float = (Math.sin(Math.toRadians(angles!![i].toDouble())) * startRadius).toFloat()
                var y:Float = (Math.cos(Math.toRadians(angles!![i].toDouble())) * startRadius).toFloat()
                pointFList?.add(PointF(x,y))
            }
        }
    }

    private fun setUpShader(){

    }

    companion object{
        const val TYPE_CIRCLE = 0
        const val TYPE_ROUND = 1
        const val TYPE_MULTI = 3
        const val ANGLECOUNT = 5
        const val CURRENTANGLE = 180
        const val BODER_RADIUS_DEFAULT = 10
    }
}