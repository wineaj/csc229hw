/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.csc229hw;

import java.util.*;

/**
 *
 * @author antho
 */
public class Csc229hw {

    public static void main(String[] args) {
        
        //The user is prompted to enter a prefix expression with spaces,
        //and the PrefixSolver method returns the result
        
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a prefix expression.\nPlease seperate all operands and operators using spaces.");
        String input = scnr.nextLine();
        System.out.println(PrefixSolver(input));
        
    }
    
    public static String[] parsed;
    
    public static boolean isNumber(String s){
        
        //Determines if a string is a number by attempting 
        //to convert it to a double
        
        if (s == null) return false;
        try {
            double num = Double.parseDouble(s);
        }
        catch (NumberFormatException nfException){
            return false;
        }
        return true;
    }
    
    public static void PrefixParser(String unparsed){
        
        //Organizes the numbers and operators from a string input into an array
        
        //Spaces are used in the input so that numbers with more than one digit 
        //can be read properly
        
        int arrayLength = 0;
        int arrayCounter = 0;
        String current = "";
        unparsed = unparsed + " ";
        for (int i = 0; i < unparsed.length(); i++) {
            if (unparsed.charAt(i) == ' '){
                arrayLength++;
            }
        }
        parsed = new String[arrayLength];
        for (int i = 0; i < unparsed.length(); i++) {
            if (unparsed.charAt(i) != ' '){
                current = current + unparsed.charAt(i);
            }
            else{
                parsed[arrayCounter] = current;
                arrayCounter++;
                current = "";
            }
        }
    }
    
    public static double PrefixSolver(String expression){
        
        //The above method is called to parse the string input
        
        //A stack is created to hold all values in the expression
        
        //Two variables are created to hold the active operands
        
        PrefixParser(expression);
        Stack<Double> prefixStack = new Stack<>();
        double left;
        double right;
        
        //The for loop iterates through the array from right to left.
        
        for (int i = parsed.length - 1; i >= 0; i--) {
            
            //If the string is determined to hold a numeric value, 
            //it is converted to a double and added to the stack
            
            if (isNumber(parsed[i])){
                double num = Double.parseDouble(parsed[i]);
                prefixStack.push(num);
            }
            
            //If the string does not contain a number,
            //it is assumed to be an operator,
            //and the two elements at the top of the stack are assigned
            //to variables before being removed.
            
            else{
                left = prefixStack.peek();
                prefixStack.pop();
                right = prefixStack.peek();
                prefixStack.pop();
                
                //The switch statment performs the appropriate operation
                //depending on which operator is detected.
                //It returns an error message if the string does not match
                //one of the four operators.
                
                switch(parsed[i]){
                    case "+":
                        prefixStack.push(left + right);
                        break;
                    case "-":
                        prefixStack.push(left - right);
                        break;
                    case "*":
                        prefixStack.push(left * right);
                        break;
                    case "/":
                        prefixStack.push(left / right);
                        break;
                    default: System.out.println("Error");
                }
            }
        }
        
        //The method returns the top value in the stack,
        //which should be the result of the expression
        //and the only value remaining.
        
        return prefixStack.peek();

    }
}
