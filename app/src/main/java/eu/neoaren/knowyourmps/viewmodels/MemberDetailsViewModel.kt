package eu.neoaren.knowyourmps.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.neoaren.knowyourmps.data.MemberOfParliament
import eu.neoaren.knowyourmps.data.MemberOfParliamentRepository
import eu.neoaren.knowyourmps.data.Note
import eu.neoaren.knowyourmps.data.NoteRepository
import kotlinx.coroutines.launch
import java.util.UUID.randomUUID
import javax.inject.Inject

@HiltViewModel
class MemberDetailsViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  memberOfParliamentRepository: MemberOfParliamentRepository,
  private val noteRepository: NoteRepository,
) : ViewModel() {

  private val personNumber = savedStateHandle.get<Int>("personNumber") ?: 0

  val member = memberOfParliamentRepository.getByPersonNumber(personNumber).asLiveData()

  fun addNoteToMP(mp: MemberOfParliament, note: String) {
    viewModelScope.launch {
      noteRepository.insert(Note(randomUUID().toString(), mp.personNumber, note))
    }
  }

}
