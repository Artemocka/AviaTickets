package com.dracul.task.screens.ticketsoptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dracul.task.R
import com.dracul.task.databinding.FragmentTicketsOptionsBinding


class TicketsOptionsFragment : Fragment() {


    private lateinit var binding:FragmentTicketsOptionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsOptionsBinding.inflate(layoutInflater)

        return binding.root
    }


}