package eu.neoaren.knowyourmps

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import eu.neoaren.knowyourmps.adapters.NoteListAdapter
import eu.neoaren.knowyourmps.data.MemberOfParliament
import eu.neoaren.knowyourmps.databinding.FragmentMemberDetailsBinding
import eu.neoaren.knowyourmps.viewmodels.MemberDetailsViewModel
import java.time.LocalDate

@AndroidEntryPoint
class MemberDetailsFragment : Fragment() {

  private val viewModel: MemberDetailsViewModel by viewModels()
  private lateinit var binding: FragmentMemberDetailsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_details, container, false)

    val adapter = NoteListAdapter()
    binding.noteList.adapter = adapter
    binding.noteList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    binding.noteList.layoutManager = LinearLayoutManager(context)

    viewModel.member.observe(viewLifecycleOwner) {
      updateUI(it.memberOfParliament)
      adapter.submitList(it.notes)
    }

    return binding.root
  }

  @SuppressLint("SetTextI18n")
  fun updateUI(mp: MemberOfParliament) {
    val title = if (mp.minister) "Minister" else "Member of Parliament"
    val age = (LocalDate.now().year - mp.bornYear).toString()

    binding.title.text = title
    binding.name.text = "${mp.first} ${mp.last} ($age)"
    binding.party.text = mp.party.uppercase()
    binding.constituency.text = mp.constituency

    Picasso.get().load(mp.pictureUrl).into(binding.picture)

    binding.save.setOnClickListener { addNoteTo(mp) }

  }

  private fun addNoteTo(mp: MemberOfParliament) {
    val note = binding.note.text.toString()
    binding.note.text.clear()
    binding.note.clearFocus()
    viewModel.addNoteToMP(mp, note)
    hideSoftKeyboard()
  }

  private fun hideSoftKeyboard() {
    val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.rootView?.windowToken, 0)
  }

}
