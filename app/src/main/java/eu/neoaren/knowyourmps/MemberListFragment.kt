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
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import eu.neoaren.knowyourmps.databinding.FragmentMemberListBinding
import eu.neoaren.knowyourmps.viewmodels.MemberListViewModel

@AndroidEntryPoint
class MemberListFragment : Fragment() {

  private val args: MemberListFragmentArgs by navArgs()
  private val viewModel: MemberListViewModel by viewModels()
  private lateinit var binding: FragmentMemberListBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)

    binding.toPartyList.setOnClickListener {
      val direction = MemberListFragmentDirections.actionMemberListFragmentToPartyListFragment()
      it.findNavController().navigate(direction)
    }

    binding.toMemberDetails.setOnClickListener {
      val personNumber = 1411 // test value
      val direction = MemberListFragmentDirections.actionMemberListFragmentToMemberDetailsFragment(personNumber)
      it.findNavController().navigate(direction)
    }

    viewModel.members.observe(viewLifecycleOwner) {
      Log.d("MEMBERS OF ${args.party.uppercase()}", it.toString())
    }

    return binding.root
  }

}
