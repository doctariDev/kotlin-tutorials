@file:Suppress("DuplicatedCode", "RemoveExplicitTypeArguments")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.*
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ de/serialization of properties of generic types
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Serializable
data class Dog17(val name: String)

@Serializable
data class Cat17(val name: String)

@Serializable
data class Box17<T>(val value: T)

@Serializable
data class TwoBoxes17(val box1: Box17<Dog17>, val box2: Box17<Cat17>)

fun main() {
  val boxes = TwoBoxes17(
    Box17(Dog17("Spike")),
    Box17(Cat17("Tom")),
  )

  val json1 = Json.encodeToString(boxes)
  val json2 = Json.encodeToString(TwoBoxes17.serializer(), boxes)

  println(json1)
  println(json2)
}
