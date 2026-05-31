public class MathUtils {
    public static double calculateEuclideanDistance(int x1, int y1, int x2, int y2) {
        return (double) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static int calculateManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    public boolean isItInside(int x1, int y1, int N) {
        if (x1 > N || y1 > N) {
            return false;
        } else {
            return true;
        }
    }
}
