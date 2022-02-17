@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package de.doctari.kotlinTutorial.kotlinxSerialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.Json
import java.util.*





/* **************************************************************************************
 * **************************************************************************************
 *
 *   ᐅ We can write a serializer manually
 *   ᐅ This is what the compiler plugin generates for @Serializable classes
 *   ᐅ you can use this to make 3rd party types serializable
 *
 * **************************************************************************************
 * ************************************************************************************** */

class Dog06(val name: String, val age: Int)

object Dog06Serializer : KSerializer<Dog06> {
  override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
    "de.doctari.kotlinTutorial.kotlinxSerialization.Dog06"
  ) {
    element("dogName", String.serializer().descriptor)
    element("dogAge", String.serializer().descriptor)
  }

  override fun serialize(encoder: Encoder, value: Dog06) = encoder.encodeStructure(descriptor) {
    encodeStringElement(descriptor, 0, value.name)
    encodeIntElement(descriptor, 1, value.age)
  }

  override fun deserialize(decoder: Decoder): Dog06 = TODO()
}

object DateAsLongSerializer : KSerializer<Date> {
  override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.LONG)
  override fun serialize(encoder: Encoder, value: Date) = encoder.encodeLong(value.time)
  override fun deserialize(decoder: Decoder): Date = Date(decoder.decodeLong())
}

fun main() {
  val dog = Dog06("Spike", 9)
  val date = Date()

  // throws an error, because Dog06/Date is not @Serializable
  //
  //    val noJson = Json.encodeToString(dog)
  //    val noJson = Json.encodeToString(date)

  val dogJson = Json.encodeToString(Dog06Serializer, dog)
  val dateJson = Json.encodeToString(DateAsLongSerializer, date)

  println("The dogJson is: \n\t $dogJson")
  println("The dateJson is: \n\t $dateJson")
}
