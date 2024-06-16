package com.dracul.domain.repository

import com.dracul.domain.models.Offers

interface GetOffersRepo {
    suspend fun get(): Result<Offers>
}