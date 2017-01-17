package util;

import java.io.*;

/**
 * Created by Me on 2017-01-14.
 */
public class WavConverter {

    public byte[] convert(byte[] clipData) {
        long mySubChunk1Size = 16;
        int myBitsPerSample = 16;
        int myFormat = 1;
        long myChannels = 2;
        long mySampleRate = 48000;
        long myByteRate = mySampleRate * myChannels * myBitsPerSample / 8;
        int myBlockAlign = (int) (myChannels * myBitsPerSample / 8);

        long myDataSize = clipData.length;
        long myChunk2Size = myDataSize * myChannels * myBitsPerSample / 8;
        long myChunkSize = 36 + myChunk2Size;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(new byte[] {'R', 'I', 'F', 'F'});              // 00 - RIFF
            outputStream.write(intToByteArray((int) myChunkSize), 0, 4);      // 04 - how big is the rest of this file?
            outputStream.write(new byte[] {'W', 'A', 'V', 'E'});              // 08 - WAVE
            outputStream.write(new byte[] {'f', 'm', 't', ' '});              // 12 - fmt
            outputStream.write(intToByteArray((int) mySubChunk1Size), 0, 4);  // 16 - size of this chunk
            outputStream.write(shortToByteArray((short) myFormat), 0, 2);     // 20 - what is the audio format? 1 for PCM = Pulse Code Modulation
            outputStream.write(shortToByteArray((short) myChannels), 0, 2);   // 22 - mono or stereo? 1 or 2?  (or 5 or ???)
            outputStream.write(intToByteArray((int) mySampleRate), 0, 4);     // 24 - samples per second (numbers per second)
            outputStream.write(intToByteArray((int) myByteRate), 0, 4);       // 28 - bytes per second
            outputStream.write(shortToByteArray((short) myBlockAlign), 0, 2); // 32 - # of bytes in one sample, for all channels
            outputStream.write(shortToByteArray((short) myBitsPerSample), 0, 2);  // 34 - how many bits in a sample(number)?  usually 16 or 24
            outputStream.write(new byte[] {'d', 'a', 't', 'a'});              // 36 - data
            outputStream.write(intToByteArray((int) myDataSize), 0, 4);       // 40 - how big is this data chunk
            outputStream.write(clipData);                                     // 44 - the actual data itself - just a long string of numbers

        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    private static byte[] intToByteArray(int i)
    {
        byte[] b = new byte[4];
        b[0] = (byte) (i & 0x00FF);
        b[1] = (byte) ((i >> 8) & 0x000000FF);
        b[2] = (byte) ((i >> 16) & 0x000000FF);
        b[3] = (byte) ((i >> 24) & 0x000000FF);
        return b;
    }

    // convert a short to a byte array
    public static byte[] shortToByteArray(short data)
    {
        return new byte[]{(byte)(data & 0xff),(byte)((data >>> 8) & 0xff)};
    }

}
