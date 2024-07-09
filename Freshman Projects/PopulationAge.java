public class PopulationAge {
    public static void main(String[] args) {

        //StdOut.print("How many people in your town: ");
        int nbrPeople = Integer.parseInt(args[0]); //StdIn.readInt();

        // generate nbrPeople age between 0 and 120
        for (int i = 0; i < nbrPeople; i++) {
            StdOut.println((int)(Math.random() * 121)); // gets random int from 0 to 120
        }
        
    }
}
