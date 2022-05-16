@file:Suppress("unused", "MemberVisibilityCanBePrivate", "CanBeParameter")

package de.doctari.kotlinTutorial.contextReceivers

import java.time.LocalDate
import java.time.temporal.ChronoUnit


/* **************************************************************************************
 * **************************************************************************************
 *
 *   Reminder:
 *
 *   ᐅ classes in Kotlin can have property / function members
 *   ᐅ members can only be invoked by providing a "dispatch receiver"
 *   ᐅ the dispatch receiver can be provided explicitly (qualified) or implicitly
 *   ᐅ "this" within a member body refers to the dispatch receiver
 *
 * **************************************************************************************
 * ************************************************************************************** */


/*
 * This class has 5 members:
 * - val name
 * - val birthday
 * - val age
 * - fun greet()
 * - fun myNameIs()
 */
class Person01(val name: String, val birthday: LocalDate) {
  val age = birthday.until(LocalDate.now(), ChronoUnit.YEARS)

  fun greet(): String {
    // provides dispatch receiver explicitly as "this":
    return "Hello, I'm ${this.name}"
  }

  fun myNameIs(): String {
    // provides dispatch receiver implicitly as "this":
    return "My name is $name"
  }
}

fun main() {
  val person = Person01("John", LocalDate.of(1980, 1, 1))

  // provides dispatch receiver explicitly as "person":
  println(person.name)
  println(person.age)
  println(person.greet())

  with(person) {
    // provides dispatch receiver implicitly:
    println(name)
    println(person.age)
    println(greet())
  }
}




