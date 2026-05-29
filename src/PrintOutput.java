public class PrintOutput {

    //Tick 4
public static void tickPrint(int tick){
    System.out.println("Tick " + tick);
}


     // House at (0,14) received education service
    public static void receivedServicePrint(String zone, int row, int col, String service) {
        System.out.println(zone + " at (" + row + "," + col + ") received " + service + " service");}


      // Commercial at (2,5) received 1 internet
     public static void utilityReceivedPrint(String zone,int row, int col, int amount, String utility) {
        System.out.println(zone + " at (" + row + ","+ col + ") received " + amount + " " + utility);}


      // House at (0,14) received 2 lifestyle
    public static void resourceReceivedPrint(String zone, int row, int col,int amount, String resource) {
        System.out.println(zone + " at (" + row + "," + col + ") received " + amount + " " + resource);
    }


    // House at (5,7) generated 1 population
     public static void generatedPrint(String zone, int row, int col, int amount, String resource) {
        System.out.println(zone + " at (" + row + "," + col + ") generated " + amount + " " + resource);
    }


    // Industrial at (2,9) levels up from 1 to 2
    public static void levelupPrint(String zone,int row, int col, int first, int second) {
        System.out.println(zone + " at (" + row + "," + col + ") levels up from " + first + " to " + second);
    }


    // Industrial at (2,9) levels down from 2 to 1
    //also can be used for utility lost (levels down to 0)
    public static void levelDownPrint(String zone, int row, int col, int first, int second){
        System.out.println(zone + " at (" + row + "," + col + ") levels down from " + first + " to " + second);
    }


}
