package eu.neoaren.knowyourmps.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.neoaren.knowyourmps.data.MemberOfParliamentRepository
import javax.inject.Inject

@HiltViewModel
class MemberListViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  memberOfParliamentRepository: MemberOfParliamentRepository,
) : ViewModel() {

  private val party = savedStateHandle.get<String>("party") ?: ""

  val members = memberOfParliamentRepository.getByParty(party).asLiveData()

}
