@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Reminder:
 *
 *   ᐅ classes can also have "member extension" functions / properties
 *   ᐅ member extensions have BOTH a dispatch receiver AND an extension receiver
 *   ᐅ the extension receiver can be provided explicitly (qualified) or implicitly
 *   ᐅ the dispatch receiver can only be provided implicitly (no qualified syntax)
 *   ᐅ "this" within a member extension refers to the extension receiver
 *   ᐅ "this@Type" within a member extension refers to the dispatch receiver
 *
 * **************************************************************************************
 * ************************************************************************************** */


val clubs = listOf(Club("Chelsea"), Club("Fc Barcelona"))

class Club(val name: String) {
  val clubMembers = mutableSetOf<Person03>()
}

data class Person03(val name: String) {
  fun Club.join() {
    // "this" within a member extension refers to the extension receiver (Club)
    // "this@Person03" refers to the dispatch receiver (Person03)
    this.clubMembers.add(this@Person03)
  }

  fun joinFirstClub() {
    // the extension receiver is provided explicitly as "clubs[0]"
    // the dispatch receiver is provided implicitly
    clubs[0].join()
  }
}

fun main() {
  val person = Person03("John")
  val club = clubs[0]

  // how to call the member extension join() here?

  with (person) {
    // the extension receiver is provided explicitly as "clubs[0]"
    // the dispatch receiver is provided implicitly
    club.join()
  }

  with (person) {
    with (club) {
      // the extension receiver is provided implicitly
      // the dispatch receiver is provided implicitly
      join()
    }
  }
  // there is no way to provide the dispatch receiver of join() explicitly!
}

/*
 * this example seems very artificial.
 * what are member extension functions good for?
 * => see next slide
 */
