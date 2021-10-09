package eu.neoaren.knowyourmps

import android.app.Application

class App : Application() {

  val database by lazy { MPsDatabase.getInstance(applicationContext) }

}
