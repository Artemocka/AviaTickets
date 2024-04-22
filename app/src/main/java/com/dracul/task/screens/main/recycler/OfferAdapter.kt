package com.dracul.task.screens.main.recycler

import androidx.recyclerview.widget.ListAdapter
import com.dracul.task.domain.models.Offer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dracul.task.R
import com.dracul.task.databinding.ItemOfferBinding


class OfferAdapter : ListAdapter<Offer, OfferAdapter.ViewHolder>(OfferItemCallBack()) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemOfferBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ViewHolder(binding)


        return viewHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }


    class ViewHolder(private val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Offer) {
            binding.run {
                val price = item.price.value
                tvTitle.text = item.title
                tvPrice.text = "от $price ₽"
                tvTown.text = item.town
                when(item.id){
                    1->{
                        ivIcon.setImageResource(R.drawable.image1)
                    }
                    2->{
                        ivIcon.setImageResource(R.drawable.image2)
                    }
                    3->{
                        ivIcon.setImageResource(R.drawable.image3)
                    }
                }
            }
        }


    }
}
