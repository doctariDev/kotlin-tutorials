@file:Suppress("MemberVisibilityCanBePrivate", "unused", "UNUSED_VARIABLE")

package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case for member extension:
 *   ᐅ utility function to calculate device independent pixels (dips)
 *
 * **************************************************************************************
 * ************************************************************************************** */

class DisplayMetrics {
  val density: Int = 300
}
class Resources {
  val displayMetrics = DisplayMetrics()
}
open class Activity {
  val resources = Resources()
  open fun onStart() {}
}
class Bitmap {
  private val width: Int
  private val height: Int
  @Suppress("ConvertSecondaryConstructorToPrimary")
  private constructor (width: Int, height: Int) {
    this.width = width
    this.height = height
  }

  companion object {
    fun createBitmap(width: Int, height: Int) = Bitmap(width, height)
  }
}



class MyActivity41 : Activity() {
  /*
   * Old-school way of defining a dip utility function within the Activity.
   * This is an ordinary member function.
   */
  private fun dp(px: Int) = px * resources.displayMetrics.density

  override fun onStart() {
    val bitmap = Bitmap.createBitmap(dp(200), dp(100))
    //...
  }
}
















class MyActivity42 : Activity() {
  /*
   * Better way, allowing more convenient invocation of the utility function.
   * This is a member extension function.
   */
  private val Int.dp get() = this * resources.displayMetrics.density

  override fun onStart() {
    val bitmap = Bitmap.createBitmap(200.dp, 100.dp) // more convenient usage
    //...
  }
}











/*
 * Unfortunately, we will need to duplicate this
 * utility function in each of our Activity classes.
 *
 * Is there a way to define this extension function globally as a top-level function?
*/



















/*
 * Prior to Kotlin 1.6.20 top-level functions could have at most 1 receiver.
 *
 * The best thing that we could do is this:
 */
fun Activity.dp(px: Int) = px * this.resources.displayMetrics.density

class MyActivity43 : Activity() {
  override fun onStart() {
    val bitmap = Bitmap.createBitmap(dp(200), dp(100)) // not the most convenient anymore
    //...
  }
}

/*
 * To get back the convenient invocation, we would need to be able to define
 * both arguments as receivers. But, except for member extensions, this is not
 * possible (prior to Kotlin 1.6.20).
 */
