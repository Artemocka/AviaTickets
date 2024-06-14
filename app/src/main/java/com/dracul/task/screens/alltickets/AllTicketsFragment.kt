package com.dracul.task.screens.alltickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dracul.task.databinding.FragmentAllTicketsBinding
import com.dracul.task.screens.main.recycler.TicketAdapter
import com.dracul.task.viewmodels.AllTicketsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAllTicketsBinding
    private val vm by viewModel<AllTicketsViewModel>()
    private val adapter = TicketAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            vm.tickets.collect{
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val from = AllTicketsFragmentArgs.fromBundle(requireArguments()).from
        val to = AllTicketsFragmentArgs.fromBundle(requireArguments()).to
        val date = AllTicketsFragmentArgs.fromBundle(requireArguments()).arrivalDate
        val passengers = AllTicketsFragmentArgs.fromBundle(requireArguments()).passengers

        binding = FragmentAllTicketsBinding.inflate(layoutInflater)


        binding.run {
            rvTickets.adapter = adapter
            tvDatePassengers.text = "$date, $passengers"
            tvDirection.text = "$from — $to"
            ivBackArrow.setOnClickListener {
                vm.navigateBack(findNavController())
            }
        }
        return binding.root
    }


}