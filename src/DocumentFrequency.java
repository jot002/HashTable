/*
 * NAME: Jonathan Tran
 * PID: A15967290
 */

import java.io.*;
import java.util.Scanner;

/**
 * TODO
 *
 * @author Jonathan Tran
 * @since 11/24/2021
 */
public class DocumentFrequency {

    private int numDoc;
    private Scanner scanner;

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

    public int numDocuments() {
        return numDoc;
    }

    public int query(String word) {
        HashTable table1 = new HashTable();
        while (scanner.hasNextLine()) {
            String words[] = scanner.nextLine().split(" ");
            for (String elem : words) {
                if (elem.toLowerCase().equals(word.toLowerCase())) {
                    this.numDoc++;
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
