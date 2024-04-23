package com.dracul.task.screens.main.recycler

import androidx.recyclerview.widget.DiffUtil
import com.dracul.task.domain.models.Offer
import com.dracul.task.domain.models.Ticket

class TicketItemCallBack : DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }

}