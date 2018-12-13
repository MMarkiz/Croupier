package com.rst.markiz.rstcroupier

import android.app.Application
import android.content.Context

/**
 * author marcinm on 12/12/2018.
 */
class RstCroupierApplication : Application() {

    companion object {
        var instance: RstCroupierApplication? = null

        fun getContext(): Context {
            if (instance == null) {
                instance = RstCroupierApplication()
            }
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}