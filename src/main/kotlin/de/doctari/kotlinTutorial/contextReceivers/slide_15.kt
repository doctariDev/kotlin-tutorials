package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Type Classes
 *
 *   Example 2
 *
 *   Solution
 *
 * **************************************************************************************
 * ************************************************************************************** */

typealias AvatarComponentFactory<T> = (T) -> AvatarComponent


context(AvatarComponentFactory<T>)
fun <T> T.render() = this@AvatarComponentFactory.invoke(this).render()














val UserAvatarFactory: AvatarComponentFactory<User> = { AvatarComponent(alt = "${it.firstName} ${it.lastName}", url = it.avatarUrl) }

val AdminAvatarFactory: AvatarComponentFactory<Admin> = { AvatarComponent(alt = it.name, url = it.logo)}










fun User.render15() = with(UserAvatarFactory) { render() }
fun Admin.render15() = with(AdminAvatarFactory) { render() }















fun main() {
  val user = User("Peter", "Parker", "ppavatar.png")
  println(user.render15())

  val admin = Admin("Doctari", "doctari.png")
  println(admin.render15())
}
