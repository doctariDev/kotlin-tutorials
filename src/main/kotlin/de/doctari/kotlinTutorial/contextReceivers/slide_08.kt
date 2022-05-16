@file:Suppress("FunctionName", "unused")

package de.doctari.kotlinTutorial.contextReceivers

import kotlinx.serialization.json.*

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Simplified DSL Declarations
 *
 * **************************************************************************************
 * ************************************************************************************** */

/*
 * buildJsonObject is an API provided by the Kotlinx serialization library.
 */

val json1 = buildJsonObject {
  put("clubName", "Kotlin User Group")
  putJsonArray("members") {
    add(buildJsonObject {
      put("name", "Bob")
      put("age", 12)
    })
    add(buildJsonObject {
      put("name", "Sue")
      put("age", 34)
    })
  }
}

/*
 * The above piece of code is not very readable. Can we create a DSL API that will simplify it?
 */



















/*
 * This is what we would like to write instead of above:
 */
val json2 = json {
  "clubName" `=` "Kotlin User Group"
  "members" `=` listOf(
    json {
      "name" `=` "Bob"
      "age" `=` 12
    },
    json {
      "name" `=` "Sue"
      "age" `=` 34
    }
  )
}

/*
 * Prior to Kotlin 1.6.20 the above DSL was possible only by
 * creating a class that involves member extension functions.
 *
 * This had many disadvantages.
 *
 * Thanks to context receivers, we can now make this DSL
 * possible with top-level functions:
 */








fun json(action: JsonObjectBuilder.() -> Unit) = buildJsonObject(action)

context(JsonObjectBuilder)
infix fun String.`=`(value: String) {
  put(this, value)
}

context(JsonObjectBuilder)
infix fun String.`=`(value: Number) {
  put(this, value)
}

context(JsonObjectBuilder)
infix fun String.`=`(values: List<JsonElement>) {
  putJsonArray(this) {
    for (value in values) add(value)
  }
}





fun main() {
  println(json1)
  println(json2)
}
