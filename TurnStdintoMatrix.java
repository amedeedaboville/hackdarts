import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
 
         String input = "";
         BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        
        //Read input and get the size of the matrices
        try{input = br.readLine();}
        catch(Exception e){System.out.println("ERROR");}
        String[] sizeArray = input.split(" ");
        
        //Get the size
        int rows = Integer.parseInt(sizeArray[0]);
        int columns = Integer.parseInt(sizeArray[1]);
        

        String[] rowArray = new String[columns];
        int[][] intMountain = new int[rows][columns];
        
        for (int i = rows -1; i >= 0; i--)
        {
           try{input = br.readLine();}
           catch(Exception e){System.out.println("ERROR");}
           rowArray = input.split(" ");
            //And then put that into the first row of the mountain matrix
            for (int j = 0; j < columns; j++)
                intMountain[i][j] = Integer.parseInt(rowArray[j]);  
        }
        
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
                System.out.print(intMountain[i][j] + " ");
            System.out.println("");
            
        }
    }
}