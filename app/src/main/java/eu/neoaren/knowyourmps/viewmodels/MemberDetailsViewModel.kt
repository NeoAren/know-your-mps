package eu.neoaren.knowyourmps.viewmodels

import androidx.lifecycle.*
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

  // ID of the currently viewed member of parliament
  private val personNumber = savedStateHandle.get<Int>("personNumber") ?: 0

  // The details of the currently viewed member of parliament
  val member = memberOfParliamentRepository.getByPersonNumber(personNumber).asLiveData()

  // List of all notes attached to the currently viewed member of parliament
  val notes = noteRepository.getByPersonNumber(personNumber).asLiveData()

  // Attach a new note to the currently viewed member of parliament
  fun attachNewNote(note: String) {
    viewModelScope.launch {
      noteRepository.insert(Note(randomUUID().toString(), personNumber, note))
    }
  }

}
