package eu.neoaren.knowyourmps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.neoaren.knowyourmps.databinding.FragmentMemberListBinding

@AndroidEntryPoint
class MemberListFragment : Fragment() {

  private lateinit var binding: FragmentMemberListBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)

    binding.toPartyList.setOnClickListener {
      it.findNavController().navigate(R.id.action_memberListFragment_to_partyListFragment)
    }

    binding.toMemberDetails.setOnClickListener {
      it.findNavController().navigate(R.id.action_memberListFragment_to_memberDetailsFragment)
    }

    return binding.root
  }

}
