package com.dracul.task.domain.usecase

import com.dracul.task.domain.models.Offer
import com.dracul.task.domain.models.Offers
import com.dracul.task.domain.models.Tickets
import com.dracul.task.domain.repository.GetOffersRepo
import com.dracul.task.domain.repository.GetTicketsRepo
import javax.inject.Inject

class GetTickets @Inject constructor(
    val repository: GetTicketsRepo
) {
    fun execute(): Tickets {
        return repository.get()
    }
}