public class ArrayFunctionsCaller {
    public static void main(String[] args) {

        // create an array of size 5
        double[] testArray; // declaring a variable that refers to an array
        testArray = ArrayFunctions.createDoubleArrayAndPopulate(5);

        ArrayFunctions.printDoubleArray(testArray);

        double sum = ArrayFunctions.sumDoubleArray(testArray);
        StdOut.println(sum);

        int x = 1, y = 2;

        Recitation.METHODNAME(x, y);

        Recitation.methodName(x, y);
        
        return;

    }
}
