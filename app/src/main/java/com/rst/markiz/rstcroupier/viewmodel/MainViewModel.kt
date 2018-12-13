package com.rst.markiz.rstcroupier.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rst.markiz.rstcroupier.model.Cards
import com.rst.markiz.rstcroupier.model.Constants.VALUE_FIVE
import com.rst.markiz.rstcroupier.model.Constants.VALUE_ONE
import com.rst.markiz.rstcroupier.model.Deck
import com.rst.markiz.rstcroupier.model.NetworkState
import com.rst.markiz.rstcroupier.repo.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * author marcinm on 12/12/2018.
 */
class MainViewModel : ViewModel() {
    private var drawnCards: MutableLiveData<Cards>? = MutableLiveData()
    private val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    private var deck: Deck? = null

    private suspend fun getDeck(): Deck {
        if (deck == null) {
            val job = loadDeck()
            job.join()
        }
        return deck!!
    }

    fun getCards(): LiveData<Cards>? {
        return drawnCards
    }

    fun getNextCards() {
        if (deck != null && drawnCards?.value!!.remaining < VALUE_FIVE) {
            loadReshuffledDeck()
        }
        loadCards(VALUE_FIVE)
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return networkState
    }

    private fun loadDeck() = runBlocking {
        networkState.value = NetworkState.LOADING
        val service = RetrofitFactory.createDeckService()

        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getDeck(VALUE_ONE)
            val response = request.await()
            if (response.isSuccessful) {
                deck = response.body()!!
            } else {
                networkState.value = NetworkState.ERROR
            }
        }
    }

    private fun loadCards(number: Int) {
        networkState.value = NetworkState.LOADING
        val service = RetrofitFactory.createCardsService()

        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getCards(getDeck().deckId, number)
            val response = request.await()
            if (response.isSuccessful) {
                drawnCards?.value = response.body()!!
                networkState.value = NetworkState.SUCCESS
            } else {
                networkState.value = NetworkState.ERROR
            }
        }
    }

    private fun loadReshuffledDeck() {
        networkState.value = NetworkState.LOADING
        val service = RetrofitFactory.createReshuffleDeckService()

        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getReshuffledDeck(deck!!.deckId)
            val response = request.await()
            if (response.isSuccessful) {
                deck = response.body()!!
                loadCards(VALUE_FIVE)
            } else {
                networkState.value = NetworkState.ERROR
            }
        }
    }
}