package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Type Classes
 *
 *   Example 1
 *
 *   Problem description
 *
 * **************************************************************************************
 * ************************************************************************************** */

/*
 * All of following implementations are very similar.
 *
 * Can we get rid of the code duplication?
 * (trick question: what piece of code is actually duplicated?)
 *
 * Can we create an abstraction for it, such that we can use it in more use cases?
 */

fun List<Int>.sumI(): Int = fold(0) { acc, e -> acc + e }

fun List<String>.sumS(): String = fold("") { acc, e -> acc + e }

fun <T> List<List<T>>.sumL(): List<T> = fold(emptyList()) { acc, e -> acc + e }

fun main() {
  val intList = listOf(1, 2, 3, 4, 5)
  println(intList.sumI())

  val stringList = listOf("a", "b", "c", "d", "e")
  println(stringList.sumS())

  val listList = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))
  println(listList.sumL())
}
