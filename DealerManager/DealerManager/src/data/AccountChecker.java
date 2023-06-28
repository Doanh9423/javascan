/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import tools.MyTool;

/**
 *
 * @author Asus
 */
public class AccountChecker {
    private String accFile;
    private static String SEPARATOR=",";

    public AccountChecker() {
        setupAccFile();
    }

    
    // setup location accFile
    private void setupAccFile() {
        Config cR = new Config();
        accFile = cR.getAccountFile();
    }
    
    // check account is in accFile
    public boolean check(Account acc){
        List<String> lines = MyTool.readLinesFromFile(accFile);
        //loop lines in accFile data to check one by one account
        for (String line : lines) {
            String []parts = line.split(this.SEPARATOR);
            // check account must have 3: username, password, role
            if(parts.length < 3) return false;
            // check account is in data accFile
            if(parts[0].equalsIgnoreCase(acc.getAccName())
                   && parts[1].equals(acc.getPwd())
                    && parts[2].equalsIgnoreCase(acc.getRole()))
                return true;
        }
        return false;
    }
    
   
}
