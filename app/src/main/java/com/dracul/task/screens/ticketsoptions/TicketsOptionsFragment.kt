package com.dracul.task.screens.ticketsoptions

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.dracul.task.R
import com.dracul.task.databinding.FragmentTicketsOptionsBinding
import com.dracul.task.viewmodels.CountyViewModel


class TicketsOptionsFragment : Fragment() {


    private lateinit var binding: FragmentTicketsOptionsBinding
    private val viewModel by viewModels<CountyViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsOptionsBinding.inflate(layoutInflater)

        val from = TicketsOptionsFragmentArgs.fromBundle(requireArguments()).from
        val to = TicketsOptionsFragmentArgs.fromBundle(requireArguments()).to

        binding.edTo.setText(to)
        binding.etFrom.setText(from)
        binding.run {
            item1.run {
                val ticketsOffer = viewModel.getTicketsOffers(0)
                ivCircle.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.red))
                tvTitle.text = ticketsOffer.title
                tvPrice.text = "${ticketsOffer.price.value} ₽ "
                tvTimeRange.text = ticketsOffer.timeRange.concatStrings()
            }
            item2.run {
                val ticketsOffer = viewModel.getTicketsOffers(1)
                ivCircle.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue))
                tvTitle.text = ticketsOffer.title
                tvPrice.text = "${ticketsOffer.price.value} ₽ "
                tvTimeRange.text = ticketsOffer.timeRange.concatStrings()
            }
            item3.run {
                val ticketsOffer = viewModel.getTicketsOffers(2)
                ivCircle.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
                tvTitle.text = ticketsOffer.title
                tvPrice.text = "${ticketsOffer.price.value} ₽ "
                tvTimeRange.text = ticketsOffer.timeRange.concatStrings()
            }
                   }
        return binding.root
    }

    private fun List<String>.concatStrings():String{
        var result =""
        for (i in this)
            result+=i+" "
        return result
    }
}