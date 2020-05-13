package io.github.mattpvaughn.synergies.features.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.mattpvaughn.synergies.data.local.model.Synergy
import io.github.mattpvaughn.synergies.databinding.SynergyListItemBinding


class SynergyAdapter(private val modelClick: LibraryFragment.ModelClick) :
    ListAdapter<Synergy, RecyclerView.ViewHolder>(GradientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: SynergyListItemBinding=
            SynergyListItemBinding.inflate(layoutInflater, parent, false)
        return GradientViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GradientViewHolder).bind(getItem(position), modelClick)
    }

    class GradientViewHolder constructor(private val synergyListItemBinding: SynergyListItemBinding) :
        RecyclerView.ViewHolder(synergyListItemBinding.root) {

        fun bind(synergy: Synergy, modelClick: LibraryFragment.ModelClick) {
            synergyListItemBinding.bind(synergy)
            synergyListItemBinding.root.setOnClickListener { modelClick.onClick(synergy) }
        }

        companion object {
            fun from(viewGroup: ViewGroup): GradientViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val binding = SynergyListItemBinding.inflate(inflater, viewGroup, false)
                return GradientViewHolder(binding)
            }
        }
    }

}

class GradientDiffCallback : DiffUtil.ItemCallback<Synergy>() {
    override fun areItemsTheSame(oldItem: Synergy, newItem: Synergy): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Synergy, newItem: Synergy): Boolean {
        return oldItem == newItem
    }
}
