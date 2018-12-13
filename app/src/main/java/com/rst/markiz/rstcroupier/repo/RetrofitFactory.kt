package com.rst.markiz.rstcroupier.repo

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * author marcinm on 12/12/2018.
 */
object RetrofitFactory {

    private const val BASE_URL = "https://deckofcardsapi.com"

    fun createDeckService() : GetDeckRequest{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(GetDeckRequest::class.java)
    }

    fun createCardsService() : GetCardsRequest{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(GetCardsRequest::class.java)
    }

    fun createReshuffleDeckService() : GetReshuffledDeckRequest{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(GetReshuffledDeckRequest::class.java)
    }
}