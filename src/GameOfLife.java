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

    public static int neighbours(int x, int y) {
        int count = 0;

        //WRITE AN IF STATEMENT CHECKING THE BOUNDERIES

        for (int i = 0; i < 8; i++) {
            int adjx = x + dRow[i];
            int adjy = y + dCol[i];
            if (alive(adjx,adjy)){
                count++;
            }
        }
        return count;
    }
    // mock for now
    public static boolean alive ( int row, int col){
        return true;
    }
}
