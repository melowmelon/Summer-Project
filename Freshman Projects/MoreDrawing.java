public class MoreDrawing {

    public static void main(String[] args) {

        StdDraw.square(0.25, 0.75, 0.05);
        StdDraw.filledSquare(0.8, 0.8, 0.2);
        StdDraw.circle(0.75, 0.25, 0.2);
        double[] xd = {0.1, 0.2, 0.3, 0.2};
        double[] yd = {0.2, 0.3, 0.2, 0.1};
        StdDraw.filledPolygon(xd, yd);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.8, 0.8, "White Text");

    }
}
