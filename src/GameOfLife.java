public class GameOfLife {
    // checks if the parcel is alive or not
    // true means it is alive false means it is dead
    public boolean apply(boolean currentStatus, int neighborCount){
        if(currentStatus){
            if(neighborCount == 2 || neighborCount == 3){
                return true;
            }else if(neighborCount > 3){
                return false;
            }
        }else if(currentStatus==false){
            if(neighborCount == 3){
                return true;
            }
        }
        return false;
    }
}
