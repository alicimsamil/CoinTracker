package com.alicimsamil.cointracker.feature.detail.presentation.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class LineChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var chartData: List<Double> = emptyList()
    private var chartWidth = 0
    private var chartHeight = 0
    private var maxChartValue = 0.0
    private var minChartValue = 0.0
    private var valueRange = 0.0
    private var xInterval = 0.0
    private var yInterval = 0.0
    private var linePaint: Paint? = null

    init {
        initPaints()
    }

    private fun initPaints() {
        linePaint = Paint()
        linePaint?.strokeWidth = LINE_STROKE_WIDTH.toFloat()
        linePaint?.style = Paint.Style.STROKE
        linePaint?.color = Color.BLUE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chartWidth = w - 2 * CHART_PADDING
        chartHeight = h - 2 * CHART_PADDING
        calculateChartParameters()
    }

    private fun calculateChartParameters() {
        maxChartValue = Double.MIN_VALUE
        minChartValue = Double.MAX_VALUE
        for (value in chartData) {
            maxChartValue = maxChartValue.coerceAtLeast(value)
            minChartValue = minChartValue.coerceAtMost(value)
        }
        valueRange = maxChartValue - minChartValue
        xInterval = (chartWidth / (chartData.size - 1)).toDouble()
        yInterval = chartHeight / valueRange
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val path = Path()
        for (i in chartData.indices) {
            val x = (CHART_PADDING + i * xInterval).toFloat()
            val y = (CHART_PADDING + chartHeight - (chartData[i] - minChartValue) * yInterval).toFloat()
            if (i == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }
        linePaint?.let { itPaint ->
            canvas.drawPath(path, itPaint)
        }
    }

    fun setChartData(list: List<Double>){
        chartData = list
        calculateChartParameters()
        invalidate()
        requestLayout()
    }

    companion object {
        private const val CHART_PADDING = 5
        private const val LINE_STROKE_WIDTH = 5
    }

}