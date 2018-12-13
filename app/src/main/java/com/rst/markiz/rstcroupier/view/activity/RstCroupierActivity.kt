package com.rst.markiz.rstcroupier.view.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.rst.markiz.rstcroupier.R
import com.rst.markiz.rstcroupier.view.dialog.LoadingDialog

/**
 * author marcinm on 12/12/2018.
 */
abstract class RstCroupierActivity : AppCompatActivity() {


    private var loadingDialog: LoadingDialog? = null

    fun showLoading(show: Boolean) {
        if (show) {
            if (loadingDialog == null) {
                loadingDialog = LoadingDialog(this, R.style.AppTheme)
            }
            if (!loadingDialog!!.isShowing) {
                loadingDialog!!.show()
            }
        } else {
            if (loadingDialog != null && loadingDialog!!.isShowing) {
                loadingDialog!!.dismiss()
            }
        }
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }
}