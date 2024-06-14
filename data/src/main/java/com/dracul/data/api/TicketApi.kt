package com.example.data.api

import com.dracul.domain.models.Offers
import com.dracul.domain.models.Tickets
import com.dracul.domain.models.TicketsOffers
import retrofit2.http.GET

interface TicketApi {

    @GET("214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun getOffers(): Offers
    @GET("7e55bf02-89ff-4847-9eb7-7d83ef884017")
    suspend fun getTicketsOffers(): TicketsOffers
    @GET("670c3d56-7f03-4237-9e34-d437a9e56ebf")
    suspend fun getTickets(): Tickets

}