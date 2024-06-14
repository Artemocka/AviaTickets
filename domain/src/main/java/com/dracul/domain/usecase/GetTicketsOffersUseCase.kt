package com.dracul.domain.usecase

import com.dracul.domain.models.TicketsOffers
import com.dracul.task.domain.repository.GetTicketsOffersRepo

class GetTicketsOffersUseCase(
    val repository: GetTicketsOffersRepo
) {
    suspend fun execute(): TicketsOffers {
        return repository.get()
    }
}