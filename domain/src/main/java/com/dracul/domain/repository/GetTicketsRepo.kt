package com.dracul.task.domain.repository

import com.dracul.domain.models.Tickets

interface GetTicketsRepo {
    suspend fun get(): Tickets
}