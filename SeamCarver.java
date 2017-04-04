/******************************************************************************
 *  Name:    Tyler Kaye
 *  NetID:   tkaye
 *  Precept: P05
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  This class is a Seam Carver which calculates the seam of 
 *  lowest energy either vertically or horizontally and then removes it.
 ******************************************************************************/
// imports
//import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {
    private Picture pic;  
    
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture)
    {
        if (picture == null) 
            throw new NullPointerException("Parameter cannot be null");
        
        // create defensive copy 
        Picture newPic = new Picture(picture.width(), picture.height());
        for (int j = 0; j < picture.height(); j++)
        {
            for (int i = 0; i < picture.width(); i++)
            {
                newPic.set(i, j, picture.get(i, j));   
            }
        }
        pic = newPic;
    }
    // current picture
    public Picture picture()
    {
        // return new picture
        Picture newPic = new Picture(pic.width(), pic.height());
        for (int j = 0; j < pic.height(); j++)
        {
            for (int i = 0; i < pic.width(); i++)
            {
                newPic.set(i, j, pic.get(i, j));   
            }
        }
        return newPic;
    }
    // width of current picture
    public int width()
    {
        return pic.width();
    }
    // height of current picture
    public int height() 
    {
        return pic.height();
    }
    // energy of pixel at column x and row y
    public double energy(int x, int y) 
    {
        // check for null pointers 
        if (x < 0 || x >= this.width())
            throw new IndexOutOfBoundsException("Index must be within width range");
        if (y < 0 || y >= this.height())
            throw new IndexOutOfBoundsException("Index must be within height range");
        
        // calculate the energy of pixel
        
        
        double xSQ;
        if (pic.width() < 2) xSQ = 0;
        else 
        {
            Color c1, c2;
            if (x == 0) 
            {
                c1 = pic.get(x+1, y);
                c2 = pic.get(pic.width()-1, y);
            }
            else if (x == pic.width() - 1)
            {
                c1 = pic.get(0, y);
                c2 = pic.get(x - 1, y);
            }
            else 
            {
                c1 = pic.get(x-1, y);
                c2 = pic.get(x+1, y);
            }
            xSQ = colorToDouble(c1, c2);
        }
        
        double ySQ;
        
        if (pic.height() < 2) ySQ = 0;
        
        else 
        {
            Color c1, c2;
            if (y == 0) 
            {
                c1 = pic.get(x, y+1);
                c2 = pic.get(x, pic.height() - 1);
            }
            else if (y == pic.height() - 1)
            {
                c1 = pic.get(x, 0);
                c2 = pic.get(x, y - 1);
            }
            else 
            {
                c1 = pic.get(x, y + 1);
                c2 = pic.get(x, y - 1);
            } 
            ySQ = colorToDouble(c1, c2);
        }
        
        //System.out.println( Math.sqrt(xSQ + ySQ) );
        return Math.sqrt(xSQ + ySQ);
    }
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() 
    {
        // invert the picture
        invertPic();
        // find the path using findVerticalSeam()
        int[] path = findVerticalSeam();
        // invert back 
        invertPic();
        // return the path
        return path;
    }
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() 
    {
        // energies holds the energy of each pixel
        double[][] energies = new double[pic.width()][pic.height()];
        // distTo holds the minDistance to the pixel spot
        double[][] distTo = new double[pic.width()][pic.height()];
        // edgeTo holds the edge that distTo comes from
        int[][] edgeTo = new int[pic.width()][pic.height()];
        
        // iterate through each pixel
        for (int i = 0; i < pic.width(); i++)   
        {
            for (int j = 0; j < pic.height(); j++)
            {
                // set energy 
                energies[i][j] = energy(i, j);
                
                // if first row --> distTo = energy 
                if (j == 0) distTo[i][j] = energies[i][j];
                // otherwise distTo = Positive Infinity
                else  distTo[i][j] = Double.POSITIVE_INFINITY;
                
            }
        }
        
        // iterate by row --> skip last row
        for (int j = 0; j < pic.height() - 1; j++)
        {
            // then by col
            for (int i = 0; i < pic.width(); i++)
            {
                // THIS IS ALL BASICALLY RELAXING
                // CHECK TO MAKE SURE CORRECT VERTEX IS POSSIBLE 
                // THEN RELAX IT
                double tDist = distTo[i][j];
                
                // relax edge below
                if (distTo[i][j+1] > energies[i][j+1] + tDist)
                {
                    distTo[i][j+1] = energies[i][j+1] + tDist;
                    edgeTo[i][j+1] = i;
                }
                // relax lower left edge if possible    
                if (i != 0)
                {
                    if (distTo[i-1][j+1] > energies[i-1][j+1] + tDist)
                    {
                        distTo[i-1][j+1] = energies[i-1][j+1] + tDist;
                        edgeTo[i-1][j+1] = i;
                    }
                }
                //relax lower right edge if possible 
                if (i != pic.width() - 1)
                {
                    if (distTo[i+1][j+1] > energies[i+1][j+1] + tDist)
                    {
                        distTo[i+1][j+1] = energies[i+1][j+1] + tDist;
                        edgeTo[i+1][j+1] = i;
                    }
                }
                
            }
        }
        
        // convert into correct array
        int minBot = 0;
        int jd = pic.height() - 1;
        
        // find the distTo on the bottom row with the smallest value 
        for (int i = 1; i < pic.width(); i++)
        {
            // find lowest value 
            if (distTo[i][jd] < distTo[minBot][jd])
            {
                minBot = i;   
            }
        }
        
        // create correct path
        int[] path = new int[pic.height()];
        path[pic.height() - 1] = minBot;
        
        // fill it in from the back 
        for (int j = pic.height() - 2; j >= 0; j--)
        { 
            path[j] = edgeTo[path[j+1]][j+1];
        }  
        
        
        
        // return 
        return path;
    }
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam)
    {
        if (seam == null)
            throw new NullPointerException("Parameter cannot be null");
        if (seam.length != pic.width())
            throw new IllegalArgumentException("Array must have correct length");
        if (pic.height() == 1)
            throw new IllegalArgumentException("Cannot remove seam when height = 1");
        boolean first = true;
        int last = 0;
        // check to make sure that in the array no entry is more than 1 away from 
        // the previous entry 
        for (int i : seam)
        {
            
            // if first iteration --> set last and iterate
            if (first) first = false;
            // otherwise throw exception if i is more than 1 away from last
            // if not reset last
            else 
            {
                if (i - last > 1 || last - i > 1) 
                    throw new IllegalArgumentException("Seam indices too far away");
            }
            last = i;
            
            if (i < 0 || i >= pic.height())
                throw new IllegalArgumentException("Seam indices not valid");
        }  
        
        // create new picture and fill it with correct pixels 
        Picture newPic = new Picture(pic.width(), pic.height() - 1);
        
        // fill it with correct pixels
        // exact same as removeVerticalSeasm
        for (int i = 0; i < pic.width(); i++)
        {
            int currIndex = 0;
            int skipIndex = seam[i];
            for (int j = 0; j < pic.height(); j++)
            {
                if (j != skipIndex) 
                {
                    newPic.set(i, currIndex, pic.get(i, j));
                    currIndex++;
                }
            }
        }
        pic = newPic;
    }
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam)
    {
        if (seam == null)
            throw new NullPointerException("Parameter cannot be null");
        if (seam.length != pic.height())
            throw new IllegalArgumentException("Array must have correct length");
        if (pic.width() == 1)
            throw new IllegalArgumentException("Cannot remove seam when width = 1");
        
        boolean first = true;
        int last = 0;
        // check to make sure that in the array no entry is more than 1 away from 
        // the previous entry 
        for (int i : seam)
        {
            
            // if first iteration --> set last and iterate
            if (first) first = false;
            // otherwise throw exception if i is more than 1 away from last
            // if not reset last
            else 
            {
                if (i - last > 1 || last - i > 1) 
                    throw new IllegalArgumentException("Seam indices too far away");
            }
            last = i;
            
            if (i < 0 || i >= pic.width())
                throw new IllegalArgumentException("Seam indices not valid");
        } 
        
        // create a new picture
        Picture newPic = new Picture(pic.width() - 1, pic.height());
        
        // iterate through each row 
        for (int j = 0; j < pic.height(); j++)
        {
            int currIndex = 0;
            int skipIndex = seam[j];
            // iterate through each column
            for (int i = 0; i < pic.width(); i++)
            {
                // put in each column that is not the skip index 
                // 1 col less
                if (i != skipIndex) 
                {
                    newPic.set(currIndex, j, pic.get(i, j));
                    currIndex++;
                }
            }
        }
        
        pic = newPic;
    }
    
    public static void main(String[] args)
    {
       if (args.length != 3) {
            System.out.println("Usage:\njava ResizeDemo [image filename] [num columns to remove] [num rows to remove]");
            return;
        }

        Picture picture = new Picture(args[0]);
        int picWidth = picture.width();
        int picHeight = picture.height();
        int removeColumns = Integer.parseInt(args[1]);
        int removeRows = Integer.parseInt(args[2]);

        if (removeColumns < 0 || removeRows < 0) { 
            System.out.println("ARGUMENTS CANNOT BE NEGATIVE"); 
            return;
        } 

        System.out.printf("%d-by-%d image\n", picWidth, picHeight);
        SeamCarver sc = new SeamCarver(picture);

        for (int i = 0; i < removeRows; i++) {
            int[] horizontalSeam = sc.findHorizontalSeam();
            sc.removeHorizontalSeam(horizontalSeam);
        }

        for (int i = 0; i < removeColumns; i++) {
            int[] verticalSeam = sc.findVerticalSeam();
            sc.removeVerticalSeam(verticalSeam);
        }

        System.out.printf("new image size is %d columns by %d rows\n", sc.width(), sc.height());
        String[] fileN = args[0].split("\\.");
        String name = fileN[0] + sc.picture().width() + "x" + sc.picture().height() + ".jpg";  
        sc.picture().save(name);
        return;
    }
    
    // helper class to find differential between 2 colors
    private double colorToDouble(Color c1, Color c2)
    {
        // get the difference in colors
        double dRed = c1.getRed() - c2.getRed();  
        double dGreen = c1.getGreen() - c2.getGreen();
        double dBlue = c1.getBlue()  - c2.getBlue();
        
        // square the differences
        dRed = dRed * dRed;
        dBlue = dBlue * dBlue; 
        dGreen = dGreen * dGreen;
        
        // add them and return 
        return dRed + dBlue + dGreen;
    } 
    
    // helper methods to convert 2d to 1d
    private int twoToOne(int i, int j)
    {
        return j * pic.width()  + i;    
    }
    
    private int oneToRow(int num)
    {
        return num / pic.width();
    }
    
    private int oneToCol(int num)
    {
        return num % pic.width();
    }
    
    // helper method to invert the picture
    private void invertPic()
    {
        // go through and just swap i,j with j,i
        Picture npic = new Picture(pic.height(), pic.width());
        for (int i = 0; i < pic.width(); i++)
        {
            for (int j = 0; j < pic.height(); j++)
            {
                npic.set(j, i, pic.get(i, j));   
            }
        }
        pic = npic;
    }
    
}