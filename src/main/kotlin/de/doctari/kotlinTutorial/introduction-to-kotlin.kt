@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType", "MayBeConstant", "FunctionName",
  "UNREACHABLE_CODE", "UnnecessaryVariable", "ClassName", "ClassName", "MemberVisibilityCanBePrivate",
  "ReplaceJavaStaticMethodWithKotlinAnalog", "UNUSED_VARIABLE", "DuplicatedCode", "DuplicatedCode",
  "RemoveToStringInStringTemplate", "RemoveRedundantQualifierName"
)

package de.doctari.kotlinTutorial

import com.google.gson.Gson
import de.doctari.kotlinTutorial.SelectDropdownJ.*
import java.awt.Button
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.HashSet
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import java.util.function.Predicate
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty













/**
 * Marks code that is written in a Java-esque way.
 *
 * Sometimes exaggerated for the sake of the tutorial.
 */
annotation class Java_esque


/**
 * Marks code that is written in a kotlin-idiomatic way.
 *
 * This annotation is only used when there is java-esque code to compare to.
 * When neither of the annotations are used, we assume idiomatic kotlin code by default.
 */
annotation class Kotlin_esque


























/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ variables
 *   ᐅ var / val
 *   ᐅ top-level variables (package properties)
 *   ᐅ const variables
 *
 * ⬤ types
 *   ᐅ Any, Any?, Unit, Nothing
 *   ᐅ nullable types
 *
 * ⬤ top-level code not allowed (like in JS)
 *
 * **************************************************************************************
 * ************************************************************************************** */



val name: String = "Dave"
var nameOrNull: String? = name

val someObject: Any = name
val someObjectOrNull: Any? = someObject


val noValue: Unit = Unit
val impossible: Nothing = throw Error()





















/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ functions
 *   ᐅ normal arguments
 *   ᐅ named arguments
 *   ᐅ default argument values
 *   ᐅ vararg arguments (and spread operator)
 *   ᐅ local variables
 *   ᐅ return types
 *   ᐅ no return type/value (you use Unit type; usually implicitly)
 *   ᐅ functions with block body / expression body
 *   ᐅ top-level functions (package functions)
 *
 * ⬤ expressions
 *   ᐅ everything is an expression (except for assignments)
 *   ᐅ if expressions
 *   ᐅ when expressions
 *
 * **************************************************************************************
 * ************************************************************************************** */



@Java_esque
fun truncate(s: String, maxLength: Int, prefix: String, suffix: String): String {
  val result: String

  if (s.length > maxLength) {
    result = prefix + s.substring(0, maxLength) + suffix
  } else {
    result = s
  }

  return result
}

@Java_esque
fun printTruncated(s: String): Unit {
  print(truncate(s, 10, "", "..."))

  return Unit
}

























@Kotlin_esque
fun truncate_1(s: String, maxLength: Int, prefix: String = "", suffix: String = ""): String {
  return if (s.length > maxLength) {
    prefix + s.substring(0, maxLength) + suffix
  } else {
    s
  }
}


@Kotlin_esque
fun truncate_2(
  s: String,
  maxLength: Int,
  prefix: String = "",
  suffix: String = ""
) = if (s.length > maxLength) {
  prefix + s.substring(0, maxLength) + suffix
} else {
  s
}


@Kotlin_esque
fun truncate_3(s: String, maxLength: Int, prefix: String = "", suffix: String = "") = when {
  s.length > maxLength -> prefix + s.substring(0, maxLength) + suffix
  else -> s
}













@Kotlin_esque
fun printTruncated_1(s: String) {
  print(truncate_3(s, maxLength = 10, suffix = "..."))
}


@Kotlin_esque
fun printTruncated_2(s: String) = print(truncate_3(s, maxLength = 10, suffix = "..."))
























fun printAllWithPrefix(vararg messages: String, prefix: String) {
  for (m in messages) println(prefix + m)
}



val unit6 = printAllWithPrefix(
  "Hello", "Hallo", "Salut", "Hola", "Selam",
  prefix = "Greeting: "
)



val names = arrayOf("Peter", "Clark", "Tony", "Bruce")
val unit7 = printAllWithPrefix(*names, prefix = "Greetings: ")
























