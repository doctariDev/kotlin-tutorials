@file:Suppress("MemberVisibilityCanBePrivate", "unused", "UNUSED_VARIABLE")

package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Beginning with Kotlin 1.6.20
 *   we can define contextual functions / properties / classes
 *
*    ᐅ contextual declarations can have arbitrary number of "context receivers"
 *   ᐅ contextual declarations can only be invoked by providing all context receivers
 *   ᐅ the context receivers can only be provided implicitly (no qualified syntax) !!!
 *   ᐅ "this" within a contextual declaration never refers to a context receiver !!!
 *   ᐅ "this@Type" refers to the context receiver
 *
 *   ᐅ contextual declarations can be mixed with extension / dispatch receivers also
 *
 *
 *   Use cases for contextual declarations:
 *
 *   ᐅ Multiple Receivers
 *   ᐅ Simplified DSLs
 *   ᐅ Dependency Injection
 *   ᐅ Type Classes
 *   ᐅ Context Oriented Programming
 *
 * **************************************************************************************
 * ************************************************************************************** */
