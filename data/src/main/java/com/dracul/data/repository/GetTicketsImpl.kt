package com.dracul.data.repository

import com.dracul.data.storage.NetworkTicketsStorage
import com.dracul.domain.models.Tickets
import com.dracul.task.domain.repository.GetTicketsRepo

class GetTicketsImpl (
    val networkTicketsStorage: NetworkTicketsStorage
):GetTicketsRepo {
    override suspend fun get(): Tickets {
        return networkTicketsStorage.get()
    }
}