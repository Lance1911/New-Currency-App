package com.example.innovationmvvmtest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innovationmvvmtest.databinding.FragmentCurrencyRatesBinding
import com.example.innovationmvvmtest.model.CurrencyItem
import com.example.innovationmvvmtest.resource.Resource
import com.example.innovationmvvmtest.view.feature.CurrencyAdapter
import com.example.innovationmvvmtest.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class CurrencyRatesFragment : Fragment() {

    private var _binding: FragmentCurrencyRatesBinding? = null
    private val viewModel: CurrencyViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCurrencyRatesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencyAdapter = CurrencyAdapter()
        binding.apply {
            recyclerView.apply {
                adapter = currencyAdapter
                layoutManager = LinearLayoutManager(context)
                viewModel.currencies.observe(viewLifecycleOwner) { result ->
                    currencyAdapter.submitList(result.data)
                    progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                    loadingErrorMessage.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                    loadingErrorMessage.text = result.error?.localizedMessage
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}