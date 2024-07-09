public class Cats {
    public static void main(String[] args) {

        //declare two integer varaibles
        int anaCats;
        int ellenCats;

        //inputs to the program are passed through args
        // args(0) has the first input
        // args(1) has the second input...

        // Integer.parseInt converts String to integer
        anaCats = Integer.parseInt(args[0]);
        ellenCats = Integer.parseInt(args[1]);

        if (anaCats < 0 || ellenCats < 0) {
            System.out.println("ERROR: negative cats");
        } else {
            int totalCats = anaCats + ellenCats; // variable assignment
            System.out.println("Total number of cats: " + totalCats);
        }
    }
}
