package database;

import java.io.*;
import java.util.*;

/**
 * @author Pim Dhaen & Roshan Shrestha
 */
public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {

    private static final String CHARSET_NAME_FOR_WRITER = "utf-8";

    /**
     * Assumes the text file has a single entry on each line.
     * Assumes each single entry (if containing sub entries) on a line is seperated by a delimiter.
     *
     * @param naamTxtBestand name of .txt file.
     * @param delimiter      specify symbol(s) separating each entry.
     * @return List of list of strings read from text file (to be parsed accordingly).
     */
    public ArrayList<ArrayList<String>> load(String naamTxtBestand, String delimiter) {

        ArrayList<ArrayList<String>> objectsInLine = new ArrayList<>();

        try {
            Scanner scannerForLines = new Scanner(new File(naamTxtBestand));

            while (scannerForLines.hasNextLine()) {
                String currentLine;
                try {   // --- End of line exception handling, returns read list when encountered ---
                    currentLine = scannerForLines.next();
                } catch (NoSuchElementException e) {
                    scannerForLines.close();
                    return objectsInLine;
                }       // ---^End of line exception handling, returns read list when encountered^---

                Scanner scannerForLine = new Scanner(currentLine);
                scannerForLine.useDelimiter(delimiter);

                ArrayList<String> tempStringList = new ArrayList<>();

                while (scannerForLine.hasNext()) {
                    tempStringList.add(scannerForLine.next());
                }
                objectsInLine.add(tempStringList);

                scannerForLine.close();
            }
            scannerForLines.close(); // Redundant if file has no empty line at the end
        } catch (FileNotFoundException e) {
            throw new DBException("Error finding file ---> \n" + e.getStackTrace());
        }
        return objectsInLine; // Redundant if file has no empty line at the end
    }

    /**
     * Will place a single entry on each line.
     * Will place each single entry (if containing sub entries) on a line seperated by a delimiter.
     *
     * @param lijstObjecten  List of list of strings read from text file (to be parsed accordingly).
     * @param naamTxtBestand name of .txt file to overwrite.
     * @param delimiter      specify symbol(s) separating each entry.
     */
    public void save(ArrayList<ArrayList<String>> lijstObjecten, String naamTxtBestand, String delimiter) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(naamTxtBestand), CHARSET_NAME_FOR_WRITER))) {

            for (ArrayList<String> entriesAsStrings : lijstObjecten) {
                for (String s : entriesAsStrings) {
                    writer.write(s);

                    if (entriesAsStrings.indexOf(s) == (entriesAsStrings.size() - 1)) {
                        writer.write("\n");
                    } else {
                        writer.write(delimiter);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new DBException("Error with charset name ---> \n" + e.getStackTrace());
        } catch (FileNotFoundException e) {
            throw new DBException("Error finding file ---> \n" + e.getStackTrace());
        } catch (IOException e) {
            throw new DBException("IO Exception ---> \n" + e.getStackTrace());
        }
    }

}
