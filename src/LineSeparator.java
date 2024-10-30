import java.io.*;

public class LineSeparator {
    public static void main(String[] args) {
        String inputFilePath = "largeFile.txt";
        String evenFilePath = "evenLines.txt";
        String oddFilePath = "oddLines.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter evenWriter = new BufferedWriter(new FileWriter(evenFilePath));
             BufferedWriter oddWriter = new BufferedWriter(new FileWriter(oddFilePath))) {

            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (lineNumber % 2 == 0) {
                    evenWriter.write(line);
                    evenWriter.newLine();
                } else {
                    oddWriter.write(line);
                    oddWriter.newLine();
                }
                lineNumber++;
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
