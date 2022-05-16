@file:Suppress("unused")

package de.doctari.kotlinTutorial.contextReceivers

/* **************************************************************************************
 * **************************************************************************************
 *
 *   Use Case: Dependency Injection
 *
 *   Solution via contextual class declarations (since Kotlin 1.6.20)
 *
 * **************************************************************************************
 * ************************************************************************************** */

context(UrlContext, I18nContext)
class SearchController11 {
  fun render(): String = """
    <html>
      <head>
        <title>${i18n.searchTitle}</title>
      </head>
      <body>
        ${homeBreadcrumb11()}
        <h1>${i18n.searchTitle}</h1>
      </body>
    </html>
  """.trimIndent()
}

context(UrlContext, I18nContext)
fun homeBreadcrumb11(): String = """<a href="${urls.homeUrl}">${i18n.homeTitle}</a>"""

fun main() {
  val searchController11 = DemoBeanFactory.searchController11
  println(searchController11.render())
}















val DemoBeanFactory.urlContext: UrlContext get() = object : UrlContext {
  override val urls = object : Urls {
    override val homeUrl = "http://localhost:8080"
    override val searchUrl = "http://localhost:8080/search"
  }
}

val DemoBeanFactory.i18nContext: I18nContext get() = object : I18nContext {
  override val i18n = object : I18n {
    override val homeTitle = "Home"
    override val searchTitle = "Search"
  }
}

val DemoBeanFactory.searchController11: SearchController11 get() =
  with (urlContext) {
    with (i18nContext) {
      SearchController11()
    }
  }
