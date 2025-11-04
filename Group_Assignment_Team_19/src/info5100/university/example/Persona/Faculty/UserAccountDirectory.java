package info5100.university.example.Persona.Faculty;

import info5100.university.example.Persona.*;
import info5100.university.example.Department.Department;
import java.util.ArrayList;

public class UserAccountDirectory {
    private Department department;
    private ArrayList<UserAccount> studentlist;
    
    public UserAccountDirectory(Department d) {
        department = d;
        studentlist = new ArrayList<>();
    }
    
    // Creates UserAccount with only Person parameter
    public UserAccount newUserAccount(Person p) {
        UserAccount sp = new UserAccount(p);  // ✅ This uses the Faculty.UserAccount constructor
        studentlist.add(sp);
        return sp;
    }
    
    public UserAccount findStudent(String id) {
        for (UserAccount sp : studentlist) {
            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null;
    }
    
    public ArrayList<UserAccount> getUserAccountList() {
        return studentlist;
    }
}
