import java.awt.Color; // imports the abstract data type Color to use in program

public class PlayingWithPictures {

    // converts the argument object c of type Color into gray
    public static Color convertToGray(Color c) {
        
        // reading the red, green, and blue components of Color object
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        // compute luminence
        double lum = (0.299 * r) + (0.587 * g) + (0.114 * b);
        int roundedLum = (int)Math.round(lum); // rounding

        // create a new Color object with all components of the same value
        Color grayC = new Color(roundedLum, roundedLum, roundedLum);

        // return the object
        return grayC; 
    }

    public static void main(String[] args) {

        // reference to an object of type Picture
        // The object has a 2D array of pixels (each pixel is a color object)
        Picture picBaloo = new Picture("Baloo.jpeg");

        // invoking the show() method on the objected pointed by picBaloo
        picBaloo.show();

        int height = picBaloo.height(); // number of rows of pixels
        int width = picBaloo.width(); // number of columns of pixels

        StdOut.println("Number of pixels in the picture is " + width + " by " + height);

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {

                Color origPixelColor = picBaloo.get(col, row); // retrieve the color of pixel col, row

                Color grayPixelColor = convertToGray(origPixelColor);

                picBaloo.set(col, row, grayPixelColor); // replace the original pixel with new grayed out pixel
            }
        }
        picBaloo.show();
    }
}
