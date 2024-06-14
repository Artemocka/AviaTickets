package com.dracul.task.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dracul.domain.models.Ticket
import com.dracul.domain.usecase.GetTicketsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AllTicketsViewModel(
    val getTicketsUseCase: GetTicketsUseCase
):ViewModel() {
    private val _tickets = MutableStateFlow<List<Ticket>>(emptyList())
    val tickets = _tickets.asStateFlow()

    init {
        getTickets()
    }

    fun getTickets(){
        viewModelScope.launch {
            _tickets.emit(getTicketsUseCase.execute().tickets)
        }
    }

    fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

}