package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Dependency Injection
 *
 *   Problem Description
 *
 * **************************************************************************************
 * ************************************************************************************** */

/*
 * There are various singleton bean implementations of this depending on environment.
 */
interface Urls {
  val homeUrl: String
  val searchUrl: String
}

/*
 * There are various singleton bean implementations of this depending on environment.
 */
interface I18n {
  val homeTitle: String
  val searchTitle: String
}

/*
 * This class has 2 dependencies, which are provided as constructor arguments here.
 * Usually, your dependency injection framework would provide these dependencies.
 */
class SearchController09(
  private val urls: Urls,
  private val i18n: I18n,
) {

  fun render(): String = """
    <html>
      <head>
        <title>${i18n.searchTitle}</title>
      </head>
      <body>
        ${homeBreadcrumb09(urls, i18n)}
        <h1>${i18n.searchTitle}</h1>
      </body>
    </html>
  """.trimIndent()
}

/*
 * Problem: dependency injection does not play well with top-level declarations.
 * We need to repeat the list of dependencies here. What's worse we need to provide
 * them explicitly wherever we want to invoke this function (see invocation).
 */
fun homeBreadcrumb09(urls: Urls, i18n: I18n): String = """<a href="${urls.homeUrl}">${i18n.homeTitle}</a>"""













fun main() {
  val searchController = DemoBeanFactory.searchController09
  println(searchController.render())
}













object DemoBeanFactory {
  val urls = object : Urls {
    override val homeUrl = "http://localhost:8080"
    override val searchUrl = "http://localhost:8080/search"
  }

  val i18n = object : I18n {
    override val homeTitle = "Home"
    override val searchTitle = "Search"
  }

  val searchController09 = SearchController09(urls, i18n)
}
