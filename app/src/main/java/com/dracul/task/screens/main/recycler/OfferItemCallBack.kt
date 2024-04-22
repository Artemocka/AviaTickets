package com.dracul.task.screens.main.recycler

import androidx.recyclerview.widget.DiffUtil
import com.dracul.task.domain.models.Offer

class OfferItemCallBack : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

}