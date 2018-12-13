package com.rst.markiz.rstcroupier.model

/**
 * author marcinm on 15/11/2018.
 */
open class NetworkState protected constructor(val status: Status) {

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {

        val LOADING = NetworkState(Status.LOADING)
        val SUCCESS = NetworkState(Status.SUCCESS)
        val ERROR = NetworkState(Status.ERROR)
    }
}
