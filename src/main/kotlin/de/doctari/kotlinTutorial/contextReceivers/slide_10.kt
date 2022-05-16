package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Dependency Injection
 *
 *   Solution via "contextual interface pattern" (prior to Kotlin 1.6.20)
 *
 * **************************************************************************************
 * ************************************************************************************** */

interface UrlContext {
  val urls: Urls
}

interface I18nContext {
  val i18n: I18n
}

/*
 * The main difference to previous SearchController09 is that our new
 * controller here now implements 2 context interfaces, and it provides
 * concrete implementations for the urls / i18n properties that come
 * from those interfaces.
 */
class SearchController10(
  override val urls: Urls,
  override val i18n: I18n,
) : UrlContext, I18nContext {

  fun render(): String = """
    <html>
      <head>
        <title>${i18n.searchTitle}</title>
      </head>
      <body>
        ${homeBreadcrumb10()}
        <h1>${i18n.searchTitle}</h1>
      </body>
    </html>
  """.trimIndent()
}

/*
 * This is now an extension on a T that extends both of UrlContext and I18nContext.
 * This function can only be invoked when an extension receiver is provided,
 * which implements both of those interfaces.
 *
 * Fortunately, our class implements both of those interfaces, so we can invoke
 * this function in a very convenient way (see invocation above).
 */
fun <T> T.homeBreadcrumb10(): String where T : UrlContext, T : I18nContext {
  return """<a href="${urls.homeUrl}">${i18n.homeTitle}</a>"""
}


/*
 * The disadvantage of the "contextual interface" pattern is that now
 * the dependencies of SearchController10 cannot be private anymore.
 * Also, the signature of homeBreadcrumb10() is not very concise.
 *
 * Is there a better way?
 * => see next slide
 */


fun main() {
  val searchController = DemoBeanFactory.searchController10
  println(searchController.render())
}

val DemoBeanFactory.searchController10 get() = SearchController10(urls, i18n)
