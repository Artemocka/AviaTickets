package com.dracul.task.viewmodels

import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dracul.task.di.DaggerInjector
import com.dracul.task.domain.usecase.GetOffers
import com.dracul.task.domain.usecase.GetTicketsOffers
import dagger.Component
import dagger.Module
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@Component(modules = [CountryModule::class], dependencies = [CountryDependencies::class])
interface CountryComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: CountryDependencies): Builder
        fun build(): CountryComponent
    }

    fun inject(target: CountyViewModel)
}

@Module
class CountryModule

interface CountryDependencies {
    fun getTicketsOffers(): GetTicketsOffers
}

class CountyViewModel : ViewModel() {
    @Inject
    lateinit var getTicketsOffers: GetTicketsOffers
    val backTicketDate = MutableStateFlow(0.toLong())
    val ticketDate = MutableStateFlow(0.toLong())

    init {
        DaggerCountryComponent.builder().dependencies(dependencies = DaggerInjector.appComponent).build().inject(this@CountyViewModel)
    }

    fun getTicketsOffers(index: Int) = getTicketsOffers.execute().tickets_offers[index]
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


}