/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ nullability operators
 *   ᐅ elvis operator
 *   ᐅ safe-call operator
 *   ᐅ non-null asserting operator
 *
 * ⬤ More fun with expressions (power of Nothing)
 *   ᐅ return expressions
 *   ᐅ throw expressions
 *   ᐅ try expressions
 *
 * ⬤ casts
 *   ᐅ explicit casts
 *   ᐅ implicit (smart) casts
 *
 * ⬤ string interpolations / string templates
 *
 * **************************************************************************************
 * ************************************************************************************** */



fun getLengthOrZero(s: String?): Int = s?.length ?: 0

fun getLengthUnsafe(s: String?): Int = s?.length!!

fun getLengthOrNull(s: String?): Int? = s?.length

























fun getUserByName(name: String): UserWithAddress? {
  val result: UserWithAddress = TODO()

  return result
}


@Java_esque
fun printUserAddress(userName: String): Unit {
  val nullableUser: User? = getUserByName(userName)
  if (nullableUser == null) {
    throw IllegalArgumentException()
  }

  val user: UserWithAddress = nullableUser as UserWithAddress

  print("User " + userName + "'s address is " + user.address)
  return Unit
}


@Java_esque
fun printUserAddressOptional(userName: String): Unit {
  val nullableUser = getUserByName(userName)
  if (nullableUser == null) {
    return Unit
  }

  val user: UserWithAddress = nullableUser as UserWithAddress

  print("User " + userName + "'s address is " + user.address)
  return Unit
}



















@Kotlin_esque
fun printUserAddress_1(userName: String) {
  val user = getUserByName(userName) ?: throw IllegalArgumentException()

  print("User $userName's address is ${user.address}")
}


@Kotlin_esque
fun printUserAddressOptional_1(userName: String) {
  val user = getUserByName(userName) ?: return

  print("User $userName's address is ${user.address}")
}




















@Kotlin_esque // kind of
var parsedNumberOrFallback = try { "500".toInt() } catch (e: Exception) { 0 }


































@Kotlin_esque // even better
var parsedNumberOrFallback_1 = "500".toIntOrNull() ?: 0





























/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ classes
 *   ᐅ class declarations / instantiation
 *   ᐅ primary constructor
 *   ᐅ secondary constructors
 *   ᐅ visibility
 *   ᐅ member functions
 *   ᐅ default constructor argument values
 *   ᐅ named constructor arguments
 *   ᐅ non-open (final) by default
 *
 * ⬤ properties:
 *   ᐅ val / var
 *   ᐅ simple / computed
 *
 * ⬤ visibility modifiers: private, protected, public, internal
 *
 * **************************************************************************************
 * ************************************************************************************** */



class EmptyFoo

class CityAndZipcode(val city: String, val zipcode: String)

class Coordinates(val longitude: Double, val latitude: Double)






















@Java_esque
class AddressJ {

  private val city: String
  private val zipcode: String
  private val street: String
  private val coordinates: Coordinates?

  constructor(street: String, zipcode: String, city: String, coordinates: Coordinates) {
    this.street = street
    this.zipcode = zipcode
    this.city = city
    this.coordinates = coordinates
  }

  constructor(street: String, zipcode: String, city: String) {
    this.street = street
    this.zipcode = zipcode
    this.city = city
    this.coordinates = null
  }

  fun getCity(): String {
    return city
  }
  fun getZipcode(): String {
    return zipcode
  }
  fun getStreet(): String {
    return street
  }
  fun getCoordinates(): Coordinates? {
    return coordinates
  }

  fun toCityAndZipcode(): CityAndZipcode {
    return CityAndZipcode(city, zipcode)
  }

  fun hasCoordinates(): Boolean {
    return coordinates != null
  }
}























@Kotlin_esque
class AddressK(
  val street: String,
  val zipcode: String,
  val city: String,
  val coordinates: Coordinates? = null
) {
  fun toCityAndZipcode() = CityAndZipcode(city, zipcode)

  val hasCoordinates get() = coordinates != null
}


































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ lateinit properties
 *    ᐅ e.g. when autowiring
 *
 * ⬤ delegated properties
 *    ᐅ e.g. when autowiring
 *    ᐅ many built-in property delegates exist (lazy, observable, ...)
 *
 * **************************************************************************************
 * ************************************************************************************** */



annotation class Autowired

class MySingletonService {
  // ...
}


