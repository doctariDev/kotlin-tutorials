@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ default values are not encoded (by default)
 *   ᐅ properties with default values are optional when decoding
 *
 * **************************************************************************************
 * ************************************************************************************** */

@Serializable
class Dog09(val name: String, val age: Int = 5)

fun main() {
  val dog1 = Dog09("Spark")
  println("Our dog's age is ${dog1.age}")

  val json = Json.encodeToString(dog1)
  println("""After encoding, the age is not present in json
      $json
  """)

  val dog2: Dog09 = Json.decodeFromString(json)
  println("""After decoding from a json with missing age property, the default value will be used:
      ${dog2.age}
  """)

  // This will throw an error because name does not have a default:
  //   val dog3: Dog09 = Json.decodeFromString("""{"age": 10}""")
}
