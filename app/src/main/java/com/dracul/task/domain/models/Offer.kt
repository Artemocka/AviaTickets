package com.dracul.task.domain.models

data class Offer(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)

