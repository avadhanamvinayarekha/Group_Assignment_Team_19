/*
 * Digital University Management System
 * Test file to verify system initialization
 * 
 * @author tasmiya
 */
package Business;

public class TestModel {
    public static void main(String[] args) {
        UniversitySystem system = ConfigureSystem.configure();
        
        System.out.println("=== SYSTEM INITIALIZED ===");
        System.out.println("System Name: " + system.getName());
        System.out.println("Total Persons: " + system.getPersonDirectory().getPersonList().size());
System.out.println("Total Students: " + system.getStudentDirectory().getStudentList().size());
System.out.println("Total Faculty: " + system.getFacultyDirectory().getTeacherList().size());
        System.out.println("Total User Accounts: " + system.getUserAccountDirectory().getAllAccounts().size());
        System.out.println("Total Courses: " + system.getCourseCatalog().getCourseList().size());
        
        System.out.println("\n=== TEST COMPLETE ===");
    }
}
