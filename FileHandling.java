package main.filehandling;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileHandling {

    public static void main(String[] args) {

        Path path1 = Path.of("C:\\Users\\tusha\\Downloads\\download.txt");
        try {
            // reading all lines from file (good for small files)
            for (String line : Files.readAllLines(path1)) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading all lines: " + e.getMessage());
        }

        Path path2 = Path.of("C:\\Users\\tusha\\Downloads\\HPC\\HPC.log");
        // Efficient for large files, reads lazily line by line using ~8KB buffer
        // If a line is longer than 8KB, it reads additional chunks until newline
        try (Stream<String> lines = Files.lines(path2)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error streaming file lines: " + e.getMessage());
        }

        char[] buffer = new char[16384]; // 16 KB
        try (BufferedReader reader = Files.newBufferedReader(path1)) {
            //   Everytime we read a chunk in buffer array it return the number of characters we read and assign
            //   it to read variable later we provide the char array as source in first argument, then 0 as offset
            //   of that char array and the third one as number of characters we read.
            //   This goes until we reach the end of the file and read goes to -1 aka no data to read.
            int read;
            while ((read = reader.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, read));
            }
        } catch (IOException e) {
            System.err.println("Error reading with buffer: " + e.getMessage());
        }


        Path outputFile = Path.of("C:\\Users\\tusha\\Downloads\\output.txt");
        // Writing file using FileWriter
        try (FileWriter fw = new FileWriter(String.valueOf(outputFile))) {
            String data = "This is a sample text written using FileWriter.\nLine 2 of text.";
            fw.write(data);
            fw.flush();
            System.out.println("\nData written successfully to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing file with FileWriter: " + e.getMessage());
        }
    }
}
