/*************************************************************************
 *  Compilation:  javac PolygonTransform.java
 *  Execution:    java PolygonTransform
 *
 *  @author: Merin Ashokkumar, ma1945@scarletmail.rutgers.edu, ma1945
 *
 *************************************************************************/

public class PolygonTransform {


    // Returns a new array that is an exact copy of the given array. 
    // The given array is not mutated. 
    public static double[] copy(double[] array) {

	// WRITE YOUR CODE HERE
        double[] CopyArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            CopyArray[i] = array[i];
        }
        return(CopyArray);
    }
    
    // Scales the given polygon by the factor alpha. 
    public static void scale(double[] x, double[] y, double alpha) {

	// WRITE YOUR CODE HERE

        for (int i = 0; i < x.length; i++) {
            x[i] = (alpha*(x[i]));
        }
        StdOut.println();
        for (int i = 0; i < y.length; i++) {
            y[i] = (alpha*(y[i]));
        }
        StdDraw.setPenColor(StdDraw.RED);
    
    }
    
    // Translates the given polygon by (dx, dy). 
    public static void translate(double[] x, double[] y, double dx, double dy) {

	// WRITE YOUR CODE HERE

        for (int i = 0; i < x.length; i++) {
            x[i] = ((x[i])+ dx);
        }
        StdOut.println();
        for (int i = 0; i < y.length; i++) {
            y[i] = ((y[i])+ dy);
        }
    }
    
    // Rotates the given polygon theta degrees counterclockwise, about the origin. 
    public static void rotate(double[] x, double[] y, double theta) {

	// WRITE YOUR CODE HERE
        theta = Math.toRadians(theta);
        
        double[] xRotate = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            xRotate[i] = x[i];
        }

        for (int i = 0; i < x.length; i++) {
            x[i] = (((x[i])*Math.cos(theta)) - (y[i]*Math.sin(theta)));
            
        }
        for (int i = 0; i < y.length; i++) {
            y[i] = (((y[i])*Math.cos(theta)) + (xRotate[i]*Math.sin(theta)));
            
        }
    }

    // Tests each of the API methods by directly calling them. 
    public static void main(String[] args) {

	// WRITE YOUR CODE HERE
        
        StdDraw.setScale(-5.0, +5.0); 
        double[] x = {0, 1, 1, 0};
        double[] y = {0, 0, 2, 1};

        double alpha = -2;
        scale(x, y, Math.abs(alpha));

        StdOut.println();
        double dx = 2.0, dy = 1.0;
        translate(x, y, dx, dy);

        double theta = 45.0;
        rotate(x, y, theta);
    }
}
