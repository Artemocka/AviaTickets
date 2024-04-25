package com.dracul.task.screens.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dracul.task.R
import com.dracul.task.databinding.ItemTicketBinding
import com.dracul.task.domain.models.Price
import com.dracul.task.domain.models.Ticket
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt


class TicketAdapter : ListAdapter<Ticket, TicketAdapter.ViewHolder>(TicketItemCallBack()) {


    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemTicketBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )


        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }


    class ViewHolder(private val binding: ItemTicketBinding) : RecyclerView.ViewHolder(binding.root) {
        private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        private val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        fun bind(item: Ticket) {
            binding.run {


                tvAirportArriv.text = item.arrival.airport+"\u00A0"
                tvArrive.text = item.arrival.date.getFormatted()+"\u00A0"
                tvDepar.text = item.departure.date.getFormatted()+"\u00A0"
                tvAirportDepar.text = item.departure.airport+"\u00A0"
                tvAirportArriv.text = item.arrival.airport
                badge.isGone = item.badge.isNullOrEmpty()
                card.updateLayoutParams<MarginLayoutParams> {
                    topMargin = if (item.badge.isNullOrEmpty()) 0 else root.resources.getDimensionPixelSize(R.dimen.card_top_margin)
                }
                tvTimeTransfer.text = calculateFlightTime(item.departure.date,item.arrival.date)
                item.hasTransfer.run {
                    tvTransfer.isVisible=!this
                    tvSlash.isVisible=!this
                    tvTransfer.text="Без пересадок"
                }

                tvPrice.text = "${item.price.toSplitetString()} ₽ "
            }
        }

        private fun String.getFormatted(): String {
            val date = inputFormat.parse(this)
            return outputFormat.format(date ?: "")
        }

        private fun calculateFlightTime(departureDate: String, arrivalTime: String): String {
                                                //yyyy-MM-dd'T'HH:mm:ss
            val departureDate = inputFormat.parse(departureDate)
            val arrivalDate = inputFormat.parse(arrivalTime)
            val diff = arrivalDate.time - departureDate.time
            val flightTime = ((diff/1000)/60)/60f
           return  when(flightTime%1){
                in 0.0..0.25->"${flightTime.roundToInt()}ч в пути"
                in 0.25..0.75->"${flightTime.roundToInt()}.5ч в пути"
                in 0.75..1.0->"${flightTime.roundToInt()+1}ч в пути"
               else -> {""}
           }

        }
        fun Price.toSplitetString():String{
            var result = ""
            var counter =0
            val price = this.value.toString()
            for (i in price.reversed()){
                if (counter!=2){
                    result+=i
                    counter++
                }else{
                    result+=i+" "
                    counter++
                }
            }
            return result.trim().reversed()
        }

    }

}
