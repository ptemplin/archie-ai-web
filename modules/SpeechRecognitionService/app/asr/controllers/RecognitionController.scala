package asr.controllers

import java.nio.ByteBuffer
import javax.inject._

import asr.gateway.RecognitionGateway
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class RecognitionController @Inject() extends Controller {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def recognize = Action { implicit request =>
    val recognizer = new RecognitionGateway
    val rawBody = request.body.asRaw.orNull
    val bodyBytes = rawBody.asBytes(500000).orNull.asByteBuffer
    Ok(recognizer.recognize(bodyBytes))
  }
}
