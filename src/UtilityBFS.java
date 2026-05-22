
import java.util.*;



    public  class UtilityBFS
    {



    static final int ROW = 20;
    static final int COL = 20;

    // Direction vectors
    static int dRow[] = { -1, 0, 1, 0 };
    static int dCol[] = { 0, 1, 0, -1 };

    // Function to check if a cell
// is be visited or not
    static boolean isValid(boolean vis[][], int row, int col) {

        // If cell lies out of bounds
        if (row < 0 || col < 0 ||
                row >= ROW || col >= COL)
            return false;

        // If cell is already visited
        if (vis[row][col])
            return false;

        // Otherwise
        return true;
    }

    // Function to perform the BFS traversal
    static void BFS(int grid[][], boolean vis[][], int startX, int startY, int maxRadius, int initialCapacity) {

        // Stores indices of the matrix cells

        Queue<pair > q = new LinkedList<>();
        int remainingCapacity = initialCapacity;

        // Mark the starting cell as visited
        // and push it into the queue


        q.add(new pair(startX, startY));
        vis[startX][startY] = true;

        // Iterate while the queue
        // is not empty
        while (!q.isEmpty() && remainingCapacity > 0)
        {
            pair cell = q.peek();
            int x = cell.first;
            int y = cell.second;

            System.out.print(grid[x][y] + " ");

            q.remove();

            if (isBuildingWithDemand(x, y)) {
                int demand = getBuildingDemand(x, y);
                if (remainingCapacity >= demand) {
                    remainingCapacity -= demand;
                } else {
                    remainingCapacity = 0; // Kapasite bitti
                    break;
                }
            }

            // Go to the adjacent cells
            for(int i = 0; i < 4; i++)
            {
                int adjx = x + dRow[i];
                int adjy = y + dCol[i];

                if (isValid(vis, adjx, adjy) &&  isWalkable(adjx, adjy))
                {
                    int distance = MathUtils.calculateManhattanDistance(startX, startY, adjx, adjy);

                    if (distance <= maxRadius) {
                        q.add(new pair(adjx, adjy));
                        vis[adjx][adjy] = true;
                    }
                }
            }
        }
    }
        // until classes are ready assume this
        public static boolean isBuildingWithDemand(int x, int y) {
            return false;
        }
        public static boolean isWalkable(int x, int y)
        {
            return true;
        }
        static int getBuildingDemand(int x, int y) {
        return 0;
        }

    // Driver Code
    public static void main(String[] args)
    {

        // Given input matrix
        int grid[][] = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        int startX = 10;
        int startY = 10;
        int maxRadius = 5;
        int initialCapacity = 100;

        // Declare the visited array
        boolean [][]vis = new boolean[ROW][COL];

        BFS(grid, vis, startX, startY, maxRadius, initialCapacity);
    }
}
    class pair{
    int first; // x value
    int second;
    public pair(int first, int second)
    {
        this.first = first;
        this.second = second;
    }
}