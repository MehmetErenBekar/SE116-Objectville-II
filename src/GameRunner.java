import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameRunner {

    public static char[][] readFile(String fileName) {

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            System.out.println("file readin error " + e.getMessage());
            return null;
        }

        if (lines.isEmpty()) {
            return null;
        }


        int rowCount = lines.size();  // in the arraylist we put every line in the list therefore size is the the number of rows
        int colCount = lines.get(0).length(); //length of the first line will give us the column number
        char[][] result = new char[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            result[i] = lines.get(i).toCharArray();
        }

        return result;
    }

    //testing
    static class Main {
        public static void main(String[] args) {
            char[][] board = readFile("map.txt");
            for (char[] row : board) {
                for (char c : row) {
                    System.out.print(c);
                }
                System.out.println();
            }
        }
    }
}