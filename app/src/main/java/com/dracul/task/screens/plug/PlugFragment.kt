package com.dracul.task.screens.plug

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dracul.task.databinding.FragmentPlugBinding


class PlugFragment : Fragment() {

    private lateinit var binding: FragmentPlugBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlugBinding.inflate(layoutInflater)
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }


}