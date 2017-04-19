package com.toidiu.scrape


import java.net.URL

import com.toidiu.timer.TimerExecute
import org.apache.commons.validator.routines.UrlValidator
import org.jsoup.Jsoup

import scala.collection.JavaConverters._

case class Content(title: String, meta: String, urls: List[URL])

object ScrapeExecute extends TimerExecute {

  val urlValidator = new UrlValidator()
  val chrome = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"
  val firefox = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:52.0) Gecko/20100101 Firefox/52.0"

  def execute: Unit = {
//    val content = parse(new URL("http://toidiu.com"))
    val content = parse(new URL("https://toidiu.github.io/projects"))
    println(content.title)
    println(content.urls)
    println("-=-=-=-=-")
  }

  def parse(url: URL): Content = {
    val link: String = url.toString
    val response = Jsoup.connect(link).ignoreContentType(true)
      .userAgent(firefox).execute()

    val contentType: String = response.contentType
    if (contentType.startsWith("text/html")) {
      val doc = response.parse()
      val title: String = doc.getElementsByTag("title").asScala.map(e => e.text()).head
      val descriptionTag = doc.getElementsByTag("meta").asScala.filter(e => e.attr("name") == "description")
      val description = if (descriptionTag.isEmpty) "" else descriptionTag.map(e => e.attr("content")).head
      val links: List[URL] = doc.getElementsByTag("a").asScala.map(e => e.attr("href")).filter(s =>
        urlValidator.isValid(s)).map(link => new URL(link)).toList
      Content(title, description, links)
    } else {
      // e.g. if this is an image
      Content(link, contentType, List())
    }
  }

}

