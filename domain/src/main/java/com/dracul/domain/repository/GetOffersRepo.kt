package com.dracul.task.domain.repository

import com.dracul.domain.models.Offers

interface GetOffersRepo {
    suspend fun get(): Offers
}