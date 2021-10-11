package eu.neoaren.knowyourmps.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import eu.neoaren.knowyourmps.MemberListFragmentDirections
import eu.neoaren.knowyourmps.data.MemberOfParliamentWithNotes
import eu.neoaren.knowyourmps.databinding.MemberListItemBinding

class MemberListAdapter : ListAdapter<MemberOfParliamentWithNotes, MemberListAdapter.MemberItemViewHolder>(MemberListDiffCallback()) {

  class MemberItemViewHolder(private val binding: MemberListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    // Update the view holder with the data of a new member of parliament with notes
    @SuppressLint("SetTextI18n")
    fun bind(data: MemberOfParliamentWithNotes) {
      val member = data.memberOfParliament
      val notes = data.notes
      binding.memberName.text = "${member.first} ${member.last}"
      binding.memberDescription.text = "${member.constituency}, (${notes.size} notes)"
      binding.memberContainer.setOnClickListener {
        val direction = MemberListFragmentDirections.actionMemberListFragmentToMemberDetailsFragment(member.personNumber)
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

class MemberListDiffCallback : DiffUtil.ItemCallback<MemberOfParliamentWithNotes>() {
  override fun areItemsTheSame(
    oldItem: MemberOfParliamentWithNotes,
    newItem: MemberOfParliamentWithNotes,
  ): Boolean {
    return oldItem.memberOfParliament == newItem.memberOfParliament
  }
  override fun areContentsTheSame(
    oldItem: MemberOfParliamentWithNotes,
    newItem: MemberOfParliamentWithNotes,
  ): Boolean {
    return oldItem.memberOfParliament == newItem.memberOfParliament
  }
}
