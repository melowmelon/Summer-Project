public class StarsFor {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        // prints n lines
        for (int row = 1; row <= n; row++) {

            // prints on eline of n stars
            for (int col =1; col <= n; col++) {
                    System.out.print("* ");
            }
            System.out.println(); // prints new line after line of n stars
        }
            
    }
}
