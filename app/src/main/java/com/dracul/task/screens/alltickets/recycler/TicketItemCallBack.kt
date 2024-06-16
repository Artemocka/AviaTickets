package com.dracul.task.screens.alltickets.recycler

import androidx.recyclerview.widget.DiffUtil
import com.dracul.domain.models.Ticket

class TicketItemCallBack : DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }

}