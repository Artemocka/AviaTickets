package com.dracul.task.data.storage

import com.dracul.task.domain.models.Offers
import com.dracul.task.domain.models.TicketsOffers

interface TicketsOffersStorage {
        fun get(): TicketsOffers
}