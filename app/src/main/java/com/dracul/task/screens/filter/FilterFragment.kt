package com.dracul.task.screens.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dracul.task.databinding.FragmentFilterBinding

const val WITHOUT_TRANSFER = "WITHOUT_TRANSFER"
const val WITH_BAGGAGE = "WITH_BAGGAGE"


//В тз ничего не было сказано, функционал очень простой, поэтому без clean architecture

class FilterFragment : Fragment() {

    private lateinit var binding:FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sharedPref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())

        binding = FragmentFilterBinding.inflate(layoutInflater)
        binding.swWithTransfer.isChecked = sharedPref.getBoolean(WITHOUT_TRANSFER, false)
        binding.swWithBaggage.isChecked = sharedPref.getBoolean(WITH_BAGGAGE, false)

        binding.ivClose.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.btnDone.setOnClickListener{
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val sharedPref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        with(sharedPref.edit()){
            Log.e(null, binding.swWithTransfer.isChecked.toString())
            putBoolean(WITHOUT_TRANSFER, binding.swWithTransfer.isChecked)
            putBoolean(WITH_BAGGAGE, binding.swWithBaggage.isChecked)
            apply()
        }
    }
}