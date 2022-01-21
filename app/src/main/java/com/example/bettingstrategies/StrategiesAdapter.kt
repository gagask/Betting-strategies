package com.example.bettingstrategies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bettingstrategies.database.Strategy
import com.example.bettingstrategies.databinding.StrategyItemBinding


class StrategiesAdapter(private val onItemClickListener: OnClickListener,
                        private val onItemStarClickListener: OnClickListener
) :
    ListAdapter<Strategy, StrategiesAdapter.StrategyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StrategyViewHolder {
        val binding = StrategyItemBinding.inflate(LayoutInflater.from(parent.context))
        binding.layout.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )

        return StrategyViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<Strategy>?) {
        this.notifyDataSetChanged()
        super.submitList(list)
    }

    override fun onBindViewHolder(holder: StrategyViewHolder, position: Int) {
        val strategy = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(strategy)
        }
        holder.bind(strategy, onItemStarClickListener)
    }

    class OnClickListener(val clickListener: (strategy: Strategy) -> Unit) {
        fun onClick(strategy: Strategy) = clickListener(strategy)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Strategy>() {
        override fun areItemsTheSame(oldItem: Strategy, newItem: Strategy): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Strategy, newItem: Strategy): Boolean {
            return oldItem == oldItem
        }
    }

    class StrategyViewHolder(private var binding: StrategyItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(strategy: Strategy, onItemStarClickListener: OnClickListener) {
            binding.strategy = strategy
            binding.followButton.setOnClickListener{
                onItemStarClickListener.onClick(strategy)
            }
            binding.executePendingBindings()
        }
    }
}