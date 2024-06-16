package com.dracul.domain.usecase

import com.dracul.domain.models.Offers
import com.dracul.domain.repository.GetOffersRepo

class GetOffersUseCase (
    val repository: GetOffersRepo
) {
    suspend fun execute(): Result<Offers> {
        return repository.get()
    }
}