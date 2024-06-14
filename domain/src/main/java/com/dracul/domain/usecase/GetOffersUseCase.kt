package com.dracul.domain.usecase

import com.dracul.domain.models.Offers
import com.dracul.task.domain.repository.GetOffersRepo

class GetOffersUseCase (
    val repository: GetOffersRepo
) {
    suspend fun execute(): Offers {
        return repository.get()
    }
}