import com.objectville.model.Cell;
import com.objectville.model.EmptyCell;
import com.objectville.model.Housing;

public class GameOfLife {

    // Direction vectors
    static int dRow[] = {-1, -1, -1,  0, 0,  1, 1, 1};
    static int dCol[] = {-1,  0,  1, -1, 1, -1, 0, 1};

    // checks if the parcel is alive or not
    // true means it is alive false means it is dead
    public static boolean apply(boolean currentStatus, int neighborCount) {
        if (currentStatus) {
            if (neighborCount == 2 || neighborCount == 3) {
                return true;
            } else if (neighborCount > 3) {
                return false;
            }
        } else {
            if (neighborCount == 3) {
                return true;
            }
        }
        return false;
    }

    public static int neighbours(Cell[][] grid, int x, int y) {
        int count = 0;
        int maxRows = grid.length;
        int maxCols = grid[0].length;



        for (int i = 0; i < 8; i++) {
            int adjx = x + dRow[i];
            int adjy = y + dCol[i];

            //checking bounderies
            if (adjx >= 0 && adjx < maxRows && adjy >= 0 && adjy < maxCols) {
                if (alive(grid, adjx, adjy)){
                    count++;
                }
            }
        }
        return count;
    }
    //alive == not a empty cell
    public static boolean alive (Cell [][] grid ,int row, int col){
        if(grid[row][col] instanceof EmptyCell){
            return false;
        }
        return true;
    }

    //returns the next grid
    public Cell[][] nextTick(Cell[][] original_grid) {
        int rows = original_grid.length;
        int cols = original_grid[0].length;

        Cell[][] nextGrid = new Cell[rows][cols];

        for (int i = 0; i < original_grid.length; i++) {
            for (int j = 0; j < original_grid[0].length; j++) {
                int neighbours = neighbours(original_grid, i, j);
                boolean current_status = alive(original_grid, i, j);
                boolean next_status = apply(alive(original_grid,i,j),neighbours);


                if(next_status){
                    if(current_status){
                        nextGrid[i][j] = original_grid[i][j];
                    }else{
                        nextGrid[i][j] = new Housing(i,j);
                    }
                }else{
                    nextGrid[i][j] = new EmptyCell(i,j);
                }
            }
        }
        return nextGrid;
    }
}
