package eu.neoaren.knowyourmps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.neoaren.knowyourmps.data.Note
import eu.neoaren.knowyourmps.databinding.NoteListItemBinding


class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteItemViewHolder>(NoteListDiffCallback()) {

  class NoteItemViewHolder(private val binding: NoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(note: Note) {
      binding.noteContent.text = note.content
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
    return NoteItemViewHolder(
      NoteListItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false,
      )
    )
  }

  override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
    val note = getItem(position)
    holder.bind(note)
  }

}

class NoteListDiffCallback : DiffUtil.ItemCallback<Note>() {
  override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
    return oldItem.id == newItem.id
  }
}
