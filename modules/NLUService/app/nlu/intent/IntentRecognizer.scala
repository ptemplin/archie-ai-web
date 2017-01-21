package nlu.intent

/**
  * Super simple intent recognition.
  */
class IntentRecognizer {

  val timeActions = Set(
    "WHAT TIME IS IT",
    "WHAT'S THE TIME",
    "WHAT IS THE TIME",
    "GIVE ME THE TIME",
    "THE TIME",
    "TIME")

  val greetingActions = Set(
    "HI",
    "HI ARCHIE",
    "HELLO",
    "HELLO ARCHIE",
    "HEY",
    "HEY ARCHIE",
    "HOWDY",
    "GREETINGS"
  )

  val smalltalkActions = Set(
    "HOW ARE YOU",
    "HOW ARE YOU TODAY",
    "HOW IS IT GOING",
    "HOW ARE YOU DOING",
    "HOW'S IT GOING",
    "HOW DO YOU FEEL"
  )

  val musicActions = Set(
    "PLAY SOME MUSIC",
    "PLAY MUSIC",
    "PLAY A SONG",
    "PLAY",
    "SING",
    "SING A SONG",
    "SING ME A SONG"
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
