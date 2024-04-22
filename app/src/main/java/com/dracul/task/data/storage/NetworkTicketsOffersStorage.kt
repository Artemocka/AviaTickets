package com.dracul.task.data.storage

import com.dracul.task.domain.models.Offers
import com.dracul.task.domain.models.TicketsOffers
import com.google.gson.Gson

const val TICKETS_OFFERS="""{
  "tickets_offers": [
    {
      "id": 1,
      "title": "Уральские авиалинии",
      "time_range": [
        "07:00",
        "09:10",
        "10:00",
        "11:30",
        "14:15",
        "19:10",
        "21:00",
        "23:30"
      ],
      "price": {
        "value": 3999
      }
    },
    {
      "id": 10,
      "title": "Победа",
      "time_range": [
        "09:10",
        "21:00"
      ],
      "price": {
        "value": 4999
      }
    },
    {
      "id": 30,
      "title": "NordStar",
      "time_range": [
        "07:00"
      ],
      "price": {
        "value": 2390
      }
    }
  ]
}"""

class NetworkTicketsOffersStorage : TicketsOffersStorage {
    override fun get(): TicketsOffers {
        return Gson().fromJson(TICKETS_OFFERS,TicketsOffers::class.java)
    }
}
