package com.rst.markiz.rstcroupier.view.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.annotation.StyleRes
import com.rst.markiz.rstcroupier.R
import kotlinx.android.synthetic.main.dialog_loading.*

/**
 * author marcinm on 13/12/2018.
 */
class LoadingDialog constructor(
    context: Context, @StyleRes theme: Int,
    cancelable: Boolean = false,
    cancelListener: DialogInterface.OnCancelListener? = null
) : Dialog(context, theme) {

    init {
        setCancelable(cancelable)
        setCanceledOnTouchOutside(cancelable)
        setOnCancelListener(cancelListener)
        init()
    }

    private fun init() {
        super.setContentView(R.layout.dialog_loading)
        background.alpha = 0.5f
    }
}