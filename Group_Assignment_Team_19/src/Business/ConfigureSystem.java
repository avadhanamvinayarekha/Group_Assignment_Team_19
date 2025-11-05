/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;
import Business.Profiles.AdminProfile;
import Business.UserAccounts.*;
import Business.UserAccounts.UserAccountDirectory;
import info5100.university.example.Persona.*;
import info5100.university.example.Persona.Faculty.*;
import info5100.university.example.CourseCatalog.*;
import info5100.university.example.CourseSchedule.*;
import info5100.university.example.Department.*;
import java.util.Random;

/**
 *
 * @author anusha
 */
public class ConfigureSystem {
    
    private static final String[] FIRST_NAMES = {
        "John", "Jane", "Michael", "Emily", "David", "Sarah", "Robert", "Lisa",
        "James", "Mary", "William", "Jennifer", "Richard", "Linda", "Thomas"
    };
    
    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
        "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez"
    };
    
    private static final String[] COURSE_NAMES = {
        "Application Engineering and Development",
        "Data Structures and Algorithms",
        "Database Management Systems",
        "Web Development",
        "Software Engineering"
    };
    
    private static final String[] COURSE_NUMBERS = {
        "INFO5100", "INFO6205", "INFO6210", "INFO6250", "INFO5500"
    };
    
    private static Random random = new Random();
        public static UniversitySystem configure() {
        UniversitySystem system = new UniversitySystem("Northeastern University");
        
        Department dept = system.getDepartment();
        PersonDirectory personDir = dept.getPersonDirectory();
        StudentDirectory studentDir = dept.getStudentDirectory();
        FacultyDirectory facultyDir = dept.getFacultyDirectory();
        UserAccountDirectory userDirectory = system.getUserAccountDirectory();
        // ===== CREATE 1 ADMIN =====
        Person adminPerson = personDir.newPerson("Admin User");
        adminPerson.setEmail("admin@northeastern.edu");
        adminPerson.setContactInfo("617-555-0001");
        AdminProfile adminProfile = new AdminProfile(adminPerson);
        system.getUserAccountDirectory().createUserAccount(adminProfile, "admin", "admin123", "ADMIN");
        
        // ===== CREATE 10 FACULTY =====
        FacultyProfile[] faculties = new FacultyProfile[10];
        for (int i = 1; i <= 10; i++) {
            Person facPerson = personDir.newPerson("Faculty" + i + " Member");
            facPerson.setEmail("faculty" + i + "@northeastern.edu");
            facPerson.setContactInfo("617-555-10" + String.format("%02d", i));
            
            FacultyProfile facProfile = facultyDir.newFacultyProfile(facPerson);
            facProfile.setDepartment("Information Systems");
            facProfile.setOfficeLocation("WVH " + (300 + i));
            facProfile.setOfficeHours("Mon/Wed 2-4 PM");
            
            faculties[i-1] = facProfile;
            system.getUserAccountDirectory().createUserAccount(facProfile, "faculty" + i, "pass123", "FACULTY");
        }
        
        // ===== CREATE 10 STUDENTS =====
        StudentProfile[] students = new StudentProfile[10];
        for (int i = 1; i <= 10; i++) {
            Person stuPerson = personDir.newPerson("Student" + i + " Name");
            stuPerson.setEmail("student" + i + "@northeastern.edu");
            stuPerson.setContactInfo("617-555-20" + String.format("%02d", i));
            
            StudentProfile stuProfile = studentDir.newStudentProfile(stuPerson);
            stuProfile.setDepartment("Information Systems");
            
            students[i-1] = stuProfile;
            system.getUserAccountDirectory().createUserAccount(stuProfile, "student" + i, "pass123", "STUDENT");
        }
        
        // ===== CREATE 9 MORE PERSONS (Total 30) =====
        for (int i = 1; i <= 9; i++) {
            Person person = personDir.newPerson("Person" + i);
            person.setEmail("person" + i + "@northeastern.edu");
            person.setContactInfo("617-555-30" + String.format("%02d", i));
        }
        
        // ===== CREATE COURSES =====
        CourseCatalog catalog = dept.getCourseCatalog();
        
        Course c1 = catalog.newCourse("Application Engineering", "INFO 5100", 4);
        Course c2 = catalog.newCourse("Data Structures & Algorithms", "INFO 6205", 4);
        Course c3 = catalog.newCourse("Web Design & User Experience", "INFO 6150", 4);
        Course c4 = catalog.newCourse("Cloud Computing", "INFO 7245", 4);
        Course c5 = catalog.newCourse("Cryptography & Security", "INFO 7500", 4);
        
        
        
        // Mark INFO 5100 as core course
        dept.addCoreCourse(c1);
        
        // ===== CREATE SEMESTER =====
        CourseSchedule fall2025 = dept.newCourseSchedule("Fall2025");
        
        // ===== CREATE 5 COURSE OFFERINGS =====
        CourseOffer offer1 = fall2025.newCourseOffer("INFO 5100");
        offer1.generatSeats(30);
        offer1.AssignAsTeacher(faculties[0]);
        
        CourseOffer offer2 = fall2025.newCourseOffer("INFO 6205");
        offer2.generatSeats(30);
        offer2.AssignAsTeacher(faculties[1]);
        
        CourseOffer offer3 = fall2025.newCourseOffer("INFO 6150");
        offer3.generatSeats(30);
        offer3.AssignAsTeacher(faculties[2]);
        
        CourseOffer offer4 = fall2025.newCourseOffer("INFO 7245");
        offer4.generatSeats(30);
        offer4.AssignAsTeacher(faculties[3]);
        
        CourseOffer offer5 = fall2025.newCourseOffer("INFO 7500");
        offer5.generatSeats(30);
        offer5.AssignAsTeacher(faculties[4]);
        
        // ===== ADD FACULTY ASSIGNMENTS =====
        // Create FacultyAssignment objects for each faculty-course pair
        FacultyAssignment fa1 = new FacultyAssignment(faculties[0], offer1);
        faculties[0].getFacultyAssignments().add(fa1);
        
        FacultyAssignment fa2 = new FacultyAssignment(faculties[1], offer2);
        faculties[1].getFacultyAssignments().add(fa2);
        
        FacultyAssignment fa3 = new FacultyAssignment(faculties[2], offer3);
        faculties[2].getFacultyAssignments().add(fa3);
        
        FacultyAssignment fa4 = new FacultyAssignment(faculties[3], offer4);
        faculties[3].getFacultyAssignments().add(fa4);
        
        FacultyAssignment fa5 = new FacultyAssignment(faculties[4], offer5);
        faculties[4].getFacultyAssignments().add(fa5);
        
        // ===== ENROLL STUDENTS IN COURSES =====
        // Each of first 3 students enrolls in 2 courses
        for (int i = 0; i < 3; i++) {
            StudentProfile student = students[i];
            
            // Create course load for Fall 2025
            CourseLoad courseLoad = student.newCourseLoad("Fall2025");
            
            // Enroll in INFO 5100
            SeatAssignment sa1 = courseLoad.newSeatAssignment(offer1);
            if (sa1 != null) {
                student.getTuitionAccount().chargeForCourse(4);  // 4 credits
                // Assign some grades
                String[] grades = {"A", "A-", "B+", "B", "B-"};
                sa1.setLetterGrade(grades[random.nextInt(grades.length)]);
            }
            
            // Enroll in INFO 6205
            SeatAssignment sa2 = courseLoad.newSeatAssignment(offer2);
            if (sa2 != null) {
                student.getTuitionAccount().chargeForCourse(4);  // 4 credits
                String[] grades = {"A", "A-", "B+", "B", "B-"};
                sa2.setLetterGrade(grades[random.nextInt(grades.length)]);
            }
        }
        
        // Enroll more students in different courses
        for (int i = 3; i < 6; i++) {
            StudentProfile student = students[i];
            CourseLoad courseLoad = student.newCourseLoad("Fall2025");
            
            SeatAssignment sa3 = courseLoad.newSeatAssignment(offer3);
            if (sa3 != null) {
                student.getTuitionAccount().chargeForCourse(4);
                String[] grades = {"A", "A-", "B+", "B", "C+"};
                sa3.setLetterGrade(grades[random.nextInt(grades.length)]);
            }
            
            SeatAssignment sa4 = courseLoad.newSeatAssignment(offer4);
            if (sa4 != null) {
                student.getTuitionAccount().chargeForCourse(4);
                String[] grades = {"A", "A-", "B+", "B", "C+"};
                sa4.setLetterGrade(grades[random.nextInt(grades.length)]);
            }
        }
        
        // Enroll remaining students
        for (int i = 6; i < 10; i++) {
            StudentProfile student = students[i];
            CourseLoad courseLoad = student.newCourseLoad("Fall2025");
            
            SeatAssignment sa5 = courseLoad.newSeatAssignment(offer5);
            if (sa5 != null) {
                student.getTuitionAccount().chargeForCourse(4);
                String[] grades = {"A", "B+", "B", "C+", "C"};
                sa5.setLetterGrade(grades[random.nextInt(grades.length)]);
            }
        }
        
        System.out.println("=== System Configuration Complete ===");
        System.out.println("Created 1 Admin, 10 Faculty, 10 Students");
        System.out.println("Created 5 Courses with Faculty Assignments");
        System.out.println("Enrolled Students with Grades");
        System.out.println("=====================================");
        
        return system;
    }

}