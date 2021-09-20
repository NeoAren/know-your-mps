package eu.neoaren.knowyourmps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import eu.neoaren.knowyourmps.databinding.FragmentMemberDetailsBinding
import java.time.LocalDate

class MemberDetailsFragment : Fragment() {

  private lateinit var binding: FragmentMemberDetailsBinding

  @SuppressLint("SetTextI18n")
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_details, container, false)

    // Render random MP once
    renderRandomParliamentMember()

    // Render random MP on button press
    binding.random.setOnClickListener {
      renderRandomParliamentMember()
    }

    binding.toMemberList.setOnClickListener {
      it.findNavController().navigate(R.id.action_memberDetailsFragment_to_memberListFragment)
    }

    return binding.root
  }

  @SuppressLint("SetTextI18n")
  private fun renderRandomParliamentMember() {
    val member = ParliamentMembersData.members.shuffled().take(1)[0]

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
