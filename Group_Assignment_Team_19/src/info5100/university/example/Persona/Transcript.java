/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kal bugrara
 * Modified by: [Your Team Name] - Added GPA calculations and academic tracking
 */
public class Transcript {

    StudentProfile student;
    HashMap<String, CourseLoad> courseloadlist;
    CourseLoad currentcourseload;

    public Transcript(StudentProfile sp) {
        student = sp;
        courseloadlist = new HashMap();
    }

    public int getStudentSatisfactionIndex() {
        //for each courseload 
        //get seatassigmnets; 
        //for each seatassignment add 1 if like =true;
        return 0;
    }

    public CourseLoad newCourseLoad(String sem) {
        currentcourseload = new CourseLoad(sem);
        currentcourseload.student = this.student;  // Link student to course load
        courseloadlist.put(sem, currentcourseload);
        return currentcourseload;
    }

    public CourseLoad getCurrentCourseLoad() {
        return currentcourseload;
    }

    public CourseLoad getCourseLoadBySemester(String semester) {
        return courseloadlist.get(semester);
    }

    public float getStudentTotalScore() {
        float sum = 0;
        for (CourseLoad cl : courseloadlist.values()) {
            sum = sum + cl.getSemesterScore();
        }
        return sum;
    }
    
    //sat index means student rated their courses with likes;
    public int getStudentSatifactionIndex() {
        ArrayList<SeatAssignment> courseregistrations = getCourseList();
        int sum = 0;
        for (SeatAssignment sa : courseregistrations) {
            if (sa.getLike()) {
                sum = sum + 1;
            }
        }
        return sum;
    }
    
    //generate a list of all courses taken so far (seetassignments) 
    //from multiple semesters (course loads)
    //from seat assignments we will be able to access the course offers
    public ArrayList<SeatAssignment> getCourseList() {
        ArrayList temp2;
        temp2 = new ArrayList();

        for (CourseLoad cl : courseloadlist.values()) { //extract cl list as objects --ignore label
            temp2.addAll(cl.getSeatAssignments()); //merge one array list to another
        }

        return temp2;
    }

    // ========== NEW METHODS ADDED FOR ASSIGNMENT ==========
    
    /**
     * Calculate GPA for a specific semester
     * @param semester The semester to calculate GPA for (e.g., "Fall2025")
     * @return Term GPA as double (0.0 - 4.0)
     */
    public double calculateTermGPA(String semester) {
        CourseLoad cl = courseloadlist.get(semester);
        if (cl == null) return 0.0;
        
        double totalPoints = 0;
        int totalCredits = 0;
        
        for (SeatAssignment sa : cl.getSeatAssignments()) {
            if (sa.getLetterGrade() != null && !sa.getLetterGrade().equals("IP")) {
                int credits = sa.getCreditHours();
                totalPoints += sa.getGradePoints() * credits;
                totalCredits += credits;
            }
        }
        
        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }
    
    /**
     * Calculate overall GPA across all semesters
     * @return Overall cumulative GPA (0.0 - 4.0)
     */
    public double calculateOverallGPA() {
        double totalPoints = 0;
        int totalCredits = 0;
        
        for (CourseLoad cl : courseloadlist.values()) {
            for (SeatAssignment sa : cl.getSeatAssignments()) {
                if (sa.getLetterGrade() != null && !sa.getLetterGrade().equals("IP")) {
                    int credits = sa.getCreditHours();
                    totalPoints += sa.getGradePoints() * credits;
                    totalCredits += credits;
                }
            }
        }
        
        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }
    
    /**
     * Get total credits earned (only graded courses count)
     * @return Total credit hours completed
     */
    public int getTotalCreditsEarned() {
        int total = 0;
        
        for (CourseLoad cl : courseloadlist.values()) {
            for (SeatAssignment sa : cl.getSeatAssignments()) {
                if (sa.getLetterGrade() != null && !sa.getLetterGrade().equals("IP")) {
                    total += sa.getCreditHours();
                }
            }
        }
        
        return total;
    }
    
    /**
     * Determine academic standing based on GPA
     * Rules:
     * - Good Standing: Term GPA >= 3.0 AND Overall GPA >= 3.0
     * - Academic Warning: Term GPA < 3.0 (even if overall >= 3.0)
     * - Academic Probation: Overall GPA < 3.0
     * 
     * @return Academic standing as String
     */
    public String getAcademicStanding() {
        if (courseloadlist.isEmpty()) return "Good Standing";
        
        // Get most recent semester GPA
        String latestSemester = "";
        if (currentcourseload != null) {
            latestSemester = currentcourseload.semester;
        }
        
        double latestTermGPA = latestSemester.isEmpty() ? 0.0 : calculateTermGPA(latestSemester);
        double overallGPA = calculateOverallGPA();
        
        if (latestTermGPA >= 3.0 && overallGPA >= 3.0) {
            return "Good Standing";
        } else if (latestTermGPA < 3.0 && overallGPA >= 3.0) {
            return "Academic Warning";
        } else {
            return "Academic Probation";
        }
    }
    
    /**
     * Get list of completed courses (graded, not in progress)
     * @return ArrayList of SeatAssignments with grades
     */
    public ArrayList<SeatAssignment> getCompletedCourses() {
        ArrayList<SeatAssignment> completed = new ArrayList<>();
        
        for (CourseLoad cl : courseloadlist.values()) {
            for (SeatAssignment sa : cl.getSeatAssignments()) {
                if (sa.getLetterGrade() != null && !sa.getLetterGrade().equals("IP")) {
                    completed.add(sa);
                }
            }
        }
        
        return completed;
    }

}