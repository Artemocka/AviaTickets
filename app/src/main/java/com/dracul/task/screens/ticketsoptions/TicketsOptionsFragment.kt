package com.dracul.task.screens.ticketsoptions

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.Insets
import androidx.core.text.toSpannable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dracul.task.R
import com.dracul.task.databinding.FragmentTicketsOptionsBinding
import com.dracul.task.screens.ticketsoptions.recycler.TicketsOfferAdapter
import com.dracul.task.viewmodels.CountyViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class TicketsOptionsFragment : Fragment() {


    private lateinit var binding: FragmentTicketsOptionsBinding
    private val viewModel by viewModel<CountyViewModel>()
    private val ticketOffersAdapter = TicketsOfferAdapter()
    private var snackbar: Snackbar? = null
    private val dateBackPicker =
        MaterialDatePicker.Builder.datePicker().setTitleText("Select date").setSelection(
            MaterialDatePicker.todayInUtcMilliseconds()
        ).build()

    private val datePicker =
        MaterialDatePicker.Builder.datePicker().setTitleText("Select date").setSelection(
            MaterialDatePicker.todayInUtcMilliseconds()
        ).build()

    private val calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.ticketsOffers.collect {
                ticketOffersAdapter.submitList(it)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsOptionsBinding.inflate(layoutInflater)
        val from = TicketsOptionsFragmentArgs.fromBundle(requireArguments()).from
        val to = TicketsOptionsFragmentArgs.fromBundle(requireArguments()).to

        binding.run {
            ViewCompat.setOnApplyWindowInsetsListener(root) { _, insets ->
                WindowInsetsCompat.Builder(insets)
                    .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.NONE)
                    .build()
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.errorMessage.collect { message ->
                    if (message != null) {
                        snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
                            .setAction(getString(R.string.retry)) { viewModel.retry() }
                            .setAnimationMode(
                                BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                            )
                        snackbar?.show()
                    } else {
                        snackbar?.dismiss()
                    }
                }
            }
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
            rvTicketsOffers.adapter = ticketOffersAdapter
            etFrom.setText(from)
            etTo.setText(to)
            chpBack.run {
                chpBack.setItalic()
                setOnClickListener {
                    dateBackPicker.show(childFragmentManager, "datePicker")
                }
            }

            chpSettings.setOnClickListener {
                viewModel.navigatetoFilter(findNavController())
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


}