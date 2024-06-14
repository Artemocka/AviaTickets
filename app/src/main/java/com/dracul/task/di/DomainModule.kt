package com.dracul.task.di


import com.dracul.domain.usecase.GetOffersUseCase
import com.dracul.domain.usecase.GetTicketsOffersUseCase
import com.dracul.domain.usecase.GetTicketsUseCase
import org.koin.dsl.module


val domainModule = module {
    single<GetOffersUseCase> {
        GetOffersUseCase(get())
    }

    single<GetTicketsUseCase> {
        GetTicketsUseCase(get())
    }
    single<GetTicketsOffersUseCase> {
        GetTicketsOffersUseCase(get())
    }
}
