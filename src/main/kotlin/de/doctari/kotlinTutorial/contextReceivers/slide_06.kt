@file:Suppress("MemberVisibilityCanBePrivate", "unused", "UNUSED_VARIABLE")

package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Multiple Receivers
 *
 *   Example 1 (Android dips utility function)
 *
 * **************************************************************************************
 * ************************************************************************************** */


/*
 * This is a contextual extension function, and as such has 2 receivers.
 */
context(Activity)
val Int.dp: Int get() = this * resources.displayMetrics.density



class MyActivity61 : Activity() {
  override fun onStart() {
    val bitmap = Bitmap.createBitmap(200.dp, 100.dp) // convenient invocation!
    //...
  }
}
