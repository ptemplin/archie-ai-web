package tts.controllers

import javax.inject._

import play.api.mvc._
import util.Test

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class VoiceBuilderController @Inject() extends Controller {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def buildSpeech = Action { implicit request =>
    val input = request.queryString.get("text").orNull
    if (input == null) {
      BadRequest("No 'text' parameter specified")
    } else {
      Ok(Test.getSpeech(input.head))
    }
  }
}
