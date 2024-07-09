/*************************************************************************
 *  Compilation:  javac FindDuplicate.java
 *  Execution:    java FindDuplicate
 *
 *  @author: Merin Ashokkumar, ma1945@scarletmain.rutgers.edu, ma1945
 *
 * FindDuplicate that reads n integer arguments from the command line 
 * into an integer array of length n, where each value is between is 1 and n, 
 * and displays true if there are any duplicate values, false otherwise.
 *
 *  % java FindDuplicate 2 10 8 5 4 1 3 6 7 9
 *  false
 *
 *  % java FindDuplicate 4 5 2 1 2
 *  true
 *************************************************************************/

public class FindDuplicate {

    public static void main(String[] args) {

		//WRITE YOUR CODE HERE
		int length = args.length;

		int number[] = new int[length]; 


		for (int k = 0; k < length; k++) {
			number[k] = Integer.parseInt(args[k]);
		}

		int count = 0;
		for (int i = 0; i < length; i++) {
			for (int j = i+1; j < length; j++) {
				if (number[i] == number[j]) {
					count++;
				}
			}	
		}

		if (count == 0) {
			System.out.println("false");
		} else {
			System.out.println("true");
		}
	}
}
