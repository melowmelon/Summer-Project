public class Classroom {
    public static void main(String[] args) {
        
        //System.out.print("How many students in this class: ");
        int nbrStudents = StdIn.readInt(); // reads integer from keyboard

        
        String[] InLine = new String[nbrStudents];

        for (int i = 0; i < InLine.length; i++) {
            //System.out.print("Enter student " + i + " name: ");
            InLine[i] = StdIn.readString();
        }

        // print students in line using the enhanced for loop (foreach loop)
        for (String stu: InLine) {
            System.out.print(stu + " - ");
        }
        System.out.println();

        //System.out.print("Number of classroom rows: ");
        int rows = StdIn.readInt();
        //System.out.print("Number of columns: ");
        int columns = StdIn.readInt();

        // create a 2D array
        String[][] room = new String[rows][columns];
        
        // add students from line into room in row-major order
        int i = 0;
        for (int r = 0; r < room.length; r++) {
            for (int c = 0; c < room[r].length; c++) {
                
                if (i == InLine.length) {
                    // all students from the line are seated
                    break; // break out of the immediate loop
                }
                room[r][c] = InLine[i];
                i++;
            }
            
        }

        // students leave the classroom in a single line
        // Fill up the line column-wise
        i = 0;
        for (int c = 0; c < room[0].length; c++) {

            for (int r = 0; r < room.length; r++) {

                if (i == InLine.length) {
                    break;
                }
                if (room[r][c] == null) {

                }else {
                    InLine[i] = room[r][c];
                    i++;
                }
            }
        }

        StdOut.println("Students are leaving column-wise: ");
        for (String stu : InLine) {
            StdOut.print(stu + " - ");
        }

    }
}
