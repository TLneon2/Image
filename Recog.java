import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/* Converts different image file formats into binary and checks them
   against each other to identify if it's the same image

   Images are normally made up of 2D arrays with each pixel specifying
   the ARGB (int values). This in case, Java provides imageio and
   Bufferedimage to read [x][y] of the image and get rgb of
   each pixel automatically.

   If two images are different, how similar are they? Can we draw a correlation
   between them?
*/

public class Recog {
    
    public static void main(String[] args) {
        int i = 0;
        int j = 0;
        String s = "";
        String c = "";
        
        /* Scans the first image and stores the binary string into store */
        try{
            System.out.println("Enter the (filename).(format) : ");
            Scanner stdin = new Scanner(System.in);
            FileInputStream in = new FileInputStream(stdin.nextLine());
            StringBuilder sb = new StringBuilder();
            while((i = in.read()) !=-1) {
                sb.append(Integer.toBinaryString(i & 0xFF));
            }
            s = sb.toString();
            in.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("file not found, added .jpg or .png?");
        }
        catch(IOException a) {
            System.out.println("input is invalid");
        }
        
        /* Scans the second image and compares it against the first */
        try {
            System.out.println("Enter the other (filename).(format) : ");
            Scanner stdin = new Scanner(System.in);
            FileInputStream in = new FileInputStream(stdin.nextLine());
            StringBuilder sb = new StringBuilder();
            while((i = in.read()) != -1) {
                sb.append(Integer.toBinaryString(i & 0xFF));
            }
            c = sb.toString();
            if (c.equals(s)) {
                System.out.println("MATCH. The images are the same image in different formats.");
            }
            else {
                System.out.println("NO MATCH. The images are totally different.");
            }
            in.close();
        }        
        catch (FileNotFoundException e) {}
        catch(IOException a) {}
        
        try {
            BufferedImage img = null;
            String filename = "";
            System.out.println("Enter the image file to calc. pixel values: ");
            Scanner stdin = new Scanner(System.in);
            filename = stdin.nextLine();
            img = ImageIO.read(new File(filename));
            ReadValues(img);
        } catch (IOException e) {
            System.out.println("File not found, added .jpg or .png?");
        }
        
    }

    private static void ReadValues(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("Image total width & height: " + w + ", " + h);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.println("x,y:" + j + "," + i);
                int pixel = image.getRGB(j, i);
                printValues(pixel);
                System.out.println();
            }
        }
    }

    public static void printValues(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("pixel's argb: " + alpha + ", " + red + ", " + green + ", " + blue);
    }
    
}
