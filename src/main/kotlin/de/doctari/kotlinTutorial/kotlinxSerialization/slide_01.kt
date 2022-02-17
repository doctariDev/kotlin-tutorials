package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ @Serializable makes custom (data) classes serializable
 *   ᐅ custom enum/sealed by default
 *   ᐅ built-in primitive types, String and Collections types by default
 *   ᐅ use serialization API for de/serializing: encodeToString / encodeToStream ...
 *   ᐅ choose between formats JSON, CBOR, protocol buffers, ...
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Serializable
data class Dog(val name: String, val age: Int)

fun main() {
  val dog1 = Dog("Spark", 4)
  val json = Json.encodeToString(dog1)
  println("The dog is \n\t $dog1")
  println("Its json is \n\t $json")

  val dog2: Dog = Json.decodeFromString(json)
  println("The deserialized dog is \n\t $dog2")
}
