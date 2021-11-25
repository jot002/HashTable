/*
 * NAME: Jonathan Tran
 * PID: A15967290
 */

import java.io.*;
import java.util.Scanner;

/**
 * Document frequency of a word refers to the number of documents that
 * contain this word. This program will take a path to a text file as
 * an argument and read all documents in the file.
 *
 * @author Jonathan Tran
 * @since 11/24/2021
 */
public class DocumentFrequency {

    private int numDoc;
    private Scanner scanner;

    /**
     * This method takes a path to a text file that satisfies the
     * assumptions we discussed. It reads the file, then initializes
     * and populates any instance variables.
     * @param path path of the file that is read
     * @exception IOException if given file can't be found or read
     */
    public DocumentFrequency(String path) throws IOException{
        // open and read file
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            this.numDoc = 0;
        }
        catch (IOException ioe) {
            return;
        }
    }

    /**
     * This method returns the number of documents stored in this object.
     * @return an integer representing the number of documents stored
     */
    public int numDocuments() {
        return numDoc;
    }

    /**
     * This method takes a word and returns the document frequency of
     * this word. In other words, it returns how many lines in the
     * file contain this word.
     * @param word word that will be checked for frequency
     * @return thee amount of lines that contains the word
     */
    public int query(String word) {
        while (scanner.hasNextLine()) {
            String words[] = scanner.nextLine().split(" ");
            for (String elem : words) {
                if (elem.toLowerCase().equals(word.toLowerCase())) {
                    this.numDoc++;
                    // skips to next line
                    break;
                }
            }
        }
        return this.numDoc;
    }

    public static void main(String[] args) throws IOException {
        DocumentFrequency test = new DocumentFrequency("./src/files/test.txt");
        System.out.println(test.query("the"));
    }
}
