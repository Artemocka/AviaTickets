package com.dracul.task.di


import com.dracul.task.viewmodels.AllTicketsViewModel
import com.dracul.task.viewmodels.CountyViewModel
import com.dracul.task.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel {
        AllTicketsViewModel(
            getTicketsUseCase = get(),
            context = get(),
        )
    }
    viewModel {
        MainViewModel(
            getOffersUseCase = get(),
            context = get()
        )
    }
    viewModel {
        CountyViewModel(
            getTicketsOffersUseCase = get(),
            context = get(),
        )
    }

}


