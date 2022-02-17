@file:Suppress("DuplicatedCode")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ static serialization of polymorphic properties
 *
 * **************************************************************************************
 * ************************************************************************************** */

@Serializable
data class Family13(
  val firstPet: Animal13,
  val secondPet: Animal13
)

@Serializable
open class Animal13 {
  var name: String? = null
  override fun toString() = "Animal13(name=$name)"
}

@Serializable
class Dog13 : Animal13() {
  var dogBreed: String? = null
  override fun toString() = "Dog13(name=$name, dogBreed=$dogBreed)"
}

@Serializable
class Cat13 : Animal13() {
  var catBreed: String? = null
  override fun toString() = "Cat13(name=$name, catBreed=$catBreed)"
}

fun main() {
  val family1 = Family13(
    firstPet = Dog13().apply {
      name = "Spike"
      dogBreed = "German Shepherd"
    },
    secondPet = Cat13().apply {
      name = "Tom"
      catBreed = "Persian"
    }
  )
  println("""Our family is:
      $family1
  """)

  val json = Json.encodeToString(family1)
  println("""Our family's json is:
      $json
  """)

  val family2: Family13 = Json.decodeFromString(json)
  println("""Our deserialized Family is:
      $family2
  """)
}
