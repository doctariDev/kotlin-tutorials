@file:Suppress("DuplicatedCode", "EXPERIMENTAL_API_USAGE")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonClassDiscriminator
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ polymorphic serialization of polymorphic properties
 *   ᐅ @SerialName for classes
 *   ᐅ @JsonClassDiscriminator for classes
 *
 * **************************************************************************************
 * ************************************************************************************** */

@Serializable
data class Family14(
  @Polymorphic
  val firstPet: Animal14,

  @Polymorphic
  val secondPet: Animal14
)

@Serializable @JsonClassDiscriminator("animalType")
open class Animal14 {
  var name: String? = null
  override fun toString() = "Animal14(name=$name)"
}

@Serializable @SerialName("dog")
class Dog14 : Animal14() {
  var dogBreed: String? = null
  override fun toString() = "Dog14(name=$name, dogBreed=$dogBreed)"
}

@Serializable @SerialName("cat")
class Cat14 : Animal14() {
  var catBreed: String? = null
  override fun toString() = "Cat14(name=$name, catBreed=$catBreed)"
}

fun main() {
  val family1 = Family14(
    firstPet = Dog14().apply {
      name = "Spike"
      dogBreed = "German Shepherd"
    },
    secondPet = Cat14().apply {
      name = "Tom"
      catBreed = "Persian"
    }
  )
  println("""Our family is:
      $family1
  """)

  val json = Json14.encodeToString(family1)
  println("""Our family's json is:
      $json
  """)

  val family2: Family14 = Json14.decodeFromString(json)
  println("""Our deserialized Family is:
      $family2
  """)
}











val Json14 = Json {
  serializersModule = SerializersModule {
    polymorphic(Animal14::class) {
      subclass(Dog14::class)
      subclass(Cat14::class)
    }
  }
}
