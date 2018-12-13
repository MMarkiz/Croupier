package com.rst.markiz.rstcroupier.view.activity

import android.os.Bundle
import com.rst.markiz.rstcroupier.R
import com.rst.markiz.rstcroupier.model.Constants.FRAGMENT_CONTAINER
import com.rst.markiz.rstcroupier.view.fragment.MainFragment

/**
 * author marcinm on 12/12/2018.
 */
class MainActivity : RstCroupierActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addFragment(MainFragment.newInstance(), FRAGMENT_CONTAINER)
        }
    }
}