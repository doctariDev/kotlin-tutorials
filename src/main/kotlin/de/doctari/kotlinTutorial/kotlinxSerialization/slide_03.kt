@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ deserialization is type safe!
 *   ᐅ instance of the actual type is created
 *   ᐅ type is decided statically
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Serializable
class Dog03(val name: String) {
  fun greet() = println("Woof, I'm $name.")
}

@Serializable
class Cat03(val name: String) {
  fun greet() = println("Meeow, I'm $name.")
}

fun main() {
  val dog = Dog03("Spike")
  val cat = Cat03("Tom")

  val dJson = Json.encodeToString(dog)
  val cJson = Json.encodeToString(cat)

  println("The dog's json is \n\t $dJson")
  println("The cat's json is \n\t $cJson")

  val dog2: Dog03 = Json.decodeFromString(dJson)
  val cat2: Cat03 = Json.decodeFromString(cJson)

  dog2.greet()
  cat2.greet()

  // all of these will throw an error because of invalid json data:
  //
  //   val noDog1: Dog03 = Json.decodeFromString("""{}""")
  //   val noDog2: Dog03 = Json.decodeFromString("""{"name": true}""")
  //   val noDog3: Dog03 = Json.decodeFromString("""{"mame": "Spike"}""")
}
