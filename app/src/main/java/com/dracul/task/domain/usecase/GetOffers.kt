package com.dracul.task.domain.usecase

import com.dracul.task.domain.models.Offer
import com.dracul.task.domain.models.Offers
import com.dracul.task.domain.repository.GetOffersRepo
import javax.inject.Inject

class GetOffers @Inject constructor(
    val repository: GetOffersRepo
) {
    fun execute(): Offers {
        return repository.get()
    }
}