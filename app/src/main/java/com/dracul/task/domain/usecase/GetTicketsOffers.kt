package com.dracul.task.domain.usecase

import com.dracul.task.domain.models.TicketsOffers
import com.dracul.task.domain.repository.GetOffersRepo
import com.dracul.task.domain.repository.GetTicketsOffersRepo
import javax.inject.Inject

class GetTicketsOffers@Inject constructor(
    val repository: GetTicketsOffersRepo
) {
    fun execute():TicketsOffers{
        return repository.get()
    }
}