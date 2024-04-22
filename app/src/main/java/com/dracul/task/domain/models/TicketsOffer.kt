package com.dracul.task.domain.models

import com.google.gson.annotations.SerializedName

data class TicketsOffer(
    val id: Int,
    val price: Price,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val title: String
)