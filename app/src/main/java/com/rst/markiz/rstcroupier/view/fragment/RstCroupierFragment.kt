package com.rst.markiz.rstcroupier.view.fragment

import android.support.v4.app.Fragment
import com.rst.markiz.rstcroupier.view.activity.RstCroupierActivity

/**
 * author marcinm on 12/12/2018.
 */
abstract class RstCroupierFragment : Fragment() {
    protected open fun showLoading(show: Boolean) {
        if (activity != null) {
            (activity as RstCroupierActivity).showLoading(show)
        }
    }
}