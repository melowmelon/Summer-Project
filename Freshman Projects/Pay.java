// every program in java has to be inside a class
public class Pay {

    // the main method (entry point for code)
    // interpreter looks for this method to start execution of program
    public static void main(String[] args) {

        // declare variables as doubles (real numbers - decimals)
        double hoursWorked = Double.parseDouble(args[0]);
        double wage = Double.parseDouble(args[1]);

        // check for error
        if (hoursWorked < 0 || wage <= 0) {
            System.out.println("Error");
        } else {
            double salary = hoursWorked * wage;
            System.out.println("The total salary is $" + salary);
        }

    }
}
