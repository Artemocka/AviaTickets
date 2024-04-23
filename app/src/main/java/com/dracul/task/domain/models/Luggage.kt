package com.dracul.task.domain.models

import com.dracul.task.domain.models.Price
import com.google.gson.annotations.SerializedName

data class Luggage(
    @SerializedName("has_luggage")
    val hasLuggage: Boolean,
    val price: Price
)