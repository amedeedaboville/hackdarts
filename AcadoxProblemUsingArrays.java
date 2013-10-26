import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
 

public class Solution {

    public static void main(String[] args) {
        
        String input = "";
        try{
            BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
            input=br.readLine();
 
        }catch(IOException io){
            io.printStackTrace();
        }
         
      
        String[] stringArray = input.split(" ");
        String a = stringArray[0];
        String b = stringArray[1];
        if (b != "~") {
            String operator = stringArray[2];
            int ahex = 0;
            int bhex = 0;
            try{
                ahex = Integer.parseInt(a, 16);
                bhex = Integer.parseInt(b, 16);
            }
            catch(Exception e){
                System.out.println("ERROR");
                return;
            }
            int result = 0;
            
        }
        
   
        ahex = compute(ahex, bhex, operator);
        
        for (int i = 3; i < stringArray.length; i = i + 2) { 
            
            bhex = Integer.parseInt(stringArray[i], 16);
            operator = stringArray[i+1];
            ahex = compute(ahex, bhex, operator);       
        }
        
        System.out.println(String.format("%04X", ahex));
    }
    
    public static int compute (int a, int b, String op)
    {
        int result = 0;
        switch (op) {
                case "+":  result = a + b; break;
                case "-":  result = a - b; break;
                case "&":  result = a & b; break;
                case "|":  result = a | b; break;
                case "~":  result = ~ a; break;
                case "X":  result = a ^ b; break;
            }
        if (result > 0xFFFF) result = 0xFFFF;
        if (result < 0) result = 0;
        return result;
    }
 }