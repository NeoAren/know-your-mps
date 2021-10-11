package eu.neoaren.knowyourmps.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "mps_table")
data class MemberOfParliament(
  @PrimaryKey
  val personNumber: Int,
  val seatNumber: Int = 0,
  val last: String,
  val first: String,
  val party: String,
  val minister: Boolean = false,
  val picture: String = "",
  val twitter: String = "",
  val bornYear: Int = 0,
  val constituency: String = "",
) {

  val pictureUrl
    get() = "$BASE_PICTURE_URL$picture"

  companion object {
    private const val BASE_PICTURE_URL = "https://avoindata.eduskunta.fi/"
  }

}

data class MemberOfParliamentWithNotes(
  @Embedded
  val memberOfParliament: MemberOfParliament,
  @Relation(parentColumn = "personNumber", entityColumn = "personNumber")
  val notes: List<Note>,
)
