package com.rst.markiz.rstcroupier.view.fragment

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rst.markiz.rstcroupier.R
import com.rst.markiz.rstcroupier.model.Card
import com.rst.markiz.rstcroupier.model.Constants.CARD_JACK
import com.rst.markiz.rstcroupier.model.Constants.CARD_KING
import com.rst.markiz.rstcroupier.model.Constants.CARD_QUEEN
import com.rst.markiz.rstcroupier.model.Constants.VALUE_ONE
import com.rst.markiz.rstcroupier.model.Constants.VALUE_TWO
import com.rst.markiz.rstcroupier.model.NetworkState
import com.rst.markiz.rstcroupier.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.StringBuilder

/**
 * author marcinm on 12/12/2018.
 */
class MainFragment : RstCroupierFragment(), View.OnClickListener {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    private lateinit var resultInfo: StringBuilder

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deck.setOnClickListener(this)
        checkInternetConnection()
        viewModel.getCards()?.observe(this, Observer {
            checkResult(viewModel.getCards()?.value!!.cards)
        })

        viewModel.getNetworkState().observe(this, Observer { state ->
            if (state != null) {
                when (state.status) {
                    NetworkState.Status.LOADING -> {
                        showLoading(true)
                    }
                    NetworkState.Status.SUCCESS -> showLoading(false)
                    NetworkState.Status.ERROR -> {
                        showLoading(false)
                        showDialog(resources.getString(R.string.error_info))
                    }
                }
            }
        })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.deck -> {
                viewModel.getNextCards()
            }
        }
    }


    private fun checkResult(cards: List<Card>) {
        resultInfo = StringBuilder()
        showDrawnCards(cards)
        checkSuit(cards)
        checkValue(cards)
        checkFigures(cards)
        cards_number.text = resources.getString(R.string.deck_left_cards, viewModel.getCards()!!.value!!.remaining)
        result.text = resultInfo
    }


    private fun checkSuit(cards: List<Card>) {
        if (cards.groupingBy { it.suit }.eachCount().filter { it.value > 2 }.size == VALUE_ONE) {
            resultInfo.appendln(resources.getString(R.string.result_colour))
        }
    }

    private fun checkValue(cards: List<Card>) {
        if (cards.groupingBy { it.value }.eachCount().filter { it.value > 2 }.size == VALUE_ONE) {
            resultInfo.appendln(resources.getString(R.string.result_twins))
        }
    }

    private fun checkFigures(cards: List<Card>) {
        if (cards.count { it.value == CARD_JACK || it.value == CARD_QUEEN || it.value == CARD_KING } > VALUE_TWO) {
            resultInfo.appendln(resources.getString(R.string.result_figures))
        }
    }

    private fun showDrawnCards(cards: List<Card>?) {
        if (cards != null) {
            Glide.with(this).load(cards[0].image).into(card_first)
            Glide.with(this).load(cards[1].image).into(card_second)
            Glide.with(this).load(cards[2].image).into(card_third)
            Glide.with(this).load(cards[3].image).into(card_fourth)
            Glide.with(this).load(cards[4].image).into(card_fifth)
        }
    }

    private fun showConnectionErrorDialog() {
        AlertDialog.Builder(activity)
            .setCancelable(false)
            .setTitle(R.string.connection_error)
            .setMessage(R.string.connection_error_message)
            .setPositiveButton(R.string.connection_check_again) { dialog, _ ->
                checkInternetConnection()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.connection_close_app) { _, _ -> activity?.finish() }
            .show()
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(activity)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(R.string.error_ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun checkInternetConnection() {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork?.isConnected != true) {
            showConnectionErrorDialog()
        }
    }
}