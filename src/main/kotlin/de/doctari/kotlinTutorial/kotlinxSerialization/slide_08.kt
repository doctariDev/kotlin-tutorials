@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package de.doctari.kotlinTutorial.kotlinxSerialization

import de.doctari.kotlinTutorial.kotlinxSerialization.Location.Munich
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ serialization of nested serializable objects
 *   ᐅ built-in serializable types (primitives, collections, enums, ...)
 *
 * **************************************************************************************
 * ************************************************************************************** */

enum class Location {
  Munich, Berlin, Heidelberg, Timisoara
}

@Serializable
class Project08(
  val name: String,
  val location: Location,
  val productOwner: User08,
  val developers: Array<User08>,
)

@Serializable
class User08(val name: String, val isDeveloper: Boolean)

fun main() {
  val project1 = Project08(
    name = "doctari pro",
    location = Munich,
    productOwner = User08("Fee", isDeveloper = false),
    developers = arrayOf(
      User08("Fatih", isDeveloper = true),
      User08("Marcin", isDeveloper = true),
    ),
  )

  val json = Json.encodeToString(project1)
  println(json)
}
