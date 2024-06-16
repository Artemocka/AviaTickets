package com.dracul.domain.usecase

import com.dracul.domain.models.Tickets
import com.dracul.task.domain.repository.GetTicketsRepo

class GetTicketsUseCase(
    val repository: GetTicketsRepo
) {
    suspend fun execute(): Result<Tickets> {
        return repository.get()
    }
}