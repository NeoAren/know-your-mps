package eu.neoaren.knowyourmps

import android.annotation.SuppressLint
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
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import eu.neoaren.knowyourmps.data.MemberOfParliament
import eu.neoaren.knowyourmps.databinding.FragmentMemberDetailsBinding
import eu.neoaren.knowyourmps.viewmodels.MemberDetailsViewModel
import java.time.LocalDate

@AndroidEntryPoint
class MemberDetailsFragment : Fragment() {

  private val args: MemberDetailsFragmentArgs by navArgs()
  private val viewModel: MemberDetailsViewModel by viewModels()
  private lateinit var binding: FragmentMemberDetailsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_details, container, false)

    viewModel.member.observe(viewLifecycleOwner) { updateUI(it) }

    return binding.root
  }

  @SuppressLint("SetTextI18n")
  fun updateUI(member: MemberOfParliament) {
    val title = if (member.minister) "Minister" else "Member of Parliament"
    val age = (LocalDate.now().year - member.bornYear).toString()

    binding.title.text = title
    binding.name.text = "${member.first} ${member.last} ($age)"
    binding.party.text = member.party.uppercase()
    binding.constituency.text = member.constituency

    Picasso
      .get()
      .load("https://avoindata.eduskunta.fi/${member.picture}")
      .into(binding.imageView)
  }

}
