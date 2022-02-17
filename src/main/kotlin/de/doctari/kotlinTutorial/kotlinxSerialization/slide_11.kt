package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ configuring deserialization behavior
 *
 * **************************************************************************************
 * ************************************************************************************** */

private val Json11 = Json {
  prettyPrint = true
  //  isLenient = true
  //  ignoreUnknownKeys = true
  //  coerceInputValues = true
  //  encodeDefaults = true
  //  explicitNulls = true
}


@Serializable
class Project11(
  val name: String,
  val productOwner: User11,
  val developers: Array<User11>
)

@Serializable
class User11(val name: String, val isDeveloper: Boolean)

fun main() {
  val project1 = Project11(
    name = "doctari pro",
    productOwner = User11("Fee", isDeveloper = false),
    developers = arrayOf(
      User11("Fatih", isDeveloper = true),
      User11("Marcin", isDeveloper = true),
    ),
  )

  val json = Json11.encodeToString(project1)
  println(json)
}
