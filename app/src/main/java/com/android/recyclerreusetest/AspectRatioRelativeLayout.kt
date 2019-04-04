package com.android.recyclerreusetest

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.res.use

/** android lint doesn't recognises kotlin "use" function */
@SuppressLint("Recycle")
open class AspectRatioRelativeLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {

    private var ratio: Float = 0.0f

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioRelativeLayout)
        typedArray.use {
            ratio = it.getFloat(R.styleable.AspectRatioRelativeLayout_heightToWidthRatio, 1.0f)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val originalWidth = View.MeasureSpec.getSize(widthMeasureSpec)

        val desiredHeight = originalWidth * ratio

        super.onMeasure(
            MeasureSpec.makeMeasureSpec(originalWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(Math.round(desiredHeight), MeasureSpec.EXACTLY)
        )
    }

}