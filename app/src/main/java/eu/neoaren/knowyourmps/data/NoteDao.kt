package eu.neoaren.knowyourmps.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(entry: Note)

  @Query("select * from notes_table where personNumber = :personNumber")
  fun getByPersonNumber(personNumber: Int): Flow<List<Note>>

}