@Java_esque
class MyControllerJ {
  // autowired property needs to be nullable here, is there a better way?
  @Autowired
  private val myService: MySingletonService? = null

  fun handleRequest() {
    // ...
  }
}



























@Kotlin_esque
class MyControllerK1 {

  // unfortunately it is a var now, is there a better way?
  @Autowired
  private lateinit var myService: MySingletonService

  fun handleRequest() {
    // ...
  }
}





























@Kotlin_esque
class MyControllerK2 {

  private val myService by Autowire<MySingletonService>()

  fun handleRequest() {
    // ...
  }
}




























class Autowire<T> : ReadOnlyProperty<Any, T> {
  override operator fun getValue(thisRef: Any, property: KProperty<*>): T {
    TODO("Not yet implemented")
  }
}





























/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ inheritance
 *
 * ⬤ interfaces
 *
 * ⬤ open classes
 *
 * ⬤ abstract classes
 *
 * **************************************************************************************
 * ************************************************************************************** */



interface User {
  val name: String
}

open class UserWithAddress(override val name: String, var address: AddressK? = null) : User
































class AnonymousUser(override val name: String) : User
































abstract class PrivilegedUser(name: String, address: AddressK?) : UserWithAddress(name, address) {
  abstract fun hasPrivilege(privilege: String): Boolean
}






























class Admin(
  name: String,
  address: AddressK? = null,
  private val privileges: Set<String> = emptySet()
) : PrivilegedUser(name, address) {

  override fun hasPrivilege(privilege: String) = privileges.contains(privilege)
}
































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ class delegation (decorator pattern)
 *
 * **************************************************************************************
 * ************************************************************************************** */



@Java_esque
class MyMapJ<K,V>(private val backingMap: Map<K,V>) : Map<K, V> {

  override val entries get() = backingMap.entries
  override val keys get() = backingMap.keys
  override val size get() = backingMap.size
  override val values get() = backingMap.values
  override fun containsKey(key: K) = backingMap.containsKey(key)
  override fun containsValue(value: V) = backingMap.containsValue(value)
  override fun isEmpty() = backingMap.isEmpty()

  override fun get(key: K): V? {
    val value = backingMap[key]
    print("$key returned $value")
    return value
  }
}

























@Kotlin_esque
class MyMapK<K,V>(private val backingMap: Map<K,V>) : Map<K, V> by backingMap {

  override fun get(key: K): V? {
    val value = backingMap[key]
    print("$key returned $value")
    return value
  }
}

































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ data classes
 *
 * ⬤ destructuring declarations
 *
 * ⬤ sealed classes
 *
 * ⬤ type checks and implicit type casts (smart casts)
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Java_esque
class ResultJ<T>(val value: T?, val error: String?)

@Java_esque
fun validateAgeInputJ(age: String): ResultJ<Int> {
  try {
    return ResultJ(age.toInt(), null)
  } catch (e: NumberFormatException) {
    return ResultJ(null, "invalid.age.input")
  }
}

@Java_esque
fun resultUsageJ(): Any {
  val validatedAge = validateAgeInputJ("84")

  if (validatedAge.error != null) {
    return TODO("do something with ${validatedAge.error}")
  } else {
    return TODO("do something with ${validatedAge.value}")
  }
}



























@Kotlin_esque
data class ResultK1<T>(val value: T? = null, val error: String? = null)

@Kotlin_esque
fun validateAgeInputK1(age: String): ResultK1<Int> = try {
  ResultK1(value = age.toInt())
} catch (e: NumberFormatException) {
  ResultK1(error = "invalid.age.input")
}

@Kotlin_esque
fun resultUsageK1(): Any {
  val (age, error) = validateAgeInputK1("84")

  if (error != null) {
    return TODO("do something with $error")
  } else {
    return TODO("do something with $age")
  }
}




























@Kotlin_esque
sealed interface ResultK2<out T>
class SuccessK2<out T>(val value: T) : ResultK2<T>
class ErrorK2(val error: String) : ResultK2<Nothing>

@Kotlin_esque
fun validateAgeInputK2(age: String): ResultK2<Int> = try {
  SuccessK2(age.toInt())
} catch (e: NumberFormatException) {
  ErrorK2("invalid.age.input")
}

