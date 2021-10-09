package eu.neoaren.knowyourmps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.neoaren.knowyourmps.databinding.FragmentPartyListBinding
import eu.neoaren.knowyourmps.viewmodels.PartyListViewModel

@AndroidEntryPoint
class PartyListFragment : Fragment() {

  private val partyListViewModel: PartyListViewModel by viewModels()
  private lateinit var binding: FragmentPartyListBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)

    binding.toMemberList.setOnClickListener {
      val party = "kesk" // test value
      val direction = PartyListFragmentDirections.actionPartyListFragmentToMemberListFragment(party)
      it.findNavController().navigate(direction)
    }

    partyListViewModel.parties.observe(viewLifecycleOwner) {
      Log.d("PARTIES", it.toString())
    }

    return binding.root
  }

}
