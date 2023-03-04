import java.util.Scanner;
import java.io.*;

/**
 * Class that wraps file reading and writing functions and count number of english letters
 */
public class FileHandler {
    /**
     * Keeps counted english symbols in file
     */
    private int used[];
    /**
     * Number of upper and lower case english letters
     */
    private static final int numberOfLetters = 52;

    /**
     * Default constructor what load the cycle of reading and writing in files
     */
    FileHandler() {
        String fileName;
        used = new int[numberOfLetters];

        while (true) {
            System.out.println("Enter file name for letter's counting:");
            fileName = getFileName();

            if (readFile(fileName))
                break;
        }

        while (true) {
            System.out.println("Enter file name for counting output:");
            fileName = getFileName();

            if (writeFile(fileName))
                break;
        }

    }

    /**
     * @return file name entered by user
     */
    private static String getFileName() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * @param fileName filename to read
     * @return if file was opened correctly
     */
    private boolean readFile(String fileName) {
        try(FileReader reader = new FileReader(fileName)) {
            int input;
            while ((input = reader.read()) != -1) {
                updateLetterCounter((char)input);
            }
            System.out.println("File has been successfully read");
;           return true;
        }
        catch(IOException exception) {
            System.out.println("An error occurred");
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * @param fileName filename to write
     * @return if file was opened correctly
     */
    private boolean writeFile(String fileName) {
        File userDataFile = new File(fileName);

        if(!userDataFile.exists()) {
            System.out.println("FIle was created");
        }
        try(FileWriter writer = new FileWriter(userDataFile, false)) {
            for (int i = 0; i < numberOfLetters / 2; ++i) {
                writer.append("[" + (char)(i + 'A') + "] = " + used[i] + "\n");
            }
            for (int i = numberOfLetters / 2; i < numberOfLetters; ++i) {
                writer.append("[" + (char)(i - numberOfLetters / 2 + 'a')
                        + "] = " + used[i] + "\n");
            }
            System.out.println("File has been successfully written");
            writer.flush();
            return true;
        }
        catch(IOException exception) {
            System.out.println("An error occurred");
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * @param input char to check if it's from english alphabet
     */
    private void updateLetterCounter(char input) {
        if (input >= 'A' && input <= 'Z') {
            used[input - 'A']++;
        }
        else if (input >= 'a' && input <= 'z') {
            used[input - 'a' + 26]++;
        }
    }
}
