package com.dracul.task.screens.ticketsoptions.recycler

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dracul.domain.models.TicketsOffer
import com.dracul.task.R
import com.dracul.task.concatStrings
import com.dracul.task.databinding.ItemTicketOfferBinding


class TicketsOfferAdapter : ListAdapter<TicketsOffer, TicketsOfferAdapter.ViewHolder>(TicketsOfferItemCallBack()) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemTicketOfferBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item, position)
    }


    class ViewHolder(private val binding: ItemTicketOfferBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TicketsOffer, position: Int) {
            binding.run {

                when(position){
                    0->ivCircle.imageTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.red))
                    1->ivCircle.imageTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.blue))
                    2->ivCircle.imageTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.white))
                }

                tvTitle.text = item.title
                tvPrice.text = "${item.price.getSplitted()} ₽ "
                tvTimeRange.text = item.timeRange.concatStrings()
            }
        }
    }
}
