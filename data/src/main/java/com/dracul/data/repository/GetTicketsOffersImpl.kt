package com.dracul.data.repository

import com.dracul.data.storage.NetworkTicketsOffersStorage
import com.dracul.domain.models.TicketsOffers
import com.dracul.task.domain.repository.GetTicketsOffersRepo

class GetTicketsOffersImpl(
    val networkStorage: NetworkTicketsOffersStorage
)  : GetTicketsOffersRepo {
    override suspend fun get(): TicketsOffers {
        return networkStorage.get()
    }
}

