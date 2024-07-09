/*************************************************************************
 *  Compilation:  javac RURottenTomatoes.java
 *  Execution:    java RURottenTomatoes
 *
 *  @author: Merin Ashokkumar, ma1945@scarletmain.rutgers.edu, ma1945
 *
 * RURottenTomatoes creates a 2 dimensional array of movie ratings 
 * from the command line arguments and displays the index of the movie 
 * that has the highest sum of ratings.
 *
 *  java RURottenTomatoes 3 2 5 2 3 3 4 1
 *  0
 *************************************************************************/

public class RURottenTomatoes {

    public static void main(String[] args) {

		// WRITE YOUR CODE HERE
		int rows = Integer.parseInt(args[0]);
		int columns = Integer.parseInt(args[1]);

		int movies[][] = new int[rows][columns]; 

		int s = 2;
		for (int i = 0; i < rows; i++) {
			for (int k = 0; k < columns; k++) {
				movies[i][k] = Integer.parseInt(args[k+s]);
			}
			s += columns;
		}

		int max = 0;
		int maxC = 0;
		for (int k = 0; k < columns; k++) {
			int cTotal = 0;
			for (int i = 0; i < rows; i++) {
				cTotal += (movies[i][k]);
				
			}
			if (cTotal > max) {
				max = cTotal;
				maxC = k;
			}
		}
		System.out.println((maxC));
	}
}
