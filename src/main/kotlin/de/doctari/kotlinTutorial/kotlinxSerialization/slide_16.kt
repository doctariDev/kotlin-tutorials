@file:Suppress("DuplicatedCode", "RemoveExplicitTypeArguments")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.*
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ de/serialization of generic classes
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Serializable
data class Dog16(val name: String)

@Serializable
data class Box16<T>(val value: T)

fun main() {
  val boxedDog = Box16(Dog16("Spike"))

  val json1 = Json.encodeToString(boxedDog)
  val json2 = Json.encodeToString(serializer<Box16<Dog16>>(), boxedDog)
  val json3 = Json.encodeToString(Box16.serializer(Dog16.serializer()), boxedDog)

  println(json1)
  println(json2)
  println(json3)

  val boxedDog2: Box16<Dog16> = Json.decodeFromString(json1)
  println(boxedDog2)
}
