import java.awt.Color;
public class DrunkenTurtle {
    
    // client program
    public static void main(String[] args) {
        StdOut.print("Enter the sanctuary number of turtles: ");
        int numberOfTurtles = StdIn.readInt();

        StdOut.print("Enter the number of steps for the turtles to take: ");
        int numberOfSteps = StdIn.readInt();

        StdOut.print("Enter the step size for the turtles: ");
        double stepSize = StdIn.readDouble();

        // allocate enough space to hold all numberOfTurtles turtles
        // allocates the array to hold numberOfTurtles
        Turtle[] turtles = new Turtle[numberOfTurtles];

        // instantiate the turtles object
        // each turtle is one object, each turtle is one array index
        for (int i = 0; i < turtles.length; i++) {
            
            // position
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);

            // color
            int red = StdRandom.uniform(256); // [0,255]
            int green = StdRandom.uniform(256);
            int blue = StdRandom.uniform(256);
            Color color = new Color(red, green, blue);

            // instantiate the turtle object - object of type Turtle
            turtles[i] = new Turtle(x, y, 0.0, color);
        }

        StdDraw.setXscale(-1, 5);
        StdDraw.setYscale(-1, 5);
        
        // make each turtle take 1 step at a time for a total of numberOfSteps
        for (int s = 1; s <= numberOfSteps; s++) {
            // traverse the entire array to:
            // (1) make each turtle turn left by a random angle
            // (2) make each turtle take 1 step
            for (int t = 0; t < turtles.length; t++) {
                double angle = StdRandom.uniform(0.0, 361.0);
                turtles[t].turnLeft(angle);
                turtles[t].moveForward(stepSize);
            }
        }
    }
}
