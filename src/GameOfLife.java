public class GameOfLife {
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
