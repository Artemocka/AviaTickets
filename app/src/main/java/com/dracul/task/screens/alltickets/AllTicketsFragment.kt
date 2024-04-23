package com.dracul.task.screens.alltickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.core.view.size
import androidx.core.view.updatePaddingRelative
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dracul.task.R
import com.dracul.task.databinding.FragmentAllTicketsBinding
import com.dracul.task.databinding.FragmentMainBinding
import com.dracul.task.screens.main.recycler.TicketAdapter
import com.dracul.task.viewmodels.AllTicketsViewModel

class AllTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAllTicketsBinding
    private val viewModel by viewModels<AllTicketsViewModel>()
    private val adapter = TicketAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.submitList(viewModel.getTickets())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val from = AllTicketsFragmentArgs.fromBundle(requireArguments()).from
        val to = AllTicketsFragmentArgs.fromBundle(requireArguments()).to
        val date = AllTicketsFragmentArgs.fromBundle(requireArguments()).arrivalDate
        val passengers = AllTicketsFragmentArgs.fromBundle(requireArguments()).passengers

        binding = FragmentAllTicketsBinding.inflate(layoutInflater)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() and  WindowInsetsCompat.Type.displayCutout())
            v.updatePaddingRelative(bottom = systemBars.bottom )
            insets
        }

        binding.run {
            rvTickets.adapter = adapter
            tvDatePassengers.text = "$date, $passengers"
            tvDirection.text = "$from—$to"
            ivBackArrow.setOnClickListener {
                viewModel.navigateBack(findNavController())
            }
        }
        return binding.root
    }


}