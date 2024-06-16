package com.dracul.data.storage

import com.dracul.domain.models.Offers

interface OffersStorage {
    suspend fun get(): Result<Offers>
}