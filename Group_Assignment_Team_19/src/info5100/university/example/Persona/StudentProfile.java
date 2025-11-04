/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Persona.EmploymentHistory.EmploymentHistroy;
import java.util.ArrayList;
import Business.Financial.TuitionAccount;

/**
 *
 * @author kal bugrara
 */
public class StudentProfile {

       Person person;
    Transcript transcript;
    EmploymentHistroy employmenthistory;
    
    // ADD THESE NEW FIELDS
    TuitionAccount tuitionAccount;
    String studentId;
    String department;
    
    public StudentProfile(Person p) {
        person = p;
        transcript = new Transcript(this);
        employmenthistory = new EmploymentHistroy();
        
        // ADD THESE LINES
        this.studentId = "STU" + System.currentTimeMillis();
        this.tuitionAccount = new TuitionAccount(this);
    }

    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public CourseLoad getCourseLoadBySemester(String semester) {

        return transcript.getCourseLoadBySemester(semester);
    }

    public CourseLoad getCurrentCourseLoad() {

        return transcript.getCurrentCourseLoad();
    }

    public CourseLoad newCourseLoad(String s) {

        return transcript.newCourseLoad(s);
    }

    public ArrayList<SeatAssignment> getCourseList() {

        return transcript.getCourseList();

    }
        public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public TuitionAccount getTuitionAccount() {
        return tuitionAccount;
    }
    
    public Person getPerson() {
        return person;
    }
    
    // Method to check current credit load
    public int getCurrentCreditLoad() {
        CourseLoad current = transcript.getCurrentCourseLoad();
        if (current == null) return 0;
        
        int total = 0;
        for (SeatAssignment sa : current.getSeatAssignments()) {
            total += sa.getCreditHours();
        }
        return total;
    }
    
    // Method to check if student can enroll (max 8 credits)
    public boolean canEnrollInCourse(int courseCredits) {
        return (getCurrentCreditLoad() + courseCredits) <= 8;
    }
    
    // Method to check if can view transcript (no balance)
    public boolean canViewTranscript() {
        return !tuitionAccount.hasBalance();
    }

}