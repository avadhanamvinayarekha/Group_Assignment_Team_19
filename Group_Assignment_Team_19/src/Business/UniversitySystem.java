/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.*;
import info5100.university.example.Persona.Faculty.*;
import info5100.university.example.CourseCatalog.*;
import info5100.university.example.CourseSchedule.*;
import Business.UserAccounts.UserAccountDirectory;
/**
 *
 * @author hp
 */
public class UniversitySystem {
    
    String name;
    Department department;
    UserAccountDirectory userAccountDirectory;
    
    public UniversitySystem(String name) {
        this.name = name;
        this.department = new Department("Information Systems");
        this.userAccountDirectory = new UserAccountDirectory();
    }
    
    public String getName() {
        return name;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }
    
    // Convenience methods
    public PersonDirectory getPersonDirectory() {
        return department.getPersonDirectory();
    }
    
    public StudentDirectory getStudentDirectory() {
        return department.getStudentDirectory();
    }
    
    public FacultyDirectory getFacultyDirectory() {
        return department.getFacultyDirectory();
    }
    
    public CourseCatalog getCourseCatalog() {
        return department.getCourseCatalog();
    }
    
}
