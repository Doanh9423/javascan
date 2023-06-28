/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import tools.MyTool;

/**
 *
 * @author Asus
 */
public class LogIn {

    private Account acc = null;

    public LogIn() {
    }

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        String username = MyTool.readNonBlank("Enter username");
        String password = MyTool.readNonBlank("Enter password ");
        String role = MyTool.readNonBlank("Enter role");
        Account account = new Account();
        account.setAccName(username);
        account.setPwd(password);
        account.setRole(role);
        return account;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Account acc = null;
        boolean cont = false;
        boolean valid = false;
        do {         
            cont = false;
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTool.readBool("Invalid account- Try again?");
            }
            if(!valid && !cont)System.exit(0);
        } while (cont);
        LogIn logInObj = new LogIn(acc);
        if(acc.getRole().equalsIgnoreCase("ACC-1")){
            String[] options = {
                "Add new dealer","Search a dealer",
                "Remove a dealer","Update a dealer",
                "Print all dealers","Print continuing delears",
                "Print UN-continuing dealers","Write to files"
            };
            Menu mnu = new Menu(options);
            DealerList dList = new DealerList(logInObj);
            dList.initWithFile();
            int choice =0;
            do {                
                choice = mnu.getChoice("Managing dealers");
                switch(choice){
                    case 1: dList.addDealer();break;
                    case 2: dList.searchDealer();break;
                    case 3: dList.removeDealer();break;
                    case 4: dList.updateDealer();break;
                    case 5: dList.printAllDealers();break;
                    case 6: dList.printContinuingDealers();break;
                    case 7: dList.printUnContinuingDealers();break;
                    case 8: dList.writeDealerToFile();break;
                    default:
                        if(dList.isChanged()){
                            boolean res = MyTool.readBool("Data changed. Write to file? ");
                            if(res == true)dList.writeDealerToFile();
                        }
                }
            } while (choice > 0 && choice < mnu.size()+1);
            System.out.println("Bye.");
        }
    }

}
