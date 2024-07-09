public class StringTypes {

    public static void main(String[] args) {

        String s1; // reference to an object of type String

        // 1. new keyword allocates memory for object
        // 2. new keyword invokes the constructor to initialize the memory
        // 3. new keyword returns the memory address of the object
        // 4. stored into s1
        s1 = new String("Rum Raisin"); 

        StdOut.println(s1); // prints the value of the object reffered by s1

        String s2 = s1.substring(4); // substring returns a portion of the string
        StdOut.println(s2);

        // how do we compare two Strings?
        // use the .equals operation to compare the content of two objects
        if (s1.equals(s2)) {
            StdOut.println("s1 has the same characters as s2");
        } else {
            StdOut.println("s1 DOES NOT have the same characters as s2");
        }

        // (1) declared a reference s3 as a reference to a String
        // (2) assigned s2's value (memory address) to s3
        String s3 = s2; 
        if (s2 == s3) {
            StdOut.println("s2 and s3 refer to the same memory address");
        } else {
            StdOut.println("s2 and s3 DO NOT refer to the same memory address");
        }

        StdOut.println("The length of " + s2 + " is " + s2.length());

        for (int i = 0; i < s2.length(); i++) {

            char letter = s2.charAt(i); // retrieving the character at index i
            StdOut.println("[" + i + "] = " + letter);
        }

        // null
        String s4 = null; // reference s4 points to null/nowhere
        StdOut.println(s4);

        // nullPointerException: error
        // s4.substring(3);

        String s5 = new String("Pistachio");
        int cmp = s1.compareTo(s5);

        if (cmp == 0) {
            StdOut.println("the same");
        } else if (cmp < 0) {
            StdOut.println("s1 comes before s5");
        } else {
            StdOut.println("s1 comes after s5");
        }

        StdOut.println(s5.contains("cake"));
        StdOut.println(s5.contains("achio"));
    }
}
