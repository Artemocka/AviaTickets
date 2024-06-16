package com.dracul.task.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dracul.domain.models.Ticket
import com.dracul.domain.usecase.GetTicketsUseCase
import com.dracul.task.getErrorMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AllTicketsViewModel(
    val getTicketsUseCase: GetTicketsUseCase,
    val context: Context,
) : ViewModel() {
    private val _tickets = MutableStateFlow<List<Ticket>>(emptyList())
    private  var _errorMessage = MutableSharedFlow<String?>()
    val tickets = _tickets.asStateFlow()
    var errorMessage = _errorMessage.asSharedFlow()

    init {
        getTickets()
    }

    private fun getTickets() {
        viewModelScope.launch {
            val result = getTicketsUseCase.execute()
            result.onFailure {
                _errorMessage.emit(context.getErrorMessage(it))
            }
            result.onSuccess {
                _errorMessage.emit(null)
                _tickets.emit(it.tickets)
            }
        }

    }

    fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

    fun retry() {
        getTickets()
    }

}