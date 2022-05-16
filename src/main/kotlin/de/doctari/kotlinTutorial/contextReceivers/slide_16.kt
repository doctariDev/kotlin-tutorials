package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Context Oriented Programming
 *
 *   All the previous examples have been context oriented programming.
 *
 *   In future many libraries might make use of this concept. For example, whenever
 *   a library would normally require you to chain an argument through a call chain,
 *   it could now become a context receiver.
 *
 *   In the past, this was often made more convenient by providing compiler plugins
 *   that would provide the argument for you:
 *
 *   ᐅ CoroutineScope
 *     automatically provided by a compiler plugin to all suspend functions
 *
 *   ᐅ Transactions
 *     are often tracked via annotation processors
 *
 *   ᐅ Jetpack Compose
 *     the composable scope is provided by a compiler plugin to annotated functions
 *
 *   In the future, these libraries might not need a compiler plugin anymore.
 *
 * **************************************************************************************
 * ************************************************************************************** */


