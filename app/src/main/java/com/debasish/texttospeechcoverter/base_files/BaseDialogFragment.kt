package com.debasish.texttospeechcoverter.base_files

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment


abstract class BaseDialogFragment : DialogFragment() {


    internal abstract val layoutRes: Int
    internal abstract val dialogStyle: Int
    internal abstract val width: Int
    internal abstract val height: Int
    internal abstract val style: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(layoutRes, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(width,height)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val custumAlertDialog = super.onCreateDialog(savedInstanceState)
        setStyle(style, dialogStyle)
        custumAlertDialog.setCanceledOnTouchOutside(true)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
        return custumAlertDialog
    }
}