@Kotlin_esque
fun resultUsageK2(): Any {
  val age = validateAgeInputK2("84")

  if (age is ErrorK2) {
    return TODO("do something with ${age.error}")
  }
  if (age is SuccessK2) {
    return TODO("do something with ${age.value}")
  }
  throw IllegalStateException()
}

































@Kotlin_esque
fun resultUsageK3(): Any = when (val age = validateAgeInputK2("84")) {
  is ErrorK2 -> TODO("do something with ${age.error}")
  is SuccessK2 -> TODO("do something with ${age.value}")
}




































// The idiomatic Java way of solving below problem would be double dispatch / visitor pattern.
// It is omitted here for the sake of simplicity.


@Kotlin_esque
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
data class Diff(val e1: Expr, val e2: Expr) : Expr()
data class Times(val e1: Expr, val e2: Expr) : Expr()
data class Div(val e1: Expr, val e2: Expr) : Expr()

@Kotlin_esque
fun eval(expr: Expr): Double = when(expr) {
  is Const    -> expr.number
  is Sum      -> eval(expr.e1) + eval(expr.e2)
  is Diff     -> eval(expr.e1) - eval(expr.e2)
  is Times    -> eval(expr.e1) * eval(expr.e2)
  is Div      -> eval(expr.e1) / eval(expr.e2)
  // the `else` clause is not required because we've covered all the cases of a sealed class
}



















/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ interfaces with default methods
 *   ᐅ enable inheriting code from multiple super types
 *
 * **************************************************************************************
 * ************************************************************************************** */



interface Coder {
  fun code() {
    print("coding")
  }

  fun hack() {
    print("hacking code")
  }
}

interface Cook {
  fun cook() {
    print("cooking")
  }

  fun hack() {
    print("hacking food")
  }
}

class CodingCook : Coder, Cook {
  // The compiler requires hack() to be overridden:
  override fun hack() {
    super<Coder>.hack() // call to Coder.hack()
    super<Cook>.hack() // call to Cook.hack()
  }
}




































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ object expressions (anonymous types)
 *
 * **************************************************************************************
 * ************************************************************************************** */


fun guiAppExample() {
  val button = Button()

  button.addActionListener(object : ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
      // ...
    }
  })

}




























private val adhocTypedValue = object {
  var x: Int = 0
  var y: Int = 0
}

private fun getAdhocTypedValue() = object {
  val x = "foo"
  val y = "bar"
}

fun usingAdhocType() {
  // works only in private values/types

  print(adhocTypedValue.x + adhocTypedValue.y)

  val o = getAdhocTypedValue()
  print(o.x)
  print(o.y)
}





















/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ companion objects
 *
 * ⬤ singleton objects
 *
 * **************************************************************************************
 * ************************************************************************************** */



@Java_esque
class StringUtilsJ1 {
  // companion objects are not necessarily idiomatic Kotlin
  companion object {
    // consider using package-properties / constants instead of companion object constants like this:
    val EMPTY_STRING = ""

    // definitely just use a private top-level package property instead of this:
    private val DEFAULT_MAX_LENGTH = 50

    // consider using a package function instead of this or put it in an object class:
    fun isEmptyOrNull(s: String?) = s == null || s.isEmpty()

    // consider using a package function instead of this or put it in an object class:
    fun truncate(s: String, maxLength: Int = DEFAULT_MAX_LENGTH): String = TODO()
  }
}


val b1 = StringUtilsJ1.isEmptyOrNull(StringUtilsJ1.EMPTY_STRING)

























@Java_esque
class StringUtilsJ2 private constructor() {
  companion object {
    val INSTANCE = StringUtilsJ2()
  }

  fun truncate(s: String, maxLength: Int): String = TODO()
}

val s1: String = StringUtilsJ2.INSTANCE.truncate("foo", 20)
































// the following is more idiomatic kotlin than examples above
// but there are even more idiomatic ways of doing this
// (see next section about extension functions below)

interface GeoObject {
  fun getChildren(): Array<GeoObject>
}

object GeoUtils {
  fun getChildren(geoObject: GeoObject?): Array<GeoObject> = when (geoObject) {
    null -> emptyArray()
    else -> geoObject.getChildren()
  }
}


object StringUtilsK1 {
  fun truncate(s: String, maxLength: Int): String = TODO()
}

