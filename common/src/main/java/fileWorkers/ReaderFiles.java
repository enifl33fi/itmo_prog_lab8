package fileWorkers;

import general.GeneralVars;

import java.io.*;

/** Class that simplifies reading from file via InputStreamReader.
 * @author Kirill Markov
 * @version 1.0
 */
public class ReaderFiles {
    /** Line Feed ASCII code. */
    private static final int asciin = '\n';
    /** Carriage Return ASCII code. */
    private static final int asciir = '\r';

    /**
     * Reads line of text. A line is considered to be terminated by any one of a line feed ('\n'), a
     * carriage return ('\r'), a carriage return followed immediately by a line feed, or by reaching
     * the end-of-file (EOF).
     *
     * @param fileReader current InputStreamReader.
     * @return Line of text.
     * @throws IOException if an I/O error occurs.
     */
    private String readLine(BufferedReader fileReader) throws IOException {
        return fileReader.readLine();
    }

    /**
     * Reads line of text. A line is considered to be terminated by any one of a line feed ('\n'), a *
     * carriage return ('\r'), a carriage return followed immediately by a line feed, or by reaching *
     * the end-of-file (EOF). If file ended returns null.
     *
     * @param reader current InputStreamReader.
     * @return Line of text.
     */
    public String getLine(BufferedReader reader) throws IOException {
        return readLine(reader);
    }

}
