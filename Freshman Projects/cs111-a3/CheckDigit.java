/*************************************************************************
 *  Compilation:  javac CheckDigit.java
 *  Execution:    java CheckDigit 020131452
 *
 *  @author: Merin Ashokkumar, ma1945@scarletmail.rutgers.edu, ma1945
 *
 *  Takes a 12 or 13 digit integer as a command line argument, then computes
 *  and displays the check digit
 *
 *  java CheckDigit 048231312622
 *  0
 *
 *  java CheckDigit 9780470458310
 *  0
 * 
 *  java CheckDigit 9780470454310
 *  8
 * 
 *  Print only the check digit character, nothing else.
 *
 *************************************************************************/

public class CheckDigit {

    public static void main (String[] args) {

        // WRITE YOUR CODE HERE
        long number = Long.parseLong(args[0]);
        long sum1 = 0;
        long sum2 = 0;
        long num1 = number;
        long num2 = number;
        while (num1 > 0) {
            long digit1 = num1 % 10;
            sum1 = sum1 + digit1;
            num1 /= 10;
            num1 /= 10;
        }

        num2 = num2/10;
        while (num2 > 0) {
            long digit2 = num2 % 10;
            sum2 = sum2 + digit2;
            num2 /= 10;
            num2 /= 10;
        }
        sum2 = (sum2%10)*3;

        long total = sum1 + sum2;
        System.out.println(total%10);
    }
}