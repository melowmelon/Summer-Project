/*************************************************************************
 *  Compilation:  javac RecursiveAppend.java
 *  Execution:    java RecursiveAppend
 *
 *  @author: Merin Ashokkumar, ma1945@scarletmail.rutgers.edu, ma1945
 *
 *************************************************************************/

public class RecursiveAppend {

    // Returns the original string appended to the original string n times 
    public static String appendNTimes (String original, int n) {

	// WRITE YOUR CODE HERE
    if (n == 0) {
        return original;
    } else {
        original = original + appendNTimes(original, n-1);
    }
    return original;
    }

    public static void main (String[] args) {

	// WRITE TEST CASES HERE to test your method
    String s = args[0];
    int num = Integer.parseInt(args[1]);
    
    String output = appendNTimes(s, num);
    StdOut.println(output);
    }
}
