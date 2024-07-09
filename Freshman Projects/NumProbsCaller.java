public class NumProbsCaller {
    
    public static void main(String[] argd) { // function signature
        
        // Call the "numberOfDigits" function 
        int nbr = NumProbs.numberOfDigits(123456);
        StdOut.println(nbr);

        nbr = NumProbs.numberOfDigits(546);
        StdOut.println(nbr);

        int fact = NumProbs.factorial(5);
        NumProbs.myprint("" + fact);
    }
}
