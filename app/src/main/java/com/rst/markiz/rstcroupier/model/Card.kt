package com.rst.markiz.rstcroupier.model

import com.squareup.moshi.Json

/**
 * author marcinm on 12/12/2018.
 */
data class Card(
    @field:Json(name = "suit") val suit: String,
    @field:Json(name = "value") val value: String,
    @field:Json(name = "code") val code: String,
    @field:Json(name = "image") val image: String
)
