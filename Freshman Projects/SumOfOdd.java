public class SumOfOdd {
    public static void main(String[] args) {

        // Sum of odd numbers between x and y
        int x = Integer.parseInt(args[0]); // declaring x to be of type int, and storing user value into variable x
        int y = Integer.parseInt(args[1]);

        int sum = 0;

        while (x < y) {
            if (x % 2 == 1) {
                sum = sum + x; // same as sum += x
            }
            x++;
        }
        System.out.println(sum);
    }   
}
