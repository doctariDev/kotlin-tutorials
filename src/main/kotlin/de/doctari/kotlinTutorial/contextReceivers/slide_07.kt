@file:Suppress("MemberVisibilityCanBePrivate", "unused", "UNUSED_VARIABLE")

package de.doctari.kotlinTutorial.contextReceivers

import java.io.Closeable
import java.io.File

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Multiple Receivers
 *
 *   Example 2 (Auto close scope)
 *
 * **************************************************************************************
 * ************************************************************************************** */


class AutoCloseScope {
  internal val closeable = mutableListOf<Closeable>()
}

context(AutoCloseScope)
fun File.open() = inputStream().also { closeable += it }

fun withAutoClose(block: context(AutoCloseScope) () -> Unit) {
  val scope = AutoCloseScope()
  try {
    block(scope)
  } finally {
    scope.closeable.forEach {
      try {
        it.close()
      } catch (e: Exception) {
        // ignore
      }
    }
  }
}

fun main() {
  withAutoClose {
    val input = File("input.txt").open()
    val config = File("config.txt").open()
    // ...use the streams

    // All files are automatically closed at the end of this block
  }
}
