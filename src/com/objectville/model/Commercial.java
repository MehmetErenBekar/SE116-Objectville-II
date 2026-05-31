package com.objectville.model;

public class Commercial extends Zone {
    //population comes from housing zones this tick
    private int receivedPopulation = 0;
    //goods comes from industrial zones this tick
    private int receivedGoods = 0;

    public Commercial(int row, int col) {
        super(row, col);
    }

    public int getReceivedPopulation() {
        return receivedPopulation;
    }

    public void setReceivedPopulation(int receivedPopulation) {
        this.receivedPopulation = receivedPopulation;
    }

    public int getReceivedGoods() {
        return receivedGoods;
    }

    public void setReceivedGoods(int receivedGoods) {
        this.receivedGoods = receivedGoods;
    }

    @Override
    public void updateLevel() {
        //Commercila needs all utilities
        boolean hasAllUtilities = receivedElectricity >= 1 && receivedWater >= 1 && receivedInternet >= 1;
        //population must be at least 1 person
        boolean hasWorkers = receivedPopulation >= 1;
        //at least 1 good
        boolean hasGoods = receivedGoods >= 1;

        //if utility is zero go back to level 0
        if (!hasAllUtilities) {
            level = 0;
            return;
        }
        //start at level 0
        int targetLevel = 0;

        //level 1 requires utilities, workers and goods
        if (hasAllUtilities && hasWorkers && hasGoods) {
            targetLevel = 1;
        }
        //level 2 requires level 1 conditions and security
        if (targetLevel == 1 && hasSecurity) {
            targetLevel = 2;
        }
        //level 3 requires level 2 conds, population and goods more than demand
        if (targetLevel == 2 && receivedPopulation > demand && receivedGoods > demand) {
            targetLevel = 3;
        }
        //move at most 1 level per tick
        if (targetLevel > level) {
            if (level < 3) {
                level=level+1;
            }
        } else if (targetLevel < level) {
            level=level-1;
        }//if target level= level no change
    }
    @Override
    public int calculateProduction(){
        //the min value will be bounder
        int minUtility =receivedElectricity;

        if (receivedWater< minUtility) {
            minUtility=receivedWater;
        }
        if (receivedInternet<minUtility){
            minUtility=receivedInternet;
        }
        //find the smaller of population and goods for level 3 bonus
        int smallerOfPopAndGoods = receivedPopulation;
        if (receivedGoods<smallerOfPopAndGoods){
            smallerOfPopAndGoods=receivedGoods;
        }
        //no utility no lifestyle
        if (level==0){
            output=0;
        //lifestyle equals minUtility
        } else if (level==1) {
            output=minUtility;
        }//level 2 --> x2 minUtility
        else if (level==2) {
            output= 2* minUtility;
        }//level 3 --> x2 minUtility + smaller of population and goods
        else if (level==3) {
            output= 2 * minUtility + smallerOfPopAndGoods;
        }
        return output;
    }
    //returns map symbol
    @Override
    public char display() {
        return 'C';
    }


    @Override
    public void reset() {
        super.reset();
        receivedPopulation = 0;
        receivedGoods = 0;
    }
}
