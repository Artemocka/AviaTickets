package com.dracul.data.storage

import com.dracul.domain.models.Offers
import com.example.data.api.TicketApi

class NetworkOffersStorage(
    val api:TicketApi
): OffersStorage {
    override suspend fun get(): Offers {
        return api.getOffers()
    }
}