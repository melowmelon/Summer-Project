public class CDN {
    public static void main(String[] args) {
        long sum1 = 0;
        long sum2 = 0;

        long number = Long.parseLong(args[0]);
        for (long newnum1 = number; newnum1>0; newnum1/=100) {
            sum1 = sum1 + (newnum1%10);
        }
        sum1 %= 10;

        for (long newnum2 = number/10; newnum2>0; newnum2/=100) {
            sum2 = sum2 + (newnum2%10);
        }
        sum2 = (sum2%10)*3;

        long total = (sum1 + sum2)%10;
        System.out.println(total);
    }
}
