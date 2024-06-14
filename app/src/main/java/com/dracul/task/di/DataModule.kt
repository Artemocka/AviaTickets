package com.dracul.task.di

import com.dracul.data.repository.GetOffersImpl
import com.dracul.data.repository.GetTicketsImpl
import com.dracul.data.repository.GetTicketsOffersImpl
import com.dracul.data.storage.NetworkOffersStorage
import com.dracul.data.storage.NetworkTicketsOffersStorage
import com.dracul.data.storage.NetworkTicketsStorage
import com.dracul.task.domain.repository.GetOffersRepo
import com.dracul.task.domain.repository.GetTicketsOffersRepo
import com.dracul.task.domain.repository.GetTicketsRepo
import com.example.data.api.TicketApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {

    single<TicketApi> {
        Retrofit.Builder().baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TicketApi::class.java)
    }

    single<GetTicketsRepo> {
        GetTicketsImpl(get())
    }

    single<GetTicketsOffersRepo> {
        GetTicketsOffersImpl(get())
    }
    single<GetOffersRepo> {
        GetOffersImpl(get())
    }
    single<NetworkOffersStorage> {
        NetworkOffersStorage(get())
    }
    single<NetworkTicketsStorage> {
        NetworkTicketsStorage(get())
    }
    single<NetworkTicketsOffersStorage> {
        NetworkTicketsOffersStorage(get())
    }

}