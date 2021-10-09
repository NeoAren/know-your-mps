package eu.neoaren.knowyourmps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eu.neoaren.knowyourmps.adapters.PartyItemAdapter
import eu.neoaren.knowyourmps.databinding.FragmentPartyListBinding
import eu.neoaren.knowyourmps.viewmodels.PartyListViewModel

@AndroidEntryPoint
class PartyListFragment : Fragment() {

  private val viewModel: PartyListViewModel by viewModels()
  private lateinit var binding: FragmentPartyListBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)

    val adapter = PartyItemAdapter()
    binding.partyList.adapter = adapter
    binding.partyList.setHasFixedSize(true)
    binding.partyList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    binding.partyList.layoutManager = LinearLayoutManager(context)

    viewModel.parties.observe(viewLifecycleOwner) { adapter.submitList(it) }

    return binding.root
  }

}
