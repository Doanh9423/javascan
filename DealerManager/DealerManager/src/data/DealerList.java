/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;
import mng.LogIn;
import tools.MyTool;

/**
 *
 * @author Asus
 */
public class DealerList extends ArrayList<Dealer> {

    LogIn loginObj;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public DealerList(LogIn loginObj) {
        super();
        this.loginObj = loginObj;
    }

    public DealerList() {
    }

    // load data of Dealer from File
    private void loadDealerFromFile() {
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        //loop lines in accFile data to add one by one dealer to list
        for (String line : lines) {
            String[] parts = line.split("" + Dealer.SEPARATOR);
            Dealer d = new Dealer(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                    parts[3].trim(), MyTool.parseBool(parts[4].trim()));
            this.add(d);
        }
    }

    // get dealer file and load dealer to program
    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }

    // get list of dealers is continuing 
    public DealerList getContinuingList() {
        DealerList dl = new DealerList();
        for (Dealer t : this) {
            if (t.isContinuing() == true) {
                dl.add(t);
            }
        }
        return dl;
    }

    // get list of dealers is not continuing
    public DealerList getUnContinuingList() {
        DealerList dl = new DealerList();
        for (Dealer t : this) {
            if (t.isContinuing() == false) {
                dl.add(t);
            }
        }
        return dl;
    }

    // search dealer of list Dealer in program by ID
    private int searchDealer(String ID) {

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    //search dealer of list Dealer in prgram by ID but check and print found or not found
    public void searchDealer() {
        String ID = MyTool.readPattern("ID of search dealer", Dealer.ID_FORMAT);
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("NOT FOUND!");
        } else {
            System.out.println(this.get(pos).toString());
        }
    }

    // add dealer to dealer list in program
    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        // check ID from user input is duplicate
        do {
            ID = MyTool.readPattern("ID of new dealer: ", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer: ").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer");
        phone = MyTool.readPattern("Phone number: ", Dealer.PHONE_FORMAT);
        continuing = true;
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added.");
        changed = true;
    }

    // remove dealer of list dealer by ID
    public void removeDealer() {
        String ID = MyTool.readPattern("ID of dealer you delete: ", Dealer.ID_FORMAT);
        int pos = searchDealer(ID);
        // check if pos < 0 so it not found delear else it found and remove
        if (pos < 0) {
            System.out.println("NOT FOUND");
        } else {
            this.remove(pos);
            changed = true;
            System.out.println("DELETE SUCCESFULLY");
        }
    }

    // update dealer of list dealer by ID
    public void updateDealer() {
        System.out.println("Dealer's ID needs updating: ");
        String ID = MyTool.SC.nextLine();
        int pos = searchDealer(ID);
        // check if pos < 0 so it not found delear else it found and update
        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found!");
        } else {
            Dealer d = this.get(pos);
            String newName = "";
            System.out.print("new name, Enter for omitting: ");
            newName = MyTool.SC.nextLine().trim().toUpperCase();
            // check newName is empty
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }
            String newAddr = "";
            System.out.print("address, Enter for omitting: ");
            newAddr = MyTool.SC.nextLine().trim();
            // check newAddress is Empty
            if (!newAddr.isEmpty()) {
                d.setAddr(newAddr);
                changed = true;
            }
            String newPhone = "";
            newPhone = MyTool.readPattern("phone number, Enter for omitting: ",
                    Dealer.PHONE_FORMAT);
            if (!newPhone.isEmpty()) {
                d.setPhone(newPhone);
                changed = true;
            }
            System.out.println("UPDATED SUCCESFULLY");
        }
    }

    // print All delears
    public void printAllDealers() {
        // check list of dealers is empty
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            System.out.println(this);
        }
    }

    // print all dealer is continuing dealers
    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }
    
    // print all dealer is not continuing dealers
    public void printUnContinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    // write Dealer to file
    public void writeDealerToFile() {
        // check if changed data
        if (changed) {
            // so write to file
            MyTool.writeFile(dataFile, this);
            changed = false;
        }
    }

    public LogIn getLoginObj() {
        return loginObj;
    }

    public void setLoginObj(LogIn loginObj) {
        this.loginObj = loginObj;
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
