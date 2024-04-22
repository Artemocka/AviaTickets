package com.dracul.task.domain.repository

import com.dracul.task.domain.models.Offers
import com.dracul.task.domain.models.TicketsOffers

interface GetTicketsOffersRepo {
    fun get():TicketsOffers
}