val s2: String = StringUtilsK1.truncate("foo", 20)

























/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ extension functions
 *
 * ⬤ extension properties
 *
 * ⬤ extensions on nullable types
 *
 * ⬤ member extension functions
 *
 * **************************************************************************************
 * ************************************************************************************** */



fun String.truncate(maxLength: Int): String = when {
  this.length > maxLength -> this.substring(0, maxLength)
  else -> this
}

val s3 = "foo".truncate(20)
































val String?.isEmptyOrNull: Boolean get() = this == null || this == ""

val b2 = "foo".isEmptyOrNull
val b3 = null.isEmptyOrNull






























fun GeoObject?.getChildren(geoObject: GeoObject?): Array<GeoObject> = when (geoObject) {
  null -> emptyArray()
  else -> geoObject.getChildren()
}



























interface Zipcode { val code: String }

interface ZipcodeService {
  fun parseZipcode(code: String): Zipcode
}

class MyZipcodeSelectionPageController {
  private val zipcodeService by Autowire<ZipcodeService>()

  fun handleRequest(code: String) {
    val zipcode = code.toZipcode()

    TODO("use the $zipcode")
  }

  private fun String.toZipcode(): Zipcode = zipcodeService.parseZipcode(this)
}





































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ infix functions
 *
 * ⬤ operator overloading
 *
 * **************************************************************************************
 * ************************************************************************************** */



infix fun Double.pow(other: Double) = Math.pow(this, other)

val result = 10.0 pow 2.0



























class MyListWithCustomOperators {
  private val list = mutableListOf<String>()

  operator fun plusAssign(value: String) {
    list.add(value)
  }

  operator fun contains(value: String) = list.contains(value)

  operator fun get(index: Int) = list.get(index)

  operator fun iterator() = list.iterator()

  operator fun component1() = list.get(0)

  operator fun component2() = list.get(1)

  operator fun component3() = list.get(2)

  operator fun invoke(s: String) = list.add(s)
}

fun customOperatorsUsage() {
  val list = MyListWithCustomOperators()

  list += "foo"
  list += "bar"

  val u = "foo" in list

  val x = list[1]
  val y = list[2]
  val z = list[100]

  val (a,b,c) = list
  print(a)
  print(b)
  print(c)

  for (value in list) {
    print(value)
  }

  list("another foo")
}



















fun idiomaticUsageOfMapOperators() {
  val map1 = mutableMapOf<String,String>()

  map1["foo"] = "bar"

  val v = map1["foo"]

  for ((key, value) in map1) {
    print(key)
    print(value)
  }
}






































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ generic types
 *
 * ⬤ use-site variance
 *
 * ⬤ declaration-site variance
 *
 * ⬤ Unit can instantiate a type parameter T
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Java_esque /*use-site variance*/
fun <K> incrementValueInMap(map: MutableMap<in K, Int>, key: K) {
  val value = map[key]
  if (value == null) {
    map[key] = 1
  } else {
    map[key] = value + 1
  }
}


@Java_esque /*use-site variance*/
fun <K, V> getFilteredValues(map: Map<out K, V>, predicate: Predicate<in K>): Set<V> {
  val filteredValues = HashSet<V>()

  for ((key, value) in map) {
    if (!predicate.test(key)) {
      continue
    }

    filteredValues += value
  }

  return filteredValues
}















@Kotlin_esque /*declaration site variance*/
class Producer<out T>(private val t: T) {
  fun produce(): T = t
}

val p: Producer<Any> = Producer<String>("foo")


@Kotlin_esque /*declaration site variance*/
class Consumer<in T> {
  fun consume(t: T) = print("Consuming $t")
}

val c: Consumer<String> = Consumer<Any>()
























interface Callback<in A, out R> {
  fun call(arg: A): R
}

fun <A,R> invokeDelayed(delayMs: Long, arg: A, callback: Callback<A,R>): R {
  Thread.sleep(delayMs)

  return callback.call(arg)
}


val string7 = invokeDelayed(1000, "foo", object : Callback<String, String> {
  override fun call(arg: String): String {
    return arg + arg
  }
})


@Java_esque
val unit8 = invokeDelayed(1000, "foo", object : Callback<String, java.lang.Void?> {
  override fun call(arg: String): java.lang.Void? {
    print(arg)
    return null
  }
})























