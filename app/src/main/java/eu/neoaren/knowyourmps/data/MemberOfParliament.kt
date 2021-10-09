package eu.neoaren.knowyourmps.data

import androidx.room.Entity
import androidx.room.PrimaryKey

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
)
