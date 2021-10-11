package eu.neoaren.knowyourmps.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.neoaren.knowyourmps.data.MemberOfParliamentRepository
import javax.inject.Inject

@HiltViewModel
class PartyListViewModel @Inject constructor(
  memberOfParliamentRepository: MemberOfParliamentRepository,
) : ViewModel() {

  // List of all parties
  val parties = memberOfParliamentRepository.getParties().asLiveData()

}