@Kotlin_esque
val unit9 = invokeDelayed(1000, "foo", object : Callback<String, Unit> {
  override fun call(arg: String) {
    print(arg)
  }
})





















/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ function types / Lambda expressions
 *   ᐅ function types with extension receivers
 *   ᐅ extension lambda expressions (lambdas with receivers)
 *   ᐅ implicit "it" argument
 *   ᐅ function references
 *   ᐅ moving lambda argument out of parentheses
 *
 * **************************************************************************************
 * ************************************************************************************** */


val isLongText: (String) -> Boolean = { s -> s.length > 100 }
val isEmptyText1: (String) -> Boolean = { it.isEmpty() }
val isEmptyText2: String.() -> Boolean = { this.isEmpty() }

val b4 = isEmptyText1("foo")
val b5 = "foo".isEmptyText2()



fun <T> getFilteredValues(list: List<T>, predicate: (T) -> Boolean): List<T> {
  val result = mutableListOf<T>()

  for (t in list) {
    if (predicate(t)) {
      result.add(t)
    }
  }

  return result
}



fun usageOfGetFilteredValues() {
  val list = listOf("foo", "bar", "baz")

  val result1 = getFilteredValues(list, isLongText)
  val result2 = getFilteredValues(list, String::isEmpty)
  val result3 = getFilteredValues(list, isEmptyText1)
  val result4 = getFilteredValues(list, isEmptyText2)
}





















@Java_esque
fun usageOfGetFilteredValues_j1() {
  val list = listOf("foo", "bar", "baz")

  val result = getFilteredValues(list, { s: String ->
    val user = getUserByName(s)
    user != null
  })

  print(result)
}




























@Kotlin_esque
fun usageOfGetFilteredValues_k1() {
  val list = listOf("foo", "bar", "baz")

  val result = getFilteredValues(list) {
    getUserByName(it) != null
  }

  print(result)
}



































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ inline functions
 *    ᐅ return-transparency in inlined lambdas
 *    ᐅ reified generics in inlined functions
 *
 * **************************************************************************************
 * ************************************************************************************** */



fun my_if(condition: Boolean, code: () -> Unit) {
  if (condition) {
    code()
  }
}

fun printIfNotEmpty(s: String) {
  my_if(s.isNotEmpty()) {
    print(s)
  }
}




















/*
The following does not work though.
Can we make it work?
Yes, make my_if an inline function!

fun truncate_2(s: String, maxLength: Int): String {
  my_if(s.length > maxLength) {
    return s.substring(maxLength)
  }

  return s
}
*/























inline fun withLock(lock: Lock, body: () -> Unit) {
  lock.lock()
  try {
    body()
  } finally {
    lock.unlock()
  }
}


fun moveIntoHome(userName: String, home: AddressK): Boolean {
  val user: UserWithAddress = getUserByName(userName) ?: return false

  val lock = ReentrantLock()

  withLock(lock) {
    if (user.address != null) {
      return false
    }

    user.address = home
    print("moved $user into new $home")
  }

  return true
}





















@Java_esque
fun usageOfGsonJ() {
  val userJson = """
    {
      name: "Jacob"
    }
  """

  val user: User = Gson().fromJson(userJson, User::class.java)
  print(user)
}



























@Kotlin_esque
inline fun <reified T> Gson.fromJsonK(json: String): T = this.fromJson(json, T::class.java)

@Kotlin_esque
fun usageOfGsonK() {
  val userJson = """
    {
      name: "Jacob"
    }
  """

  val user: User = Gson().fromJsonK<User>(userJson)
  print(user)
}































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ type aliases
 *
 * ⬤ value classes
 *
 * **************************************************************************************
 * ************************************************************************************** */


typealias MyPredicate<T> = (T) -> Boolean

typealias UserId1 = Long

@JvmInline
value class UserId2(val id: Long)






















fun getUserById1(id: UserId1): User? = TODO()
fun getUserById2(id: UserId2): User? = TODO()

