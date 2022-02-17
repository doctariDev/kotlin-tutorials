package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ static serialization of polymorphic class hierarchies
 *
 * **************************************************************************************
 * ************************************************************************************** */

@Serializable
open class Animal12 {
  var name: String? = null

  open fun greet() = println("Hi, I'm $name")
}

@Serializable
class Dog12 : Animal12() {
  var dogBreed: String? = null

  override fun greet() = println("Woof, I'm $name. My breed is $dogBreed")
}

fun main() {
  val animal1: Dog12 = Dog12().apply {
    name = "Spike"
    dogBreed = "German Shepherd"
  }

  val animal2: Animal12 = animal1

  val json1 = Json.encodeToString(animal1)
  val json2 = Json.encodeToString(animal2)

  println("1st animal's json is \n\t $json1")
  println("2nd animal's json is \n\t $json2")

  val animal1Deserialized: Dog12 = Json.decodeFromString(json1)
  val animal2Deserialized: Dog12 = Json.decodeFromString(json2)

  animal1Deserialized.greet()
  animal2Deserialized.greet()
}
