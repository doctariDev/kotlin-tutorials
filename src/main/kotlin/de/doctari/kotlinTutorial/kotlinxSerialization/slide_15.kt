@file:Suppress("DuplicatedCode")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.*
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ polymorphic serialization of sealed classes
 *
 * **************************************************************************************
 * ************************************************************************************** */

@Serializable
data class Family15(
  val firstPet: Animal15,
  val secondPet: Animal15
)

@Serializable
sealed class Animal15 {
  var name: String? = null
  override fun toString() = "Animal15(name=$name)"
}

@Serializable @SerialName("dog")
class Dog15 : Animal15() {
  var dogBreed: String? = null
  override fun toString() = "Dog15(name=$name, dogBreed=$dogBreed)"
}

@Serializable @SerialName("cat")
class Cat15 : Animal15() {
  var catBreed: String? = null
  override fun toString() = "Cat15(name=$name, catBreed=$catBreed)"
}

fun main() {
  val family1 = Family15(
    firstPet = Dog15().apply {
      name = "Spike"
      dogBreed = "German Shepherd"
    },
    secondPet = Cat15().apply {
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

  val family2: Family15 = Json.decodeFromString(json)
  println("""Our deserialized Family is:
      $family2
  """)
}
