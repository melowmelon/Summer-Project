// library to manipulate arrays
public class ArrayFunctions {
    
    // creates and populates a double array of size size
    public static double[] createDoubleArrayAndPopulate(int size) {

        // creare a double array
        double[] array = new double[size];

        // populate the array
        for (int i = 0; i < size; i++) {
            array[i] = StdRandom.uniform(); // assign a random value to array[i]
        }

        // return the array
        return array;
    }

    // function that  recieves an array and prints its content
    public static void printDoubleArray(double[] array) {
        // print it
        for (int i = 0; i < array.length; i++) {
            StdOut.print(array[i] + " ");
        }
        // once the function reaches this line, it will go back to called
        // so no need for return
    }

    public static double sumDoubleArray(double[] array) {

        double sum = 0.0;
        for (int i = 0; i < array.length; i++) {
                sum += array[i];
            }
        return(sum); // return the sum to the caller
    }
}
