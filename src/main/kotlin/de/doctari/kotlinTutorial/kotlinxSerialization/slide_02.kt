@file:Suppress("RedundantExplicitType")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ all primary constructor arguments must be properties
 *   ᐅ only properties that have a backing field are de/serialized
 *   ᐅ primary constructor is used in deserialization
 *   ᐅ property initializers are executed when deserializing
 *   ᐅ init blocks are executed when deserializing
 *   ᐅ @SerialName for properties
 *   ᐅ @Transient for properties
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Serializable
class Dog02(val name: String) {
  var age: Int = 0

  val isWhelp get() = age < 1
}

fun main() {
  val dog = Dog02("Spark").apply { age = 5 }
  println("The dog is a whelp? \n\t ${dog.isWhelp}")

  val json = Json.encodeToString(dog)
  println("The dog's json is \n\t $json")
}
