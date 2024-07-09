public class GuessingGame {
    public static void main(String[] args) {

        int rNum = (int)(1000*Math.random()+1);
        int guess = 0;

        
        while (guess != rNum) {
            StdOut.print("\nEnter a number: ");
            guess = StdIn.readInt();

            if (guess < rNum) {
                StdOut.println("Your guess is too low");
                guess = StdIn.readInt();
            }
            if (guess > rNum) {
                StdOut.println("Your guess is too high");
                guess = StdIn.readInt();
            }
            if (guess == rNum) {
                StdOut.println("You guessed the correct number: " + rNum); 
            }
        }
        
    }
}
