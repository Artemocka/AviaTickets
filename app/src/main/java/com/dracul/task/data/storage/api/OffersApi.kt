package com.dracul.task.data.storage.api

import com.dracul.task.domain.models.Offers
import retrofit2.http.GET

interface OffersApi {
    @GET("1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav/view")
    suspend fun getOffers():Offers
}