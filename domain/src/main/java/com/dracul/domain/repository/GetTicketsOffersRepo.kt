package com.dracul.task.domain.repository

import com.dracul.domain.models.TicketsOffers

interface GetTicketsOffersRepo {
    suspend fun get(): Result<TicketsOffers>
}