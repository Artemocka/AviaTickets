package com.dracul.task.data.repository

import com.dracul.task.data.storage.NetworkOffersStorage
import com.dracul.task.domain.models.Offers
import com.dracul.task.domain.repository.GetOffersRepo
import javax.inject.Inject


class GetOffersImpl @Inject constructor(
    val networkStorage:NetworkOffersStorage
): GetOffersRepo {
    override fun get(): Offers {
        return networkStorage.get()
    }
}