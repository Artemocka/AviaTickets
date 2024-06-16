package com.dracul.task.viewmodels

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dracul.domain.models.Offer
import com.dracul.domain.usecase.GetOffersUseCase
import com.dracul.task.getErrorMessage
import com.dracul.task.screens.main.MainFragmentDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel(
    val getOffersUseCase: GetOffersUseCase,
    val context:Context,
) : ViewModel() {

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers = _offers.asStateFlow()
    var _errorMessage = MutableSharedFlow<String?>()
    var errorMessage = _errorMessage.asSharedFlow()

    val isBottomsheetVisible = MutableStateFlow(false)

    init {
        getOffersList()
    }


    private fun getOffersList() {
        viewModelScope.launch {
            getOffersUseCase.execute().onFailure {
                _errorMessage.emit(context.getErrorMessage(it))
            }.onSuccess {
                _errorMessage.emit(null)
                _offers.emit(it.offers)
            }
        }
    }

    fun setAnywhere(edTo: EditText, place: String) {
        edTo.setText(place)
    }

    fun hideBottomsheet() {
        isBottomsheetVisible.value = false
    }

    fun showBottomsheet() {
        isBottomsheetVisible.value = true
    }

    fun setPlaceFromRecomendation(etTo: EditText, town: String) {
        etTo.setText(town)
    }

    fun navigateToPlug(navController: NavController) {
        navController.navigate(MainFragmentDirections.actionPlug())
    }

    fun navigateToTicketsOption(navController: NavController, from: String, to: String) {
        navController.navigate(MainFragmentDirections.actionTicketsOptions(from, to))
    }
    fun retry(){
        getOffersList()
    }

}

