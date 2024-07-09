public class Variables {
    public static void main(String[] args) {

        // integer variables
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int n3 = Integer.parseInt(args[2]);
        int n4 = Integer.parseInt(args[3]);
        int n5 = Integer.parseInt(args[4]);

        // boolean variables
        boolean b1 = Boolean.parseBoolean(args[5]);

        // display
        System.out.println(n5);

        // logical operators and relational operators
        if (n1 > n2 && n1 > n3 && n1 > n4 && n1 > n5) {
            System.out.println(n1 + " > all other values");
        }

        System.out.println("Boolean value " + b1);
        boolean notB1 = !b1; // negate b1
        System.out.println("Boolean notB1 value is " + notB1);

        if (n1 > n2 && n1 >= n3) {
            System.out.println(n1 + ", " + n2 + ", " + n3);

        // comparisions is ==
            if (n1 == n2) {
                System.out.println(n1 + " is equal to " + n2);
        }
        }
    }
    
}
