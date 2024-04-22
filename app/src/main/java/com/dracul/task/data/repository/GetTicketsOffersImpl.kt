package com.dracul.task.data.repository

import com.dracul.task.data.storage.NetworkTicketsOffersStorage
import com.dracul.task.domain.models.Offers
import com.dracul.task.domain.models.TicketsOffers
import com.dracul.task.domain.repository.GetTicketsOffersRepo
import javax.inject.Inject

class GetTicketsOffersImpl @Inject constructor(
    val networkStorage: NetworkTicketsOffersStorage
)  : GetTicketsOffersRepo {
    override fun get(): TicketsOffers {
        return networkStorage.get()
    }
}

