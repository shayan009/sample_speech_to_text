package com.debasish.texttospeechcoverter.utils

import android.R
import android.content.Context
import android.graphics.drawable.InsetDrawable
import androidx.recyclerview.widget.DividerItemDecoration
import java.text.SimpleDateFormat
import java.util.*


fun Context.getDate(
    milliSeconds: Long,
    dateFormat: String?
): String? { // Create a DateFormatter object for displaying date in specified format.
    val formatter = SimpleDateFormat(dateFormat)
    // Create a calendar object that will convert the date and time value in milliseconds to date.
    val calendar: Calendar = Calendar.getInstance()
    calendar.setTimeInMillis(milliSeconds)
    return formatter.format(calendar.getTime())
}
fun Context.getItemDecorator(dimen: Int): RecyclerItemDivider? {
    val a = this.obtainStyledAttributes(intArrayOf(R.attr.listDivider))
    val divider = a.getDrawable(0)
    val inset = this.resources.getDimensionPixelSize(dimen)
    val insetDivider = InsetDrawable(divider, inset, 0, inset, 0)
    a.recycle()
    val itemDecoration =
        RecyclerItemDivider(this, DividerItemDecoration.VERTICAL)
    itemDecoration.setDrawable(insetDivider)
    return itemDecoration
}
