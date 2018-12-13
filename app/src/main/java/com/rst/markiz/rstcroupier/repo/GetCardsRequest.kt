package com.rst.markiz.rstcroupier.repo

import com.rst.markiz.rstcroupier.model.Cards
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * author marcinm on 12/12/2018.
 */
interface GetCardsRequest {

    @GET("/api/deck/{deck_id}/draw")
    fun getCards(
        @Path("deck_id") deckId: String,
        @Query("count") count: Int
    ): Deferred<Response<Cards>>
}