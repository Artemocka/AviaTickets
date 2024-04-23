package com.dracul.task.screens.ticketsoptions

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dracul.task.R
import com.dracul.task.databinding.FragmentTicketsOptionsBinding
import com.dracul.task.domain.models.Price
import com.dracul.task.viewmodels.CountyViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class TicketsOptionsFragment : Fragment() {


    private lateinit var binding: FragmentTicketsOptionsBinding
    private val viewModel by viewModels<CountyViewModel>()
    private val dateBackPicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date").setSelection(
        MaterialDatePicker.todayInUtcMilliseconds()
    ).build()
    private val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date").setSelection(
        MaterialDatePicker.todayInUtcMilliseconds()
    ).build()

    private val calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsOptionsBinding.inflate(layoutInflater)

        val from = TicketsOptionsFragmentArgs.fromBundle(requireArguments()).from
        val to = TicketsOptionsFragmentArgs.fromBundle(requireArguments()).to

        binding.etTo.setText(to)
        binding.etFrom.setText(from)
        binding.run {

            lifecycleScope.launch {
                viewModel.backTicketDate.collect {
                    if (it != 0.toLong()) {
                        calendar.timeInMillis = it
                        chpBack.text = getFormattedSpannable()
                    }
                }
            }
            lifecycleScope.launch {
                viewModel.ticketDate.collect {
                    if (it != 0.toLong()) {
                        calendar.timeInMillis = it
                        chpDate.text = getFormattedSpannable()
                    } else {
                        chpDate.text = getFormattedSpannable()
                    }
                }
            }

            chpBack.setOnClickListener {
                dateBackPicker.show(childFragmentManager, "datePicker")
            }
            chpDate.setOnClickListener {
                datePicker.show(childFragmentManager, "datePicker")
            }

            dateBackPicker.addOnPositiveButtonClickListener {
                viewModel.setBackTicketDate(it)
            }
            datePicker.addOnPositiveButtonClickListener {
                viewModel.setTicketDate(it)
            }

            ivBackArrow.setOnClickListener {
                viewModel.navigateBack(findNavController())
            }
            ivChange.setOnClickListener {
                viewModel.swap(binding.etFrom, binding.etTo)
            }
            ivClear.setOnClickListener {
                etTo.text.clear()
            }

            seeAllTickets.setOnClickListener {
                val date = chpDate.text.toString()
                val passengers = chpPassengers.text.toString()
                viewModel.navigateToAllTickets(findNavController(),from,to,date,passengers)
            }

            item1.run {
                val ticketsOffer = viewModel.getTicketsOffers(0)
                ivCircle.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.red))
                tvTitle.text = ticketsOffer.title
                tvPrice.text = "${ticketsOffer.price.toSplitetString()} ₽ "
                tvTimeRange.text = ticketsOffer.timeRange.concatStrings()
            }
            item2.run {
                val ticketsOffer = viewModel.getTicketsOffers(1)
                ivCircle.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue))
                tvTitle.text = ticketsOffer.title
                tvPrice.text = "${ticketsOffer.price.toSplitetString()} ₽ "
                tvTimeRange.text = ticketsOffer.timeRange.concatStrings()
            }
            item3.run {
                val ticketsOffer = viewModel.getTicketsOffers(2)
                ivCircle.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
                tvTitle.text = ticketsOffer.title
                tvPrice.text = "${ticketsOffer.price.toSplitetString()} ₽ "
                tvTimeRange.text = ticketsOffer.timeRange.concatStrings()
            }
        }
        return binding.root
    }

    private fun getFormattedSpannable(): Spannable {
        val dateFormat = SimpleDateFormat("dd MMM',' E", Locale("ru", "RU"))
        val formattedDate = dateFormat.format(calendar.time).replace(".", "").toSpannable()
        val spannable = formattedDate.toSpannable()
        spannable.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    requireContext(), R.color.grey_6
                )
            ), formattedDate.length - 2, formattedDate.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        return spannable
    }

    private fun List<String>.concatStrings(): String {
        var result = ""
        for (i in this) result += i + " "
        return result
    }

    private fun Price.toSplitetString():String{
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