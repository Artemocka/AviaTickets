package com.dracul.task.screens.ticketsoptions

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dracul.task.R
import com.dracul.task.databinding.FragmentTicketsOptionsBinding
import com.dracul.task.viewmodels.CountyViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class TicketsOptionsFragment : Fragment() {


    private lateinit var binding: FragmentTicketsOptionsBinding
    private val viewModel by viewModel<CountyViewModel>()
    private val dateBackPicker =
        MaterialDatePicker.Builder.datePicker().setTitleText("Select date").setSelection(
            MaterialDatePicker.todayInUtcMilliseconds()
        ).build()
    private val datePicker =
        MaterialDatePicker.Builder.datePicker().setTitleText("Select date").setSelection(
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

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.backTicketDate.collect {
                    if (it != 0.toLong()) {
                        calendar.timeInMillis = it
                        chpBack.text = getFormattedSpannable()
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.ticketDate.collect {
                    if (it != 0.toLong()) {
                        calendar.timeInMillis = it
                        chpDate.text = getFormattedSpannable()
                    } else {
                        chpDate.text = getFormattedSpannable()
                    }
                }
            }

            chpBack.run {
                chpBack.setItalic()
                setOnClickListener {
                    dateBackPicker.show(childFragmentManager, "datePicker")
                }
            }
            chpPassengers.setItalic()
            chpSettings.setItalic()
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
                viewModel.navigateToAllTickets(findNavController(), from, to, date, passengers)
            }

            item1.run {
                val ticketsOffer = viewModel.ticketsOffers.get(0)
                ivCircle.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.red))
                tvTitle.text = ticketsOffer.title
                tvPrice.text = "${ticketsOffer.price.getSplitted()} ₽ "
                tvTimeRange.text = ticketsOffer.timeRange.concatStrings()
            }
            item2.run {
                val ticketsOffer = viewModel.ticketsOffers.get(1)
                ivCircle.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue))
                tvTitle.text = ticketsOffer.title
                tvPrice.text = "${ticketsOffer.price.getSplitted()} ₽ "
                tvTimeRange.text = ticketsOffer.timeRange.concatStrings()
            }
            item3.run {
                val ticketsOffer = viewModel.ticketsOffers.get(2)
                ivCircle.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
                tvTitle.text = ticketsOffer.title
                tvPrice.text = "${ticketsOffer.price.getSplitted()} ₽ "
                tvTimeRange.text = ticketsOffer.timeRange.concatStrings()
            }
        }
        return binding.root
    }

    private fun getFormattedSpannable(): Spannable {
        val dateFormat = SimpleDateFormat("dd MMM',' E", Locale("ru", "RU"))
        val formattedDate = dateFormat.format(calendar.time).replace(".", "").toSpannable()
        val spannable = formattedDate.toSpannable()
        val start = 0
        val end = spannable.length
        spannable.setSpan(
            StyleSpan(Typeface.ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    requireContext(), R.color.grey_6
                )
            ), end - 2, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        return spannable
    }

    private fun Chip.setItalic() {
        val spannable = this.text.toSpannable()
        val start = 0
        val end = spannable.length
        spannable.setSpan(
            StyleSpan(Typeface.ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        this.text = spannable
    }

    private fun List<String>.concatStrings(): String {
        var result = ""
        for (i in this) result += "$i "
        return result
    }


}