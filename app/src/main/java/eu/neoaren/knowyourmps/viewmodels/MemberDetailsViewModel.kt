package eu.neoaren.knowyourmps.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.neoaren.knowyourmps.data.MemberOfParliamentRepository
import javax.inject.Inject

@HiltViewModel
class MemberDetailsViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  memberOfParliamentRepository: MemberOfParliamentRepository,
) : ViewModel() {

  private val personNumber = savedStateHandle.get<Int>("personNumber") ?: 0

  val member = memberOfParliamentRepository.getByPersonNumber(personNumber).asLiveData()

}
