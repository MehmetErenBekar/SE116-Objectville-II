import com.objectville.model.*;
import java.util.*;

public class UtilityBFS {

    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};

    //checks the bounderies and whether it is already visited or not
    static boolean isValid(Cell[][] grid, boolean[][] vis, int row, int col) {
        int maxRow = grid.length;
        int maxCol = grid[0].length;
        if (row < 0 || col < 0 || row >= maxRow || col >= maxCol)
            return false;
        if (vis[row][col])
            return false;
        return true;
    }

    //empty cells are not walkable
    static boolean isWalkable(Cell c) {
        if (c instanceof EmptyCell)
            return false;
        return true;
    }

    static void BFS(Cell[][] grid, boolean[][] vis, int startX, int startY, int initialCapacity, String utilityType) {

        Queue<int[]> q = new LinkedList<>();
        int remainingCapacity = initialCapacity;

        //adding the starterpoint into the queue
        q.add(new int[]{startX, startY});
        vis[startX][startY] = true;

        while (!q.isEmpty() && remainingCapacity > 0) {
            //getting the cell infront of the queue
            int[] cell = q.poll();  //poll() = peek()+remove()
            int x = cell[0];
            int y = cell[1];
            Cell current = grid[x][y];

            // If zone, give it utility
            if (current instanceof Zone) {
                Zone zone = (Zone) current;
                int demand = zone.getDemand();

                //with this line the cell will get either the demand or whatever is left
                int given = Math.min(demand, remainingCapacity);
                remainingCapacity -= given;

                if (utilityType.equals("ELECTRICITY")) {
                    zone.setReceivedElectricity(zone.getReceivedElectricity() + given);
                    PrintOutput.utilityReceivedPrint(zone.display()+"", x, y, given, "electricity");
                } else if (utilityType.equals("WATER")) {
                    zone.setReceivedWater(zone.getReceivedWater() + given);
                    PrintOutput.utilityReceivedPrint(zone.display()+"", x, y, given, "water");
                } else if (utilityType.equals("INTERNET")) {
                    zone.setReceivedInternet(zone.getReceivedInternet() + given);
                    PrintOutput.utilityReceivedPrint(zone.display()+"", x, y, given, "internet");
                }
            }
            //checking neighbors
            // Roads just pass through - expand neighbors without consuming

            for (int i = 0; i < 4; i++) {
                int adjx = x + dRow[i];
                int adjy = y + dCol[i];

                if (isValid(grid, vis, adjx, adjy) && isWalkable(grid[adjx][adjy])) {
                    vis[adjx][adjy] = true;
                    q.add(new int[]{adjx, adjy});
                }
            }
        }
    }
}