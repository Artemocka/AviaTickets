package com.dracul.task.data.repository

import com.dracul.task.data.storage.NetworkTicketsStorage
import com.dracul.task.domain.models.Tickets
import com.dracul.task.domain.repository.GetTicketsRepo
import javax.inject.Inject

class GetTicketsImpl @Inject constructor(
    val networkTicketsStorage: NetworkTicketsStorage
):GetTicketsRepo {
    override fun get(): Tickets {
        return networkTicketsStorage.get()
    }
}