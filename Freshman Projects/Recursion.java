public class Recursion {
    
    // iterative function
    // n is the input of the function
    public static int iNumberOfDigits (int n) { // function signature
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10; // integer division; last digit is discarded
        }
        return count;
    }

    // recursive function
    public static int rNumberOfDigits (int n) {
        // write the base case first. To make the recursion stop.
        if (n == 0) {
            // base case
            // stop execution and return to caller
            return 0;
        } else {
            // recursive step OR general case
            int newN = n / 10; // reducing the problem (take last digit away)
            // calling the same function to compute number of digits on the smaller number
            int count = 1 + rNumberOfDigits(newN); 
            return count;
        }
    }

    // iterative function
    // print numbers from n down to 1
    public static void iPrintNumber (int n) {

        for ( int i = 1; i > 1; i ++) {
            StdOut.print(i + " ");
        }
    }

    // recursive function
    public static void rPrintNumber (int n) {
        if (n == 0) {
            return;
        } else {
            StdOut.print(n + " ");
            int newN = n -1; // reducing the problem
            rPrintNumber(newN); // recursive step on a smaller problem
        }
    }
    public static void rConvert (int n) {

        int quotient = n / 2;
        int remainder = n % 2;
        if (quotient == 0) {
            // base case, quotient is zero
            StdOut.print(remainder);
            return;
        } else {
            // recursive step (general case)
            rConvert(quotient);
            StdOut.print(remainder);
        }
    }
}

