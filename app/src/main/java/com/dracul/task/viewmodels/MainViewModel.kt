package com.dracul.task.viewmodels

import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dracul.domain.models.Offer
import com.dracul.domain.usecase.GetOffersUseCase
import com.dracul.task.screens.main.MainFragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel(
    val getOffersUseCase: GetOffersUseCase
) : ViewModel() {

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers = _offers.asStateFlow()

    val isBottomsheetVisible = MutableStateFlow(false)

    init {
        getOffersList()
    }


    private fun getOffersList() {
        viewModelScope.launch {
            _offers.emit(getOffersUseCase.execute().offers)
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


}

