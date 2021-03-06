package eu.neoaren.knowyourmps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.neoaren.knowyourmps.PartyListFragmentDirections
import eu.neoaren.knowyourmps.databinding.PartyListItemBinding

class PartyListAdapter : ListAdapter<String, PartyListAdapter.PartyItemViewHolder>(DiffCallback()) {

  class PartyItemViewHolder(private val binding: PartyListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    // Update the view holder with the data of a new party
    fun bind(party: String) {
      binding.partyName.text = party.uppercase()
      binding.partyName.setOnClickListener {
        val direction = PartyListFragmentDirections.actionPartyListFragmentToMemberListFragment(party)
        binding.root.findNavController().navigate(direction)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyItemViewHolder {
    return PartyItemViewHolder(
      PartyListItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false,
      )
    )
  }

  override fun onBindViewHolder(holder: PartyItemViewHolder, position: Int) {
    val party = getItem(position)
    holder.bind(party)
  }

}

class DiffCallback : DiffUtil.ItemCallback<String>() {
  override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
    return oldItem == newItem
  }
  override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
    return oldItem == newItem
  }
}
