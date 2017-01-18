package controllers

import javax.inject._

import play.api.mvc._
import util.Test
import asr.gateway.RecognitionGateway
import intenthandlers.{BasicIntentHandler, ClientAction}
import nlu.intent.IntentRecognizer
import play.api.libs.json.{Json, Writes}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ArchieController @Inject() extends Controller {

  val speechRecognizer = new RecognitionGateway
  val intentRecognizer = new IntentRecognizer
  val intentHandler = new BasicIntentHandler

  implicit val clientActionWrites = new Writes[ClientAction] {
    def writes(action: ClientAction) = Json.obj(
      "display" -> action.display,
      "speak" -> action.speak
    )
  }

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action { implicit request =>
    Ok("Index")
  }

  def getActionFromSpeech = Action { implicit request =>
    val rawBody = request.body.asRaw.orNull
    val bodyBytes = rawBody.asBytes(500000).orNull.asByteBuffer
    val transcription: String = speechRecognizer.recognize(bodyBytes)
    val intent = intentRecognizer.getIntent(transcription)
    val action = intentHandler.handleIntent(intent)
    Ok(Json.toJson(action))
  }

  def getSpeechPlayback = Action { implicit request =>
    val input = request.queryString.get("text").orNull
    if (input == null) {
      BadRequest("No 'text' parameter specified")
    } else {
      Ok(Test.getSpeech(input.head))
    }
  }
}
