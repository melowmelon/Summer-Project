import java.awt.Color;

/*
 * Abstract Data Type (ADT) Turtle
 * Describes a turtle 
 */
public class Turtle {
    
    /****** INSTANCE VARIABLES: characteristics of the object *****/
    
    // Instance variable have values that together identify an object (instance of the class)
    
    // Instace variables are accessible from any of the methods in the class

    // Private modifies hides the instance variable from other classes
    // (x,y) coordinate
    private double x;
    private double y;

    // direction in which the turtle is facing
    private double angle;

    // turtle color to be able to see different objects on the screen
    private Color color;

    /****** CONSTRUCTORS *******/
    // Constructors have the same class name
    // Constructors create and initialize the object (initializes instance variables)
    // Constructors have no return type

    // Default constructor: no argument constructor
    public Turtle () {
        // initialize instance variables
        x = 0.0;
        y = 0.0;
        angle = 90.0; // 90 degrees
        color = new Color (0,0,0);
    }

    // 4 argument constructor
    public Turtle (double x, double y, double angle,Color color) {

        // initialize the turtle's instance variables
        // this.x is the instance variable
        // x is the constructor argument 
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.color = color;
    }

    /***** INSTANCE METHODS: operations of this data type ******/
    // Instance methods DO NOT have the word static
    // Instance methods manipulate (have access to) instance variables

    // toString() returns the string representation of the object
    public String toString() {
        String str = "Turtle (" + x + "," + y + ")" + " direction " + angle +
        " Color " + color.getRed() + "," + color.getGreen() + "," + color.getBlue();
        return str;
    }

    // turnLeft by updating the turtle's angle by delta degrees
    public void turnLeft (double delta) {
        angle += delta; // instance variable is being updated
    }

    // moves the turtle forward by stepSize
    // draw a line from old x,y to the new x,y
    public void moveForward (double stepSize) {
        double oldX = x;
        double oldY = y;
        // update instance variables: move the turtle
        x += stepSize * Math.cos(Math.toRadians(angle));
        y += stepSize * Math.sin(Math.toRadians(angle));

        // draw the path the turtle has taken
        StdDraw.setPenColor(color);
        StdDraw.line(oldX, oldY, x, y);
    }

    /* GETTER METHODS */
    // to allow other classes to view the instance variables values
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getAngle() {
        return angle;
    }
    public Color getColor() {
        return color;
    }

    /* SETTER METHODS */
    // allows other classes to change the values of the instance variables
    public void setX(double x) {
        // to differenciate between the argument value x and the instance value x
        // use this.x
        // this.x refers to the instance variable
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }
    // not writing a setColor because the turtle will die with the same color

    public boolean equals (Object other) {

        // is other of type Turtle
        if (other instanceof Turtle) {
            
            // cast other as Turtle
            Turtle o = (Turtle)other;

            // two turtles as the same if: 
            // they are at the same position,
            // they have the same color,
            // they face the same angle.
            boolean equalTurtles = this.x == o.x && this.y == o.y && this.color.equals(o.color) && this.angle == o.angle;

            return equalTurtles;

        } else {
            // other is not of type Turtle
            return false;
        }
    }

    /******* TEST CLIENT *******/
    // static methods DO NOT have access to instance variables or methods
    public static void main (String[] args) {

        // t1 is a reference to an object of type Turtle
        Turtle t1 = new Turtle(); // invoke default constructor

        // t2 is a reference to another object of type Turtle
        Turtle t2 = new Turtle(0.5, 0.5, 45, new Color(255,0,0));

        t1.turnLeft(60);// t1 turns left
        t1.moveForward(0.4);

        t2.moveForward(0.1);

        StdOut.println(t1.toString());
        StdOut.println(t2.toString());

        StdOut.println(t1.equals(t2));
    }
}
