package com.dracul.task.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dracul.task.di.DaggerInjector
import com.dracul.task.domain.usecase.GetTickets
import com.dracul.task.domain.usecase.GetTicketsOffers
import dagger.Component
import dagger.Module
import javax.inject.Inject


@Component(modules = [AllTicketsModule::class], dependencies = [AllTicketsDependencies::class])
interface AllTicketsComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: AllTicketsDependencies): Builder
        fun build(): AllTicketsComponent
    }

    fun inject(target: AllTicketsViewModel)
}

@Module
class AllTicketsModule

interface AllTicketsDependencies {
    fun getTickets(): GetTickets
}


class AllTicketsViewModel:ViewModel() {
    fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

    @Inject
    lateinit var getTickets: GetTickets

    fun getTickets()=getTickets.execute().tickets


    init {
        DaggerAllTicketsComponent.builder().dependencies(dependencies = DaggerInjector.appComponent).build().inject(this@AllTicketsViewModel)
    }

}