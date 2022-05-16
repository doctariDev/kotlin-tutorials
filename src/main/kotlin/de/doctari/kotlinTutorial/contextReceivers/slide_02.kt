@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package de.doctari.kotlinTutorial.contextReceivers

import java.time.LocalDate
import java.time.temporal.ChronoUnit


/* **************************************************************************************
 * **************************************************************************************
 *
 *   Reminder:
 *
 *   ᐅ classes can also have "extension" functions / properties
 *   ᐅ extensions can only be invoked by providing an "extension receiver"
 *   ᐅ the extension receiver can be provided explicitly (qualified) or implicitly
 *   ᐅ "this" within an extension body refers to the extension receiver
 *
 * **************************************************************************************
 * ************************************************************************************** */


/*
 * This class has 2 members:
 * - val name
 * - val birthday
 */
class Person02(val name: String, val birthday: LocalDate)

/*
 * And 3 extensions:
 * - val age
 * - fun greet()
 * - fun myNameIs()
 */

val Person02.age: Long get() = birthday.until(LocalDate.now(), ChronoUnit.YEARS)

fun Person02.greet(): String {
  // is "this" in next line an extension receiver or a dispatch receiver?
  // !!!!!
  return "Hello, I'm ${this.name}"
}

fun Person02.myNameIs(): String {
  return "My name is $name"
}

fun main() {
  val person = Person02("John", LocalDate.of(1980, 1, 1))

  // provides extension receiver explicitly as "person":
  println(person.name)
  println(person.age)
  println(person.greet())

  with(person) {
    // provides extension receiver implicitly:
    println(name)
    println(age)
    println(greet())
  }
}




