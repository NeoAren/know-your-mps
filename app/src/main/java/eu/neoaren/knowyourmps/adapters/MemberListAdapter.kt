package eu.neoaren.knowyourmps.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.neoaren.knowyourmps.MemberListFragmentDirections
import eu.neoaren.knowyourmps.data.MemberOfParliament
import eu.neoaren.knowyourmps.databinding.MemberListItemBinding

class MemberListAdapter : ListAdapter<MemberOfParliament, MemberListAdapter.MemberItemViewHolder>(MemberDiffCallback()) {

  class MemberItemViewHolder(
    private val binding: MemberListItemBinding,
  ) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(newMP: MemberOfParliament) {
      binding.memberName.text = "${newMP.first} ${newMP.last}"
      binding.memberName.setOnClickListener {
        val direction = MemberListFragmentDirections.actionMemberListFragmentToMemberDetailsFragment(newMP.personNumber)
        binding.root.findNavController().navigate(direction)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberItemViewHolder {
    return MemberItemViewHolder(
      MemberListItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: MemberItemViewHolder, position: Int) {
    val mp = getItem(position)
    holder.bind(mp);
  }

}

class MemberDiffCallback : DiffUtil.ItemCallback<MemberOfParliament>() {
  override fun areItemsTheSame(
    oldItem: MemberOfParliament,
    newItem: MemberOfParliament,
  ): Boolean {
    return oldItem == newItem
  }
  override fun areContentsTheSame(
    oldItem: MemberOfParliament,
    newItem: MemberOfParliament,
  ): Boolean {
    return oldItem == newItem
  }
}
