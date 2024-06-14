package com.dracul.domain.models

import com.google.gson.annotations.SerializedName

data class TicketsOffers(
    @SerializedName("ticket_offers")
    val ticketOffers: List<TicketsOffer>
)