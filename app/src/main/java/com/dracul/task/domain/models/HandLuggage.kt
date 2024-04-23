package com.dracul.task.domain.models

import com.google.gson.annotations.SerializedName

data class HandLuggage(
    @SerializedName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    val size: String
)