package com.dracul.data.storage

import com.dracul.domain.models.TicketsOffers
import com.example.data.api.TicketApi
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class NetworkTicketsOffersStorage(
    val api: TicketApi
) : TicketsOffersStorage {
    override suspend fun get(): Result<TicketsOffers> {
        return try {
            Result.success(api.getTicketsOffers())
        } catch (e: UnknownHostException) {
            Result.failure(e)
        } catch (e: SocketTimeoutException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
