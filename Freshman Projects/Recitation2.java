public class Recitation2 {
    public static void main(String[] args) {
        // factorials
        int n = 5;
        int val = factorial(n);
        System.out.println(val);

        // fibonacci
        int f = 10;
        int ans = fib(n);
        System.out.println(ans);
    }

    public static int factorial(int n) {
        if(n == 1) {
            return 1;
        } 

        return (n * factorial(n-1));
    }

    public static int fib(int f) {
        if(f == 1 || f == 2) {
            return 1;
        } 

        return (fib(f-1) + fib(f-2));
    }
}
