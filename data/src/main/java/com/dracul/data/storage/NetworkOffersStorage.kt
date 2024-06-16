package com.dracul.data.storage

import com.dracul.domain.models.Offers
import com.example.data.api.TicketApi
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkOffersStorage(
    val api:TicketApi
): OffersStorage {
    override suspend fun get(): Result<Offers> {
        return try {
            Result.success(api.getOffers())
        } catch (e: UnknownHostException) {
            Result.failure(e)
        } catch (e: SocketTimeoutException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}