public class DisplayDigits {
    public static void main(String[] args) {

        int num = 234; //Integer.parseInt(args[0]);

        for ( ; num > 0; num = num/10) {
        //while (num > 0) {
            int digit = num % 10;
            System.out.print(digit + " ");
            num = num/10; // same as digit /= 10
        }
        System.out.println();  
    }
}
