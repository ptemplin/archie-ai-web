package nlu.intent

/**
  * Super simple intent recognition.
  */
class IntentRecognizer {

  val timeActions = Set(
    "what time is it",
    "what's the time",
    "what is the time",
    "give me the time",
    "the time",
    "time")

  val greetingActions = Set(
    "hi",
    "hello",
    "hey",
    "howdy",
    "greetings"
  )

  val smalltalkActions = Set(
    "how are you",
    "how are you today",
    "how is it going",
    "how are you doing",
    "how's it going",
    "how do you feel"
  )

  val musicActions = Set(
    "play some music",
    "play music",
    "play a song",
    "play",
    "sing",
    "sing a song",
    "sing me a song"
  )

  def getIntent(input: String) : String = {
    if (timeActions(input)) {
      "GetTimeIntent"
    } else if (greetingActions(input)) {
      "GreetingIntent"
    } else if (smalltalkActions(input)) {
      "SmalltalkIntent"
    } else if (musicActions(input)) {
      "MusicIntent"
    } else {
      "UndefinedIntent"
    }
  }

}
