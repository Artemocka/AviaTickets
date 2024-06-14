package com.dracul.domain.models

data class Offer(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)

