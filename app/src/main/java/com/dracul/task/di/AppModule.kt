package com.dracul.task.di


import com.dracul.task.data.repository.GetOffersImpl
import com.dracul.task.data.repository.GetTicketsImpl
import com.dracul.task.data.repository.GetTicketsOffersImpl
import com.dracul.task.data.storage.NetworkOffersStorage
import com.dracul.task.data.storage.NetworkTicketsOffersStorage
import com.dracul.task.data.storage.NetworkTicketsStorage
import com.dracul.task.domain.repository.GetOffersRepo
import com.dracul.task.domain.repository.GetTicketsOffersRepo
import com.dracul.task.domain.repository.GetTicketsRepo
import com.dracul.task.domain.usecase.GetOffers
import com.dracul.task.domain.usecase.GetTickets
import com.dracul.task.domain.usecase.GetTicketsOffers
import dagger.Module
import dagger.Provides


@Module
object AppModule {

    @Provides
    fun provideGetTicketsUseCase(repository:GetTicketsRepo): GetTickets {
        return GetTickets(repository)
    }
    @Provides
    fun provideTicketsRepository(newtworkSotrage:NetworkTicketsStorage):GetTicketsRepo{
        return GetTicketsImpl(newtworkSotrage)
    }
    @Provides
    fun provideNetworkTicketsStorage():NetworkTicketsStorage{
        return NetworkTicketsStorage()
    }

    @Provides
    fun provideRepository(newtworkSotrage:NetworkOffersStorage):GetOffersRepo{
        return GetOffersImpl(newtworkSotrage)
    }
    @Provides
    fun provideNetworkStorage():NetworkOffersStorage{
        return NetworkOffersStorage()
    }
    @Provides
    fun provideNetworkTicketsOffersStorage():NetworkTicketsOffersStorage{
        return NetworkTicketsOffersStorage()
    }

    @Provides
    fun provideUseCase(repository:GetOffersRepo): GetOffers {
        return GetOffers(repository)
    }
    @Provides
    fun provideTicketsOffersRepository(newtworkSotrage:NetworkTicketsOffersStorage):GetTicketsOffersRepo{
        return GetTicketsOffersImpl(newtworkSotrage)
    }
    @Provides
    fun provideTicketsOffersUseCase(repository:GetTicketsOffersRepo): GetTicketsOffers {
        return GetTicketsOffers(repository)
    }

}