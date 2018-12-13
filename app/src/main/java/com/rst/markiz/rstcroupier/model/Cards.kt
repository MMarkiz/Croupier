package com.rst.markiz.rstcroupier.model

import com.squareup.moshi.Json

/**
 * author marcinm on 12/12/2018.
 */
data class Cards(
    @field:Json(name = "success") val success: Boolean,
    @field:Json(name = "cards") val cards: List<Card>,
    @field:Json(name = "deck_id") val deckId: String,
    @field:Json(name = "remaining") val remaining: Int
)