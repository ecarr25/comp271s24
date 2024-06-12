import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.io.InputStream;

/*
NO IMPORT STATEMENTS. NO CALLS TO SYSTEM.anything, except for 
System.out.println or print or printf as needed.
 */ 
public class BookReview {

    /**
     * Establishes a Scanner on a weblink. If the connection can not be made,
     * the method returns a null. That's how we can tell something went wrong
     * and decide what to do next.
     * @param link String with link to website with text to scan
     * @return Scanner connected to the website or null if connection cannot be made
     */
    public static Scanner connectToBook(String link) {
        Scanner bookScanner;
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            bookScanner = new Scanner(stream);
        } catch (Exception e) {
            bookScanner = null;
        }
        return bookScanner;
    } // method connecttoBook

    /**
     * Count the amount of unique words 
     * @param scanner connected to a book
     * @return the number of unique words
     */

    public static int countUniqueWords(Scanner scanner) {
        int wordCount = 0;
        String[] uniqueWords = new String[50000];// sets the string value 


        // looks at the book and counts the unique words 
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            // get rid of non letters ex .(!>?) so non letter characters 
            word = word.replaceAll("[^a-zA-Z]", "");
            if (!word.isEmpty() && !contains(uniqueWords, wordCount, word)) {
                uniqueWords[wordCount++] = word;
            }
        }
        return wordCount;
    }
    /**
     * see if a given word is already present 
     * @param array of unique words 
     * @param count the current amount of unique words 
     * @param word to see if the word is present 
     * @return True if the word is there if not then return false
     */
    private static boolean contains(String[] words, int count, String word) {
        for (int i = 0; i < count; i++) {
            if (words[i].equals(word)) {
                return true;
            }
        }
        return false;
    }




    /**
     * the main method that will connect to a book and count the number of unique words and count them
     * @param args 
     */

    public static void main(String[] args) {
        // https://gutenberg.org/cache/epub/98/pg98.txt is a link
        // to the text of "Tale of Two Cities" from Project Gutenberg
        String book = "https://gutenberg.org/cache/epub/98/pg98.txt";

        //connect to the book
        Scanner scanner = connectToBook(book);

        if (scanner != null) {
            //counts the words 
            int wordCount = countUniqueWords(scanner);
            
            // prints the number of words 
            System.out.println("Here is the number of unique words" + wordCount);

            scanner.close();
        } else {
            // if it does not connect 
            System.out.println("failed to connect");
        }
    } // method main
} // class BookReview
