package com.dracul.task.viewmodels

import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dracul.domain.models.TicketsOffer
import com.dracul.domain.usecase.GetTicketsOffersUseCase
import com.dracul.task.screens.ticketsoptions.TicketsOptionsFragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class CountyViewModel(
    val getTicketsOffersUseCase: GetTicketsOffersUseCase
) : ViewModel() {
    val backTicketDate = MutableStateFlow(0.toLong())
    val ticketDate = MutableStateFlow(0.toLong())
    var ticketsOffers: List<TicketsOffer> = emptyList()

    init {
        viewModelScope.launch {
            ticketsOffers = getTicketsOffersUseCase.execute().ticketOffers
        }
    }

    fun setBackTicketDate(timeInMillis: Long) {
        backTicketDate.value = timeInMillis
    }

    fun setTicketDate(timeInMillis: Long) {
        ticketDate.value = timeInMillis
    }

    fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

    fun swap(etFrom: EditText, etTo: EditText) {
        val temp = etFrom.text
        etFrom.text = etTo.text
        etTo.text = temp

    }

    fun navigateToAllTickets(
        navController: NavController,
        from: String,
        to: String,
        date: String,
        passengers: String
    ) {
        val action = TicketsOptionsFragmentDirections.actionAllTickets(
            to, from, date, passengers
        )
        navController.navigate(action)
    }


}

