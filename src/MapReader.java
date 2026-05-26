import com.objectville.exception.InvalidCharException;
import com.objectville.exception.InvalidMapFormatException;
import com.objectville.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

    public static Cell[][] readFile(String fileName) {

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            System.out.println("File reading error " + e.getMessage());
            return null;
        }

        if (lines.isEmpty()) {
            return null;
        }


        int rowCount = lines.size();  // in the arraylist we put every line in the list therefore size is the the number of rows
        int colCount = lines.get(0).length(); //length of the first line will give us the column number

        for (String l : lines) {
            if (l.length() != colCount) {
                throw new InvalidMapFormatException("Map format is not at right  shape");
            }
        }

        Cell[][] mapArray = new Cell[rowCount][colCount];


        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                char c = lines.get(i).charAt(j);
                switch (c) {
                    case 'H':
                        mapArray[i][j] = new Housing();
                        break;
                    case 'I':
                        mapArray[i][j] = new Industrial();
                        break;
                    case 'C':
                        mapArray[i][j] = new Commercial();
                        break;
                    case 'P':
                        mapArray[i][j] = new PowerPlant();
                        break;
                    case 'W':
                        mapArray[i][j] = new WaterPumpingStation();
                        break;
                    case 'T':
                        mapArray[i][j] = new InternetHub();
                        break;
                        /*
                    case 'F':
                        mapArray[i][j] = new PoliceStation();
                        break;
                    case 'D':
                        mapArray[i][j] = new Hospital();
                        break;
                    case 'S':
                        mapArray[i][j] = new School();
                        break;
                    case 'R':
                        mapArray[i][j] = new Road();
                        break;
                    case 'E':
                        mapArray[i][j] = new EmptyCell();
                        break;

                         */
                    default:
                        throw new InvalidCharException("Invalid character: " + c);
                }
            }
        }
        return mapArray;
    }
}

    /*testing
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

     */