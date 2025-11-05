/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.UserAccounts;

import java.util.ArrayList;

/**
 *
 * @author Hp
 */
public class UserAccountDirectory {
        ArrayList<UserAccount> accounts;
    
    public UserAccountDirectory() {
        accounts = new ArrayList<>();
    }
    
    public UserAccount createUserAccount(Object profile, String username, String password, String role) {
        UserAccount ua = new UserAccount(profile, username, password, role);
        accounts.add(ua);
        return ua;
    }
    
    public UserAccount authenticateUser(String username, String password) {
        for (UserAccount ua : accounts) {
            if (ua.isValidUser(username, password)) {
                return ua;
            }
        }
        return null;
    }
    
    public ArrayList<UserAccount> getAllAccounts() {
        return accounts;
    }
    
    public UserAccount findByUsername(String username) {
        for (UserAccount ua : accounts) {
            if (ua.getUsername().equals(username)) {
                return ua;
            }
        }
        return null;
    }
    
    public void deleteAccount(UserAccount account) {
        accounts.remove(account);
    }
    
    public int getCountByRole(String role) {
        int count = 0;
        for (UserAccount ua : accounts) {
            if (ua.getRole().equals(role)) {
                count++;
            }
        }
        return count;
    }


}
