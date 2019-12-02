package com.debasish.texttospeechcoverter.utils

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.debasish.texttospeechcoverter.R
import com.debasish.texttospeechcoverter.base_files.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_alert.view.*


import kotlin.math.roundToInt

class CustomAlertDialog : BaseDialogFragment() {

    override val style: Int
        get() = DialogFragment.STYLE_NO_FRAME
    private var widthScreen: Int = 0
    private var heightScreen: Int = 0
    override val width: Int
        get() = (0.9*widthScreen).roundToInt()
    override val height: Int
        get() = WindowManager.LayoutParams.WRAP_CONTENT
    override val dialogStyle: Int
        get() = R.style.alert_dialog
    override val layoutRes: Int
        get() = R.layout.dialog_alert

    internal lateinit var setOnClickListener: SetOnClickListener

    fun setSetOnClickListener(setOnClickListener: SetOnClickListener) {
        this.setOnClickListener = setOnClickListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
         widthScreen = displayMetrics.widthPixels
         heightScreen = displayMetrics.heightPixels
        view.mbOk.setOnClickListener {
          dismiss()
        }
    }

    interface SetOnClickListener {
        fun authLogout(toString: String)
    }
}