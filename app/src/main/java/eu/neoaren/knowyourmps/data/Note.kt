package eu.neoaren.knowyourmps.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
  @PrimaryKey
  val id: String,
  val personNumber: Int,
  val content: String,
)
