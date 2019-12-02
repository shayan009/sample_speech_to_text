package com.debasish.texttospeechcoverter.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class RecyclerItemDivider(context: Context, orientation: Int) :
    ItemDecoration() {
    private var mDivider: Drawable?
    private var mOrientation = 0
    private val mBounds = Rect()
    fun setOrientation(orientation: Int) {
        require(!(orientation != 0 && orientation != 1)) { "Invalid orientation. It should be either HORIZONTAL or VERTICAL" }
        mOrientation = orientation
    }

    fun setDrawable(drawable: Drawable) {
        requireNotNull(drawable) { "Drawable cannot be null." }
        mDivider = drawable
    }

    override fun onDraw(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.layoutManager != null && mDivider != null) {
            if (mOrientation == 1) {
                drawVertical(c, parent)
            } else {
                drawHorizontal(c, parent)
            }
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left,
                parent.paddingTop,
                right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val bottom = mBounds.bottom + Math.round(child.translationY)
            val top = bottom - mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(canvas)
        }
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(
                parent.paddingLeft,
                top,
                parent.width - parent.paddingRight,
                bottom
            )
        } else {
            top = 0
            bottom = parent.height
        }
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)
            val right = mBounds.right + Math.round(child.translationX)
            val left = right - mDivider!!.intrinsicWidth
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(canvas)
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (mDivider == null) {
            outRect[0, 0, 0] = 0
        } else {
            if (mOrientation == 1) {
                outRect[0, 0, 0] = mDivider!!.intrinsicHeight
            } else {
                outRect[0, 0, mDivider!!.intrinsicWidth] = 0
            }
        }
    }

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
        private const val TAG = "DividerItem"
        private val ATTRS = intArrayOf(16843284)
    }

    init {
        val a = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        if (mDivider == null) {
            Log.w(
                "DividerItem",
                "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()"
            )
        }
        a.recycle()
        setOrientation(orientation)
    }
}