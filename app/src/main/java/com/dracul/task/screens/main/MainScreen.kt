package com.dracul.task.screens.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dracul.task.R
import com.dracul.task.databinding.FragmentMainScreenBinding
import com.dracul.task.screens.main.recycler.OfferAdapter
import com.dracul.task.viewmodels.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainScreen : Fragment() {

    private lateinit var binding:FragmentMainScreenBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapter =OfferAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(layoutInflater)
        binding.rvMusicSuggestion.adapter= adapter
        adapter.submitList(viewModel.getOffersList())


        binding.included.bottomsheet.getBehavior().isHideable =true
        binding.included.bottomsheet.getBehavior().setHidden()



        binding.edTo.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edTo.clearFocus()
                binding.included.bottomsheet.getBehavior().state = BottomSheetBehavior.STATE_EXPANDED
                binding.hideKeyboard()
                val backPressCallback= requireActivity().onBackPressedDispatcher.addCallback{
                    if (binding.included.bottomsheet.getBehavior().state != BottomSheetBehavior.STATE_HIDDEN) {
                        binding.included.bottomsheet.getBehavior().state = BottomSheetBehavior.STATE_HIDDEN
                    }else
                        this.remove()

                }
            }
        }

        return binding.root
    }



    private fun View.getBehavior(): BottomSheetBehavior<View> {
        val params = layoutParams as CoordinatorLayout.LayoutParams
        return params.behavior as BottomSheetBehavior
    }

    private fun BottomSheetBehavior<View>.setHidden() {
        this.state = BottomSheetBehavior.STATE_HIDDEN
    }
    private fun FragmentMainScreenBinding.hideKeyboard() {
        val imm = this.root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}