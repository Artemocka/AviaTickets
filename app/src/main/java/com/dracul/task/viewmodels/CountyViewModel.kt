package com.dracul.task.viewmodels

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dracul.domain.models.TicketsOffer
import com.dracul.domain.usecase.GetTicketsOffersUseCase
import com.dracul.task.getErrorMessage
import com.dracul.task.screens.ticketsoptions.TicketsOptionsFragmentDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//Я считаю что usecase долнжыть инжектиться во вью модель, так по моему мнению правильно
class CountyViewModel(
    val getTicketsOffersUseCase: GetTicketsOffersUseCase,
    val context: Context,
) : ViewModel() {
    private var _ticketsOffers = MutableStateFlow<List<TicketsOffer>>(emptyList())
    var _errorMessage = MutableSharedFlow<String?>()

    var ticketsOffers = _ticketsOffers.asStateFlow()
    var errorMessage = _errorMessage.asSharedFlow()

    val backTicketDate = MutableStateFlow(0.toLong())
    val ticketDate = MutableStateFlow(0.toLong())

    init {
        getTicketsOffers()
    }

    private fun getTicketsOffers() {
        viewModelScope.launch {
            getTicketsOffersUseCase.execute().onFailure {
                _errorMessage.emit(context.getErrorMessage(it))
            }.onSuccess {
                _errorMessage.emit(null)
                _ticketsOffers.emit(it.ticketsOffers)
            }
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

    fun retry() {
        getTicketsOffers()
    }
}

