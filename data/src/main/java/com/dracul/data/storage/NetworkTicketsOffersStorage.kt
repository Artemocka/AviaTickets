package com.dracul.data.storage

import com.dracul.domain.models.TicketsOffers
import com.example.data.api.TicketApi



class NetworkTicketsOffersStorage(
    val api: TicketApi
) : TicketsOffersStorage {
    override suspend fun get(): TicketsOffers {
        return api.getTicketsOffers()
    }
}
