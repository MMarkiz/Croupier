package com.rst.markiz.rstcroupier.repo

import com.rst.markiz.rstcroupier.model.Deck
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author marcinm on 13/12/2018.
 */
interface GetReshuffledDeckRequest {

    @GET("/api/deck/{deck_id}/shuffle/")
    fun getReshuffledDeck(
        @Path("deck_id") deckId: String
    ): Deferred<Response<Deck>>
}
