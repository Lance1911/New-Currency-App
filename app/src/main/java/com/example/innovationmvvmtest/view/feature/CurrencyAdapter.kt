package com.example.innovationmvvmtest.view.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.innovationmvvmtest.R
import com.example.innovationmvvmtest.databinding.CardCurrencyItemBinding
import com.example.innovationmvvmtest.model.CurrencyItem
import java.util.Currency

class CurrencyAdapter : ListAdapter<CurrencyItem, CurrencyAdapter.CurrencyViewHolder>(CurrencyComparator()) {

    class CurrencyViewHolder(private val binding: CardCurrencyItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyItem: CurrencyItem) {
            binding.apply {
                //Glide.with(itemView).load(currencyItem.flagId).into(flag)
                currencyName.text = currencyItem.name
                currencyRate.text = currencyItem.rate.toString()

                val stringBuilder = StringBuilder(itemView.context.getString(R.string.symbol_text))
                val currency = Currency.getInstance(currencyItem.symbol)
                stringBuilder.append("\t\t")
                stringBuilder.append(currency.symbol)

                currencySymbol.text = stringBuilder.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CardCurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class CurrencyComparator : DiffUtil.ItemCallback<CurrencyItem>() {
        override fun areItemsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem) = oldItem == newItem
    }
}