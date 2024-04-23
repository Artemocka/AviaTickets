package com.dracul.task.domain.repository

import com.dracul.task.domain.models.Tickets

interface GetTicketsRepo {
    fun get(): Tickets
}