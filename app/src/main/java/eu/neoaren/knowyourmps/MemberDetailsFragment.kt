package eu.neoaren.knowyourmps

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import eu.neoaren.knowyourmps.databinding.FragmentMemberDetailsBinding
import eu.neoaren.knowyourmps.viewmodels.MemberDetailsViewModel

@AndroidEntryPoint
class MemberDetailsFragment : Fragment() {

  private val args: MemberDetailsFragmentArgs by navArgs()
  private val viewModel: MemberDetailsViewModel by viewModels()
  private lateinit var binding: FragmentMemberDetailsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_details, container, false)

    binding.toMemberList.setOnClickListener {
      it.findNavController().navigateUp()
    }

    viewModel.member.observe(viewLifecycleOwner) {
      Log.d("MEMBER ${args.personNumber}", it.toString())
    }

    return binding.root
  }

}
