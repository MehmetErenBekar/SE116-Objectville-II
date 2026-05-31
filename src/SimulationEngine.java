import com.objectville.model.Cell;

public class SimulationEngine {

    private Cell[][] grid;
    private int rows;
    private int cols;

    private int totalPopulation = 0;
    private int totalGoods = 0;
    private int totalLifestyle = 0;

    public SimulationEngine(Cell[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    public void run(int ticks){
        for(int i = 1; i <= ticks; i++){
            PrintOutput.tickPrint(i); //prints "tick" + i
            // first 5 steps
        }
    }

}