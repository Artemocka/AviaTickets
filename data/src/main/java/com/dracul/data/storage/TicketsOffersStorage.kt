package com.dracul.data.storage

import com.dracul.domain.models.TicketsOffers

interface TicketsOffersStorage {
        suspend fun get(): Result<TicketsOffers>
}