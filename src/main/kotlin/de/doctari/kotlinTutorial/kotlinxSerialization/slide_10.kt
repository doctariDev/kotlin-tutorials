@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ native support for nullable properties
 *   ᐅ especially when property's default value is null
 *
 * **************************************************************************************
 * ************************************************************************************** */

@Serializable
class Project10(
  val name: String,
  val owner: String? = null,
  val department: String? = null,
  val estimatedMonths: Int? = null,
  val location: String? = null,
)

fun main() {
  val project1 = Project10("doctari pro", location = "Munich")

  val json = Json.encodeToString(project1)
  println("""After encoding, all null properties are not present in json 
      $json
  """)

  val project2: Project10 = Json.decodeFromString(json)
  println("""After decoding, missing nullable properties are still null:
      name=${project2.name} 
      location=${project2.location} 
      owner=${project2.owner}
      department=${project2.department}
      estimatedMonths=${project2.estimatedMonths}
  """)
}
