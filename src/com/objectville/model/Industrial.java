package com.objectville.model;

public class Industrial extends Zone {
    private int receivedPopulation = 0;

    public Industrial(int row, int col) {
        super(row, col);
    }

    public int getReceivedPopulation() {
        return receivedPopulation;
    }

    public void setReceivedPopulation(int receivedPopulation) {
        this.receivedPopulation = receivedPopulation;
    }

    //determine to level up or down
    @Override
    public void updateLevel() {
        //industrial needs electricity and water ,no internet
        boolean hasElectricityAndWater = receivedElectricity >= 1 && receivedWater >= 1;
        //population check
        boolean hasWorkers = receivedPopulation >= 1;
        //0 utility ---> level 0
        if (!hasElectricityAndWater) {
            level = 0;
            return;
        }
        //start at level zero
        int targetLevel = 0;
        //level 1 requires utilities and workers
        if (hasElectricityAndWater && hasWorkers) {
            targetLevel = 1;
        }
        //level 2 requires level 1 conditions and security
        if (targetLevel == 1 && hasSecurity) {
            targetLevel = 2;
        }
        //level 3 requires level 2 and more population than demand
        if (targetLevel == 2 && receivedPopulation > demand) {
            targetLevel = 3;
        }
        //move max 1 level up or down per tick
        if (targetLevel > level) {
            if (level < 3) {
                level = level + 1;
            }
        } else if (targetLevel < level) {
            level = level - 1;
        }// if targetLevel== level no change
    }

    //goods calculation
    @Override
    public int calculateProduction() {
        int minUtility = receivedElectricity;
        if (receivedWater < minUtility) {
            minUtility = receivedWater;
        }
        //no utility no good
        if (level == 0) {
            output = 0;
        }
        //good equals to minUtility
        else if (level == 1) {
            output = minUtility;
        }
        //level 2 = x2 minUtility
        else if (level == 2) {
            output = 2 * minUtility;
        }
        //level 3 --> x2 minUtility + population bonus
        else if (level == 3) {
            output = 2 * minUtility + receivedPopulation;
        }
        return output;
    }

    //returns map symbol
    @Override
    public char display() {
        return 'I';
    }
}
