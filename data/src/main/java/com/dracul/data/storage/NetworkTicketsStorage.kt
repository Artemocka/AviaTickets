package com.dracul.data.storage

import com.dracul.domain.models.Tickets
import com.example.data.api.TicketApi
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class NetworkTicketsStorage(
    val api: TicketApi,
) : TicketsStorage {
    override suspend fun get(): Result<Tickets> {
        return try {
            Result.success(api.getTickets())
        }catch (e: UnknownHostException) {
            Result.failure(e)
        } catch (e: SocketTimeoutException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

