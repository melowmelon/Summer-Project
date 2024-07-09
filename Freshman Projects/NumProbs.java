// this is a library
// a collection of functions that provide functionalities

public class NumProbs {
    
    public static int factorial (int number) {
        int result = 1;
        for (int n = 2; n <= number; n++) {
            result *= n;
        }
        return result; // returning the value stored in result
    }
    
    // function that doesn't return a value
    public static void myprint(String value) {
        StdOut.println(value);
        return;
    }

    //function to count the number of digits in an integer
    // input is a number
    public static int numberOfDigits(int number) {

        // function's body
        int count = 0;
        while (number > 0) {
            count++;

            // integer division
            number /= 10; // removes last digit of number
        }
        // return number of digits
        return count;
    }
}
