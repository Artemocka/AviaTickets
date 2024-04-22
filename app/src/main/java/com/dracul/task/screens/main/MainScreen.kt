package com.dracul.task.screens.main

import android.content.Context
import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dracul.task.R
import com.dracul.task.databinding.FragmentMainScreenBinding
import com.dracul.task.screens.main.recycler.OfferAdapter
import com.dracul.task.viewmodels.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.launch


class MainScreen : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapter = OfferAdapter()
    private var backPressCallback: OnBackPressedCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(layoutInflater)



        lifecycleScope.launch {
            viewModel.isBottomsheetVisible.collect {
                when (it) {
                    true -> {
                        binding.included.bottomsheet.getBehavior().setExpanded()
                        backPressCallback = requireActivity().onBackPressedDispatcher.addCallback {
                            if (viewModel.isBottomsheetVisible.value) {
                                viewModel.hideBottomsheet()
                            }
                        }
                    }
                    false -> {
                        binding.included.bottomsheet.getBehavior().setHidden()
                        backPressCallback?.remove()
                    }
                }
            }
        }


        binding.rvMusicSuggestion.adapter = adapter
        adapter.submitList(viewModel.getOffersList())


        binding.included.bottomsheet.getBehavior().isHideable = true
        binding.included.bottomsheet.getBehavior().setHidden()
        setBottomsheet()


        binding.edTo.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edTo.clearFocus()
                binding.hideKeyboard()
                viewModel.showBottomsheet()
            }
        }

        return binding.root
    }


    private fun setBottomsheet() {

        binding.run {
            included.bottomsheet.getBehavior().addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {

                        BottomSheetBehavior.STATE_HIDDEN -> {
                            backPressCallback?.remove()
                            viewModel.hideBottomsheet()
                        }

                        else -> {}
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })

            included.ivDifficultRoute.setOnClickListener {
                findNavController().navigate(R.id.plugFragment)
            }
            included.ivAnywhere.setOnClickListener {
                viewModel.setAnywhere(binding.included.etTo, getString(R.string.anywhere))
            }
            included.ivWeekends.setOnClickListener {
                findNavController().navigate(R.id.plugFragment)
            }
            included.ivHotTickets.setOnClickListener {
                findNavController().navigate(R.id.plugFragment)
            }

            included.item1.run {
                ivPlace.setImageResource(R.drawable.istanbul)
                tvTown.text = getString(R.string.instanbul)
            }
            included.item2.run {
                ivPlace.setImageResource(R.drawable.sochi)
                tvTown.text = getString(R.string.sochi)
            }
            included.item3.run {
                ivPlace.setImageResource(R.drawable.phuket)
                tvTown.text = getString(R.string.phuket)
            }
        }
    }


    private fun FrameLayout.getBehavior(): BottomSheetBehavior<View> {
        val params = layoutParams as CoordinatorLayout.LayoutParams
        return params.behavior as BottomSheetBehavior
    }

    private fun BottomSheetBehavior<View>.setHidden() {
        this.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun BottomSheetBehavior<View>.setExpanded() {
        this.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun FragmentMainScreenBinding.hideKeyboard() {
        val imm = this.root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}