package com.objectville.model;

public class Housing extends Zone{
    private int receivedLifestyle=0;

    public Housing(int row,int col){
        super(row, col);
    }

    public int getReceivedLifestyle() {
        return receivedLifestyle;
    }

    public void setReceivedLifestyle(int receivedLifestyle) {
        this.receivedLifestyle = receivedLifestyle;
    }
     //determine to level up or down
    @Override
    public void updateLevel(){
        boolean hasUtilities=receivedElectricity>=1 && receivedWater>=1 && receivedInternet>=1;
        if (!hasUtilities){ //no benefit means level will be zero again
            level=0;
            return;
        }
        int targetLevel=1;
        //level 2 requires all services
        if (hasSecurity && hasHealth && hasEducation){
            targetLevel=2;
        }
        //level 3 requires level 2 conditions + lifestyle
        if (targetLevel==2 && receivedLifestyle >0){
            targetLevel=3;
        }
        if (targetLevel > level){ //level can move +1 or -1 per tick
            if (level<3){
                level=level+1;
            }
        } else if (targetLevel<level) {
            level=level-1;
        }//if targetLevel ==level no change
    }
    //calculates how much population will be produced
    @Override
    public int calculateProduction() {
        //find the min utility(elecricity,water,internet)--weakest will determine the output
        int minUtility = receivedElectricity;
        if (receivedWater < minUtility) {
            minUtility = receivedWater;
        }
        if (receivedInternet < minUtility) {
            minUtility = receivedInternet;
        }
        //POPULATİON CALCULATİON with current level
        //level 0 - no utility no output
        if (level==0){
            output=0;
        } else if (level==1) {   //output equals min utility
            output=minUtility;
        } else if (level==2) {   // x2 min utility
            output=2*minUtility;
        } else if (level==3) { // x2 min utility + lifestyle bonus
            output= 2*minUtility+receivedLifestyle;
        }
        return output;
    }
    //returns the map symbol for this cell type
    public  char display(){
        return 'H';
    }

    @Override
    public void reset() {
        super.reset();
        receivedLifestyle = 0;
    }

}
