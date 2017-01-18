package asr.gateway;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by Me on 2017-01-16.
 */
public class RecognitionGateway {

    private final StreamSpeechRecognizer recognizer;

    public RecognitionGateway() throws IOException {
        System.out.println("Loading models...");

        Configuration configuration = new Configuration();

        // Load model from the jar
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        recognizer = new StreamSpeechRecognizer(configuration);
    }

    public String recognize(ByteBuffer input) throws IOException {
        byte[] inputBytes = new byte[input.remaining()];
        input.get(inputBytes);
        InputStream stream = new ByteArrayInputStream(inputBytes);

        // Simple recognition with generic model
        recognizer.startRecognition(stream);
        SpeechResult result;
        if ((result = recognizer.getResult()) != null) {

            System.out.format("Hypothesis: %s\n", result.getHypothesis());

            System.out.println("List of recognized words and their times:");
            for (WordResult r : result.getWords()) {
                System.out.println(r);
            }

            System.out.println("Best 3 hypothesis:");
            for (String s : result.getNbest(3))
                System.out.println(s);

        }
        recognizer.stopRecognition();

        if (result != null) {
            return result.getHypothesis();
        } else {
            return null;
        }
    }

}
