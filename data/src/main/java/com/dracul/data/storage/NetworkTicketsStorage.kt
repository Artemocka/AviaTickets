package com.dracul.data.storage

import com.dracul.domain.models.Tickets
import com.example.data.api.TicketApi


class NetworkTicketsStorage(
    val api: TicketApi,
) : TicketsStorage {
    override suspend fun get(): Tickets {
        return api.getTickets()
    }
}