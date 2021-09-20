package eu.neoaren.knowyourmps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import eu.neoaren.knowyourmps.databinding.FragmentPartyListBinding

class PartyListFragment : Fragment() {

  private lateinit var binding: FragmentPartyListBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)

    binding.toMemberList.setOnClickListener {
      it.findNavController().navigate(R.id.action_partyListFragment_to_memberListFragment)
    }

    return binding.root
  }

}
