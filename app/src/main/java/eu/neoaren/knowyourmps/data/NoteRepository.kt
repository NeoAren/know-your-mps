package eu.neoaren.knowyourmps.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
  private val noteDao: NoteDao,
) {

  suspend fun insert(entry: Note) = noteDao.insert(entry)

  fun getByPersonNumber(personNumber: Int) = noteDao.getByPersonNumber(personNumber)

}
