package com.example.bitmapshader

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.RelativeLayout
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Shader
import android.R.attr.bitmap
import android.graphics.BitmapShader
import android.view.View
import android.widget.LinearLayout


class MainActivity : AppCompatActivity() {
    private var mContext: Context? = null
    private var mRL: LinearLayout? = null
    private var mTV: TextView? = null
    private var mBTN: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Get the application context
        mContext = applicationContext

        // Get the widgets reference from XML layout
        mRL = findViewById(R.id.rl)
        mTV = findViewById(R.id.tv)
        mBTN = findViewById(R.id.btn)

        mBTN?.setOnClickListener {
            // Creates Bitmap objects
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.pattern)
            // REPEAT :  replicate the edge color if the shader draws outside of its original bounds
            // CLAMP  :  repeat the shader's image horizontally and vertically, alternating mirror images so that adjacent images always seam
            // MIRROR :  repeat the shader's image horizontally and vertically
            val shader = BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)
            // LAYER_TYPE_NONE : Paint is ignored when the layer type is LAYER_TYPE_NONE
            // LAYER_TYPE_SOFTWARE : A software layer is backed by a bitmap and causes the view to be rendered using Android's software rendering pipeline, even if hardware acceleration is enabled.
            // LAYER_TYPE_HARDWARE
            mTV?.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            mTV?.paint?.shader = shader

        }
    }
}
