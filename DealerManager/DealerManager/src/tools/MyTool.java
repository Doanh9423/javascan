/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class MyTool {

    public static final Scanner SC = new Scanner(System.in);

    
    // validString is match regex
    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    //parse String to Date followed by dateFormat
    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // parse Date to String followed by dateFormat
    public static String dateToStr(Date date, String dateFormat) {
        DateFormat dFormat = new SimpleDateFormat(dateFormat);
        return dFormat.format(date);
    }

    // parse boolStr to boolean var
    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    // read String and validate is not empty
    public static String readNonBlank(String message) {
        String input = "";
        // check input and input is not empty
        do {
            System.out.print(message + ": ");
            input = SC.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    // readString by Pattern and check until match the pattern
    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        // check input is match to pattern
        do {
            System.out.println(message + ": ");
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    // read boolean from user by input String
    public static boolean readBool(String message) {
        String input;
        System.out.println(message + "[1/0-Y/n-T/F]: ");
        input = SC.nextLine().trim();
        // check input is empty
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    // read lines from file and return a list of strings
    public static List<String> readLinesFromFile(String filename) {
        List<String> ans = new ArrayList<String>();
        // check to open this file
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            // while get input has line so add data to list
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ans.add(data);
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return ans;
    }
    
    
    // write list to a file
    public static void writeFile(String filename,List list){
        // check open file existed
        try {
            FileWriter myWriter = new FileWriter(filename);
            for (Object object : list) {
                myWriter.write(object.toString());
            }
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

}