val user1: User? = getUserById1(1337)
val user2: User? = getUserById2(UserId2(1337))

































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ standard higher-order scoping functions (and when to use them)
 *   ᐅ apply
 *   ᐅ let
 *   ᐅ with
 *   ᐅ run
 *   ᐅ also
 *
 * ⬤ See
 *   ᐅ https://docs.google.com/spreadsheets/d/1P2gMRuu36pSDW4fdwE-fLN9fcA_ZboIU2Q5VtgixBNo/edit?usp=drive_web
 *   ᐅ https://medium.com/@fatihcoskun/kotlin-scoping-functions-apply-vs-with-let-also-run-816e4efb75f5
 *
 * **************************************************************************************
 * ************************************************************************************** */



inline fun <T, R> T.run(block: T.() -> R): R = block()
inline fun <T, R> with(receiver: T, block: T.() -> R): R = receiver.block()
inline fun <T> T.apply(block: T.() -> Unit): T { block(); return this }
inline fun <T> T.also(block: (T) -> Unit): T { block(this); return this }
inline fun <T, R> T.let(block: (T) -> R): R = block(this)





























/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ apply
 *   ᐅ apply initialization code on a newly created object and then return it
 *
 * **************************************************************************************
 * ************************************************************************************** */



@Java_esque
fun exampleWithoutApply(): String {
  val sb = StringBuilder()

  sb.append("foo")
  sb.append("bar")
  sb.append("baz")

  return sb.toString()
}




























@Kotlin_esque
fun exampleWithApply() = StringBuilder().apply {
  append("foo")
  append("bar")
  append("baz")
}.toString()







/*

See section about type-safe builder pattern for more complex example usage of apply!

*/

























/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ let
 *    ᐅ limit the scope of local variables
 *    ᐅ null-safe apply functions on nullable objects
 *    ᐅ convert objects to a different type
 *
 * ⬤ See also
 *    ᐅ https://medium.com/@fatihcoskun/kotlin-nullable-types-vs-java-optional-988c50853692
 *
 * **************************************************************************************
 * ************************************************************************************** */



@Java_esque
fun exampleWithoutLet1() {
  val user1 = getUserByName("jacob")
  if (user1 != null) {
    print(user1.name)
    print(user1.address)
  }

  val user2 = getUserByName("tony")
  if (user2 != null) {
    print(user2.name)
    print(user2.address)
  }
}
























@Kotlin_esque
fun exampleWithLet1() {
  // limit the scope of local variables
  // null-safe apply functions on nullable objects

  getUserByName("jacob")?.let {
    print(it.name)
    print(it.address)
  }

  getUserByName("tony")?.let {
    print(it.name)
    print(it.address)
  }
}




















fun getRating(user: User): Int = TODO()

@Java_esque
fun exampleWithoutLet2() {
  val jacob = getUserByName("jacob")

  val jacobsAddress: AddressK? = jacob?.address
  val jacobsRating: Int? = if (jacob == null) null else getRating(jacob)
}

























@Kotlin_esque
fun exampleWithLet2() {
  // convert objects to a different type

  val jacob = getUserByName("jacob")

  val jacobsAddress: AddressK? = jacob?.address
  val jacobsRating: Int? = jacob?.let { getRating(it) }
}


















/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ with
 *    ᐅ execute functions on an already existing object
 *    ᐅ kind of locally scoped import statement
 *
 * **************************************************************************************
 * ************************************************************************************** */



interface ComplexService {
  fun foo(s: String): String = TODO()
  fun bar(s: String): String = TODO()
  fun baz(s: String): String = TODO()
}

val complexService: ComplexService = TODO()

@Java_esque
fun exampleWithoutWith(s: String): String {
  return complexService.foo(complexService.bar(complexService.baz(s)))
}





















@Kotlin_esque
fun exampleWithWith(s: String): String = with(complexService) {
  return foo(bar(baz(s)))
}






































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ run
 *    ᐅ similar to with, but different signature
 *    ᐅ limit the scope of multiple local variables
 *
 * **************************************************************************************
 * ************************************************************************************** */



@Kotlin_esque
fun exampleWithRun1(s: String): String = complexService.run {
  return foo(bar(baz(s)))
}
























@Kotlin_esque
fun exampleWithRun2() {

  val user: User = run {
    val name: String = TODO("handle and parse input")
    val address: AddressK = TODO("handle and parse input")

    UserWithAddress(name,  address)
  }

  print(user)
}































