import com.objectville.model.*;

import java.util.ArrayList;

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
            distributeServices(); //first step
            distributeUtilities(); //second step
            distributeResources(i); //third step
            updateZones(); //fourth step
            collectProductions(); //fifth step
            resetZones();
        }
    }
    private void distributeServices(){
        //first scanning the grid
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                //skipping anything thats not a service provider
                if(!(grid[r][c] instanceof ServiceProvider)){
                    continue;
                }
                ServiceProvider sp = (ServiceProvider) grid[r][c];
                for (int zr = 0; zr < rows; zr++) {
                    for (int zc = 0; zc < cols; zc++) {
                        //only zones get service
                        if(!(grid[zr][zc] instanceof Zone)){
                            continue;
                        }
                        Zone zone = (Zone) grid[zr][zc];

                        int dist = MathUtils.calculateManhattanDistance(r, c, zr, zc);

                        //first checks if it is in the radius
                        //later gives the specigic service to zone
                        if (dist <= sp.getRadius()) {
                            String type = sp.getType();
                            if (type.equals("SECURITY")) {
                                zone.receiveSecurity(true);
                                PrintOutput.receivedServicePrint(
                                        zone.getClass().getSimpleName() + "", zr, zc, "security");
                            } else if (type.equals("HEALTH")) {
                                zone.receiveHealth(true);
                                PrintOutput.receivedServicePrint(
                                        zone.getClass().getSimpleName() + "", zr, zc, "health");
                            } else if (type.equals("EDUCATION")) {
                                zone.receiveEducation(true);
                                PrintOutput.receivedServicePrint(
                                        zone.getClass().getSimpleName() + "", zr, zc, "education");
                            }
                        }
                    }
                }

            }
        }
    }

    private void distributeUtilities(){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                //skipping anything thats not a utility provider
                if(!(grid[r][c] instanceof UtilityProvider)){
                    continue;
                }
                UtilityProvider up = (UtilityProvider) grid[r][c];
                //array for every provider
                boolean[][] visited = new boolean[rows][cols];

                UtilityBFS.BFS(
                        grid,
                        visited,
                        r, c,               //starting point
                        up.getCapacity(),   // exp. 100 units
                        up.getType()        // exp. "ELECTRICITY"
                );
            }
        }
    }

    private void distributeResources(int tick){

        //in the first tick skip
        if (tick == 1) return;

        ArrayList<Housing> houses = new ArrayList<>();
        ArrayList<Industrial> industrials = new ArrayList<>();
        ArrayList<Commercial> commercials = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] instanceof Housing)
                    houses.add((Housing) grid[r][c]);
                else if (grid[r][c] instanceof Industrial)
                    industrials.add((Industrial) grid[r][c]);
                else if (grid[r][c] instanceof Commercial)
                    commercials.add((Commercial) grid[r][c]);
            }
        }
        int populationReceivers = industrials.size() + commercials.size();

        //total can not be zero because if it is there is no need
        //receivers can not be zero because ot the devision there will be an error
        if (populationReceivers > 0 && totalPopulation > 0) {
            int share = totalPopulation / populationReceivers;
            for (Industrial ind : industrials) {
                ind.setReceivedPopulation(share);
                PrintOutput.resourceReceivedPrint(
                        "Industrial", ind.getRow(), ind.getCol(), share, "population");
            }
            for (Commercial com : commercials) {
                com.setReceivedPopulation(share);
                PrintOutput.resourceReceivedPrint(
                        "Commercial", com.getRow(), com.getCol(), share, "population");
            }
        }
        //
        if (!commercials.isEmpty() && totalGoods > 0) {
            int share = totalGoods / commercials.size();
            for (Commercial com : commercials) {
                com.setReceivedGoods(share);
                PrintOutput.resourceReceivedPrint(
                        "Commercial", com.getRow(), com.getCol(), share, "goods");
            }
        }

        if (!houses.isEmpty() && totalLifestyle > 0) {
            int share = totalLifestyle / houses.size();
            for (Housing h : houses) {
                h.setReceivedLifestyle(share);
                PrintOutput.resourceReceivedPrint(
                        "Housing", h.getRow(), h.getCol(), share, "lifestyle");
            }
        }
    }

    //printing info about levels
    private void updateZones(){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                //skipping anything thats not a zone
                if(!(grid[r][c] instanceof Zone)){
                    continue;
                }
                Zone zone = (Zone) grid[r][c];

                int oldLevel = zone.getLevel();
                zone.updateLevel();
                int newLevel = zone.getLevel();

                if (newLevel > oldLevel) {
                    PrintOutput.levelupPrint(
                            zone.getClass().getSimpleName()+"", r, c, oldLevel, newLevel);
                } else if (newLevel < oldLevel) {
                    PrintOutput.levelDownPrint(
                            zone.getClass().getSimpleName()+"", r, c, oldLevel, newLevel);
                }
            }
        }
    }

    private void collectProductions(){
        //setting values to zero (before the ticks they might be increased)
        totalPopulation = 0;
        totalGoods = 0;
        totalLifestyle = 0;

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                Cell cell = grid[r][c];

                //housing will provide population
                //this population will be used in industrial and commercial
                if (cell instanceof Housing) {
                    Housing h = (Housing) cell;
                    int prod = h.calculateProduction();

                    PrintOutput.generatedPrint("Housing", r, c, prod, "population");
                    totalPopulation += prod;

                    h.adjustDemand();

                    // industtrial will provide goods
                    //this will be used in commercials
                } else if (cell instanceof Industrial) {
                    Industrial ind = (Industrial) cell;
                    int prod = ind.calculateProduction();
                    if (prod > 0) {
                        PrintOutput.generatedPrint("Industrial", r, c, prod, "goods");
                        totalGoods += prod;
                    }
                    ind.adjustDemand();

                    //commercial will provide lifestyle
                    //this will be used in housing for level updates
                } else if (cell instanceof Commercial) {
                    Commercial com = (Commercial) cell;
                    int prod = com.calculateProduction();
                    if (prod > 0) {
                        PrintOutput.generatedPrint("Commercial", r, c, prod, "lifestyle");
                        totalLifestyle += prod;
                    }
                    com.adjustDemand();
                }
            }
        }

    }

    private void resetZones() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] instanceof Zone) {
                    ((Zone) grid[r][c]).reset();
                }
            }
        }
    }
}