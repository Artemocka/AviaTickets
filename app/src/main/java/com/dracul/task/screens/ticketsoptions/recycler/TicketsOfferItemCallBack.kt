package com.dracul.task.screens.ticketsoptions.recycler

import androidx.recyclerview.widget.DiffUtil
import com.dracul.domain.models.TicketsOffer

class TicketsOfferItemCallBack : DiffUtil.ItemCallback<TicketsOffer>() {
    override fun areItemsTheSame(oldItem: TicketsOffer, newItem: TicketsOffer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TicketsOffer, newItem: TicketsOffer): Boolean {
        return oldItem == newItem
    }

}