/* **************************************************************************************
 * **************************************************************************************
 *
 * ⬤ also
 *    ᐅ execute side effects on an object without changing it, and then return it
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Kotlin_esque
fun exampleWithAlso() = StringBuilder().apply {
  append("foo")
  append("bar")
  append("baz")
}.also {
  print("Initialized string $it")
}.toString()































/* **************************************************************************************
 * **************************************************************************************
 *
 *               ******************************************
 *               ************** Advanced topic ************
 *               ******************************************
 *
 * ⬤ type-safe builder pattern for defining DSLs
 *
 * **************************************************************************************
 * ************************************************************************************** */


@Java_esque
class SelectDropdownJ(val paramName: String) {
  val options = mutableListOf<OptionJ>()

  class OptionJ(val paramValue: String, val label: String) {
    var isSelected: Boolean = false
  }
}

@Java_esque
class MySearchResultsPageModelJ {
  var sortingDropdown: SelectDropdownJ? = null
  var resultsPerPageDropdown: SelectDropdownJ? = null
}


@Java_esque
fun prepareMySearchResultsPageModelJ(): MySearchResultsPageModelJ {
  val model = MySearchResultsPageModelJ()

  val sortingDropdown = SelectDropdownJ("sorting")

  val sortingOption1 = OptionJ("price", "price.label")
  sortingOption1.isSelected = false
  sortingDropdown.options.add(sortingOption1)

  val sortingOption2 = OptionJ("date", "date.label")
  sortingOption2.isSelected = true
  sortingDropdown.options.add(sortingOption2)

  model.sortingDropdown = sortingDropdown


  val resultsPerPageDropdown = SelectDropdownJ("resultsPerPage")

  val resultsPerPageOption1 = OptionJ("5", "5")
  resultsPerPageOption1.isSelected = false
  resultsPerPageDropdown.options.add(resultsPerPageOption1)

  val resultsPerPageOption2 = OptionJ("10", "10")
  resultsPerPageOption2.isSelected = true
  resultsPerPageDropdown.options.add(resultsPerPageOption2)

  model.resultsPerPageDropdown = resultsPerPageDropdown

  return model
}



















@Kotlin_esque
fun prepareMySearchResultsPageModelK1() = MySearchResultsPageModelJ().apply {
  sortingDropdown = SelectDropdownJ("sorting").apply {
    options += OptionJ("price", "price.label").apply {
      isSelected = false
    }

    options += OptionJ("date", "date.label").apply {
      isSelected = true
    }
  }

  resultsPerPageDropdown = SelectDropdownJ("resultsPerPage").apply {
    options += OptionJ("5", "5").apply {
      isSelected = false
    }

    options += OptionJ("10", "10").apply {
      isSelected = true
    }
  }
}





















@Kotlin_esque /* DSL approach: */
fun prepareMySearchResultsPageModelK2() = MySearchResultsPageModelK().apply {
  sortingDropdown("sorting") {
    Option("price", "price.label") {
      isSelected = false
    }

    Option("date", "date.label") {
      isSelected = true
    }
  }


  resultsPerPageDropdown("resultsPerPage") {
    Option("5", "5")  {
      isSelected = false
    }

    Option("10", "10") {
      isSelected = true
    }
  }
}




















@Kotlin_esque /* DSL approach: */
class MySearchResultsPageModelK {
  var sortingDropdown: SelectDropdownK? = null
  var resultsPerPageDropdown: SelectDropdownK? = null

  fun sortingDropdown(paramName: String, initAction: SelectDropdownK.() -> Unit) {
    val dropdown = SelectDropdownK(paramName)
    initAction(dropdown)
    this.sortingDropdown = dropdown
  }

  fun resultsPerPageDropdown(paramName: String, initAction: SelectDropdownK.() -> Unit) {
    val dropdown = SelectDropdownK(paramName)
    initAction(dropdown)
    this.resultsPerPageDropdown = dropdown
  }

  companion object {
    operator fun invoke(action: MySearchResultsPageModelK.() -> Unit) = MySearchResultsPageModelK().apply(action)
  }
}

@Kotlin_esque
class SelectDropdownK(val paramName: String) {
  val options = mutableListOf<OptionK>()

  fun Option(value: String, label: String, initAction: OptionK.() -> Unit) {
    val option = OptionK(value, label)
    initAction(option)
    options += option
  }
}

@Kotlin_esque
class OptionK(val value: String, val label: String) {
  var isSelected = false
}

