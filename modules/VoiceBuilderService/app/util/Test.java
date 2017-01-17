package util;

import ptemplin.nlp.tts.api.SpeechBuilder;

/**
 * Created by Me on 2017-01-15.
 */
public class Test {

    public static byte[] getSpeech(String input) {
        SpeechBuilder builder = new SpeechBuilder();
        return new WavConverter().convert(builder.buildSpeech(input));
    }

}
