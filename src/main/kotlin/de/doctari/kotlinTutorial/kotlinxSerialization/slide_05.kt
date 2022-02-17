@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ de/serialization does not use reflection
 *   ᐅ compiler plugin generates serialization code
 *   ᐅ serializer for class A is stored in companion A.serializer
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Serializable
data class Dog05(val name: String)

fun main() {
  val dog0 = Dog05("Spike")
  val json = Json.encodeToString(dog0)

  // equivalent ways to provide the static type:
  // 1. inferred
  // 2. explicit type parameter
  // 3. provide serializer manually
  // 4. alternative to 3

  val dog1: Dog05 = Json.decodeFromString(json)
  val dog2 = Json.decodeFromString<Dog05>(json)
  val dog3 = Json.decodeFromString(Dog05.serializer(), json)
  val dog4 = Json.decodeFromString(serializer<Dog05>(), json)

  arrayOf(dog1, dog2, dog3, dog4).forEach(::println)

  // The first 2 examples are syntactic sugar for the third example. No compiler magic though
  // for the first 2 examples. They are just inline functions. The third example shows what the
  // compiler plugin has provided us: a serializer() function added to the Dog05 companion object.

  // what does the serializer() function return? see next slide
}
