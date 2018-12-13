package com.rst.markiz.rstcroupier.repo

import com.rst.markiz.rstcroupier.model.Deck
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author marcinm on 12/12/2018.
 */
interface GetDeckRequest {

    @GET("/api/deck/new/shuffle")
    fun getDeck(
        @Query("deck_count") deckCount: Int
    ): Deferred<Response<Deck>>
}