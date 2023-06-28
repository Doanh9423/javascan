/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import java.util.ArrayList;
import tools.MyTool;

/**
 *
 * @author Asus
 */
public class Menu extends ArrayList<String>{

    public Menu() {
        super();
    }
    
    // Add list items to list of Menu
    public Menu(String[] items){
        super();
        for (String item : items) {
            this.add(item);
        }
    }
    
    // get choice from user input
    public int getChoice(String title){
        // loop until it true
        while(true){
            System.out.println(title);
            //loop all items in Menu
            for (int i = 0; i < this.size(); i++) {
                System.out.println((i+1)+". "+this.get(i));
            }
            //enter choice from user and check error
            try {
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(MyTool.SC.nextLine());
                return choice;
            } catch (Exception e) {
                System.out.println("Please input a number");
            }
        }
    }
    
}
