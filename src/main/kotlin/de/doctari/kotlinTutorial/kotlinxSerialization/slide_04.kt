@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ be careful to use the correct static type
 *
 *   "In general, Kotlin serialization is designed to work correctly only when the
 *   compile-time type used during serialization is the same one as the compile-time
 *   type used during deserialization."
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Serializable
class Dog04(val name: String) {
  fun greet() = println("Woof, I'm $name.")
}

@Serializable
class Cat04(val name: String) {
  fun greet() = println("Meeow, I'm $name.")
}

fun main() {
  val dog0 = Dog04("Spike")

  val dogJson = Json.encodeToString(dog0)
  println("Is this a dog or a cat? \n\t $dogJson")

  println("It can be both:")
  val dog: Dog04 = Json.decodeFromString(dogJson)
  val cat: Cat04 = Json.decodeFromString(dogJson)

  dog.greet()
  cat.greet()
}
