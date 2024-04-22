package com.dracul.task.data.storage

import com.dracul.task.domain.models.Offers

interface OffersStorage {
    fun get():Offers
}