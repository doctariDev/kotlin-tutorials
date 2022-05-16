package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Type Classes
 *
 *   Example 2
 *
 *   Problem description
 *
 * **************************************************************************************
 * ************************************************************************************** */

data class User (
  val firstName: String,
  val lastName: String,
  val avatarUrl: String,
)

data class Admin (
  val name: String,
  val logo: String,
)

class AvatarComponent(private val url: String, private val alt: String) {
  fun render() = """<img src="$url" alt="$alt" />"""
}



/*
 * All of following implementations are similar.
 *
 * Can we get rid of the code duplication?
 * Can we create an abstraction for it, such that we can use it in more use cases?
 */

fun User.render14() = AvatarComponent(url = this.avatarUrl, alt = this.firstName + " " + this.lastName).render()
fun Admin.render14() = AvatarComponent(url = this.logo, alt = this.name).render()




fun main() {
  val user = User("Peter", "Parker", "ppavatar.png")
  println(user.render14())

  val admin = Admin("Doctari", "doctari.png")
  println(admin.render14())
}
