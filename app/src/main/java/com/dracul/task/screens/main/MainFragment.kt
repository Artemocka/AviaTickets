package com.dracul.task.screens.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dracul.task.R
import com.dracul.task.databinding.FragmentMainBinding
import com.dracul.task.screens.main.recycler.OfferAdapter
import com.dracul.task.viewmodels.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private val adapter = OfferAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.offers.collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            viewModel.isBottomsheetVisible.collect {
                when (it) {
                    true -> {
                        binding.included.bottomsheet.getBehavior().setExpanded()
                    }

                    false -> {
                        binding.included.bottomsheet.getBehavior().setHidden()
                    }
                }
            }
        }
        binding.rvMusicSuggestion.adapter = adapter
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
            included.bottomsheet.getBehavior()
                .addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        when (newState) {
                            BottomSheetBehavior.STATE_HIDDEN -> {
                                viewModel.hideBottomsheet()
                            }

                            else -> {}
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                })
            included.etTo.onDone {
                val from = binding.included.etFrom.text.toString()
                val to = binding.included.etTo.text.toString()

                viewModel.navigateToTicketsOption(findNavController(), from, to)
            }
            included.rootDifficultRoute.setOnClickListener {
                viewModel.navigateToPlug(findNavController())
            }
            included.rootAnywhere.setOnClickListener {
                val anywhere = getString(R.string.anywhere)
                viewModel.setAnywhere(binding.included.etTo, anywhere)
                viewModel.navigateToTicketsOption(
                    findNavController(),
                    binding.included.etFrom.text.toString(),
                    anywhere
                )
            }
            included.rootWeekends.setOnClickListener {
                viewModel.navigateToPlug(findNavController())

            }
            included.rootHotTickets.setOnClickListener {
                viewModel.navigateToPlug(findNavController())
            }
            included.item1.run {
                val town = getString(R.string.instanbul)
                ivPlace.setImageResource(R.drawable.istanbul)
                tvTown.text = town
                root.setOnClickListener {
                    viewModel.setPlaceFromRecomendation(binding.included.etTo, town)
                    viewModel.navigateToTicketsOption(
                        findNavController(),
                        binding.included.etFrom.text.toString(),
                        town
                    )
                }
            }
            included.item2.run {
                val town = getString(R.string.sochi)
                ivPlace.setImageResource(R.drawable.sochi)
                tvTown.text = town
                root.setOnClickListener {
                    viewModel.setPlaceFromRecomendation(binding.included.etTo, town)
                    viewModel.navigateToTicketsOption(
                        findNavController(),
                        binding.included.etFrom.text.toString(),
                        town
                    )
                }
            }
            included.item3.run {
                val town = getString(R.string.phuket)
                ivPlace.setImageResource(R.drawable.phuket)
                tvTown.text = town
                root.setOnClickListener {
                    viewModel.setPlaceFromRecomendation(binding.included.etTo, town)
                    viewModel.navigateToTicketsOption(
                        findNavController(),
                        binding.included.etFrom.text.toString(),
                        town
                    )
                }
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

    private fun FragmentMainBinding.hideKeyboard() {
        val imm =
            this.root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun EditText.onDone(callback: () -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callback.invoke()
                return@setOnEditorActionListener true
            }
            false
        }
    }

}