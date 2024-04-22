package com.dracul.task.data.storage

import com.dracul.task.domain.models.Offers
import com.google.gson.Gson

const val OFFERS="""{
  "offers": [
    {
      "id": 1,
      "title": "Die Antwoord",
      "town": "Будапешт",
      "price": {
        "value": 5000
      }
    },
    {
      "id": 2,
      "title": "Socrat&Lera",
      "town": "Санкт-Петербург",
      "price": {
        "value": 1999
      }
    },
    {
      "id": 3,
      "title": "Лампабикт",
      "town": "Москва",
      "price": {
        "value": 2390
      }
    }
  ]
}"""

class NetworkOffersStorage: OffersStorage {
    override fun get(): Offers {
        return Gson().fromJson(OFFERS,Offers::class.java)
    }
}