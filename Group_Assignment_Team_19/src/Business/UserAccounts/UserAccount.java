/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.UserAccounts;

/**
 *
 * @author Hp
 */
public class UserAccount {
    String username;
    String password;
    Object profile;  // Can be StudentProfile, FacultyProfile, or AdminProfile
    String role;     // "ADMIN", "FACULTY", "STUDENT"
    
    public UserAccount(Object profile, String username, String password, String role) {
        this.profile = profile;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public boolean isValidUser(String un, String pw) {
        return username.equals(un) && password.equals(pw);
    }
    
    public String getRole() {
        return role;
    }
    
    public Object getProfile() {
        return profile;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return username + " (" + role + ")";
    }

}
