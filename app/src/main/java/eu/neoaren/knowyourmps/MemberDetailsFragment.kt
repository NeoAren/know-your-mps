package eu.neoaren.knowyourmps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.neoaren.knowyourmps.databinding.FragmentMemberDetailsBinding

@AndroidEntryPoint
class MemberDetailsFragment : Fragment() {

  private lateinit var binding: FragmentMemberDetailsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_details, container, false)

    binding.toMemberList.setOnClickListener {
      it.findNavController().navigate(R.id.action_memberDetailsFragment_to_memberListFragment)
    }

    return binding.root
  }

}
