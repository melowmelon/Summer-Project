public class Drawing {
    public static void main(String[] args) {

        double height = (Math.sqrt(3)/2)*1;
        StdDraw.line(0.0,0.0, 1.0,0.0);
        StdDraw.line(0.0,0.0, 0.5,height);
        StdDraw.line(0.5,height, 1.0,0.0);
    }
}
