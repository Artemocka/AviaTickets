package com.dracul.domain.models

import com.google.gson.annotations.SerializedName

data class Luggage(
    @SerializedName("has_luggage")
    val hasLuggage: Boolean,
    val price: Price
)