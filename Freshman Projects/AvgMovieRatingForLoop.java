public class AvgMovieRatingForLoop {
    public static void main(String[] args) {
       
        // args stores command line arguments
        // args.length tells us number of arguments in command line

        int numFriends = args.length; // number of people that went to the movies
        int sum = 0;

        for (int i = 0; i < numFriends; i++) {
            // rating is only visible inside the while loop
            int rating = Integer.parseInt(args[i]);
            sum = sum + rating; // same as sum += rating
            i++;
        }
        
        // integer divided by integer results in an integer
        double avg = (sum * 1.0)/numFriends;

        // cast numerator as double and then divide
        double avg2 = ((double)sum)/numFriends;

        System.out.println("The movie rating average is " + avg);
        System.out.println("The movie rating average is " + avg2);
    }
}
