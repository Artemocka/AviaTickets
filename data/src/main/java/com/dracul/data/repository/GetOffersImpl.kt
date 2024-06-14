package com.dracul.data.repository

import com.dracul.data.storage.NetworkOffersStorage
import com.dracul.domain.models.Offers
import com.dracul.task.domain.repository.GetOffersRepo

class GetOffersImpl(
    val networkStorage: NetworkOffersStorage
): GetOffersRepo {
    override suspend fun get(): Offers {
        return networkStorage.get()
    }
}