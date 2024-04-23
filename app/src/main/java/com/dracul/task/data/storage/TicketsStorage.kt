package com.dracul.task.data.storage

import com.dracul.task.domain.models.Offers
import com.dracul.task.domain.models.Tickets

interface TicketsStorage {
    fun get(): Tickets
}