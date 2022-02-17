@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ @Serializable makes unserializable properties serializable, too!
 *   ᐅ make properties of 3rd party types serializable
 *
 * **************************************************************************************
 * ************************************************************************************** */

@Serializable
class Cat07(val name: String, val age: Int)

@Serializable
class Family07(
  // does not compile without the annotation:
  @Serializable(with = DateAsLongSerializer::class)
  val startedOn: Date,

  // does not compile without the annotation:
  @Serializable(with = Dog06Serializer::class)
  val dog: Dog06? = null,

  val cat: Cat07? = null,
)

fun main() {
  val family = Family07(
    startedOn = Date(),
    dog = Dog06("Spike", 9),
    cat = Cat07("Tom", 6),
  )


  val json = Json.encodeToString(family)
  println("And the json is: \n\t $json")
}
