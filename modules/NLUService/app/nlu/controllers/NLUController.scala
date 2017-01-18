package nlu.controllers

import javax.inject._

import nlu.intent.IntentRecognizer
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class NLUController @Inject() extends Controller {

  val intentRecognizer = new IntentRecognizer

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def getIntent = Action { implicit request =>
    val input = request.queryString.get("input").orNull
    if (input == null) {
      BadRequest("No 'input' parameter specified")
    } else {
      Ok(intentRecognizer.getIntent(input.head))
    }
  }
}
