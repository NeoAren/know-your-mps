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
import eu.neoaren.knowyourmps.adapters.MemberListAdapter
import eu.neoaren.knowyourmps.databinding.FragmentMemberListBinding
import eu.neoaren.knowyourmps.viewmodels.MemberListViewModel

@AndroidEntryPoint
class MemberListFragment : Fragment() {

  private val viewModel: MemberListViewModel by viewModels()
  private lateinit var binding: FragmentMemberListBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)

    val adapter = MemberListAdapter()
    binding.memberList.adapter = adapter
    binding.memberList.setHasFixedSize(true)
    binding.memberList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    binding.memberList.layoutManager = LinearLayoutManager(context)

    viewModel.members.observe(viewLifecycleOwner) { adapter.submitList(it) }

    return binding.root
  }

}
