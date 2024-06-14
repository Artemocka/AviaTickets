package com.dracul.data.storage

import com.dracul.domain.models.Tickets

interface TicketsStorage {
    suspend fun get(): Tickets
}