package intenthandlers

import java.text.SimpleDateFormat
import java.util.Calendar

/**
  * Created by Me on 2017-01-17.
  */
class BasicIntentHandler {

  def handleIntent(intent: String): ClientAction = {
    if (intent == "GetTimeIntent") {
      val time = new SimpleDateFormat("hh:mm") format Calendar.getInstance.getTime
      ClientAction(time, "The time is " + time)
    } else if (intent == "GreetingIntent") {
      val response = "Hello there. How are you?"
      ClientAction(response, response)
    } else if (intent == "SmalltalkIntent") {
      val response = "I'm doing well. Thank you for asking."
      ClientAction(response, response)
    } else if (intent == "MusicIntent") {
      val response = "Playing"
      ClientAction(response, response)
    } else {
      val response = "I'm not sure how to help you with that"
      ClientAction(response, response)
    }
  }

}
