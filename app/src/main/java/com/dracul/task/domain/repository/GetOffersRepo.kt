package com.dracul.task.domain.repository

import com.dracul.task.domain.models.Offers

interface GetOffersRepo {
    fun get():Offers
}