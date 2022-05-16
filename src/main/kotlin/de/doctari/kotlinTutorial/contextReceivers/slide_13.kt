@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Type Classes
 *
 *   Example 1
 *
 *   Solution
 *
 * **************************************************************************************
 * ************************************************************************************** */


interface Monoid<T> {
  val unit: T
  operator fun T.plus(other: T): T
}

context(Monoid<T>)
fun <T> List<T>.sum(): T = fold(unit) { acc, e -> acc + e }






















object IntMonoid : Monoid<Int> {
  override val unit = 0
  override operator fun Int.plus(other: Int) = this + other
}

object StringMonoid : Monoid<String> {
  override val unit = ""
  override operator fun String.plus(other: String) = this + other
}

class ListMonoid<T> : Monoid<List<T>> {
  override val unit = emptyList<T>()
  override operator fun List<T>.plus(other: List<T>) = (this as Collection<T>) + other
}













fun List<Int>.sum() = with(IntMonoid) { sum() }
fun List<String>.sum() = with(StringMonoid) { sum() }
fun <T> List<List<T>>.sum() = with(ListMonoid<T>()) { sum() }










fun main() {
  val intList = listOf(1, 2, 3, 4, 5)
  println(intList.sum())


  val stringList = listOf("a", "b", "c", "d", "e")
  println(stringList.sum())


  val listList = listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
  println(listList.sum())
}
