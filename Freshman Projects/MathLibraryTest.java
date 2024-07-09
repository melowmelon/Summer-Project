public class MathLibraryTest {
    public static void main(String[] args) {

        int intValue = Integer.parseInt(args[0]);
        double doubleValue = Double.parseDouble(args[1]);

        double powIntValue = Math.pow(intValue, 3);
        double floor = Math.floor(doubleValue);
        double ceil = Math.ceil(doubleValue);

        System.out.println(intValue + ", " + powIntValue);
        System.out.println(doubleValue + ", " + floor + ", " + ceil);
    }
}
