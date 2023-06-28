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
public class Config {
    private static final String CONFIG_FILE ="DealerData/config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;
    
    
    
    public Config(){
        readData();
    }
    
    // readData and load accountFile, dealerFile, delivery File to program
    private void readData() {
        // read lines from File config
        List<String> lines = MyTool.readLinesFromFile(CONFIG_FILE);
        // loop all lines
        for (String line : lines) {
            line=  line.toUpperCase();
            String[] parts = line.split(":");
            // check lines is accountFile to be account File variable and so on
            if(line.indexOf("ACCOUN") >= 0)
                accountFile = "DealerData/" + parts[1].trim();
            else if(line.indexOf("DEALE") >= 0)
                dealerFile= "DealerData/" + parts[1].trim();
            else if(line.indexOf("DELIVER") >= 0)
                deliveryFile = "DealerData/" +parts[1].trim();
        }
    }

    public String getAccountFile() {
        return accountFile;
    }

    public void setAccountFile(String accountFile) {
        this.accountFile = accountFile;
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public void setDealerFile(String dealerFile) {
        this.dealerFile = dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }

    public void setDeliveryFile(String deliveryFile) {
        this.deliveryFile = deliveryFile;
    }
    
    
}
