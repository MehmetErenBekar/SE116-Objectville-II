public class Main {
    public static void main(String[] args){



        if (args.length != 2){
     System.out.println("Invalid input.");
     return;}

 String fileName = args[0];
 int tick;

//tick control

    try {
        tick = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
        System.out.println("Tick must be integer");
        return;}

        if (tick <= 0) {
        System.out.println("Tick must be positive");
        return;}

        Cell[][] map = MapReader.readFile(fileName);

    }
}
