package com.dracul.task.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dracul.task.R
import com.dracul.task.databinding.FragmentAllTicketsBinding
import com.dracul.task.databinding.FragmentMainBinding

class AllTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAllTicketsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTicketsBinding.inflate(layoutInflater)
        return binding.root
    }


}