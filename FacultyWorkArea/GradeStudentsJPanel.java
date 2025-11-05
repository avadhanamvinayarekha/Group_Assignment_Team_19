package UserInterface.FacultyWorkArea;

import Business.UniversitySystem;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.Seat;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * GradeStudentsJPanel - Assign grades and rank students by performance
 * Faculty can select courses, view enrolled students, assign grades, and rank by GPA
 * @author anusha
 */
public class GradeStudentsJPanel extends javax.swing.JFrame {
    
    private UniversitySystem system;
    private FacultyProfile facultyProfile;
    private CourseOffer selectedCourseOffer;

    public GradeStudentsJPanel() {
        initComponents();
    }
    
    public GradeStudentsJPanel(UniversitySystem system, FacultyProfile faculty) {
        initComponents();
        this.system = system;
        this.facultyProfile = faculty;
        
        setTitle("Grade Students");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        tableStudents.setDefaultEditor(Object.class, null);
        
        populateCourseCombo();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblSelectCourse = new javax.swing.JLabel();
        comboCourses = new javax.swing.JComboBox<>();
        btnLoadStudents = new javax.swing.JButton();
        btnAssignGrade = new javax.swing.JButton();
        btnRankStudents = new javax.swing.JButton();
        lblClassGPA = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStudents = new javax.swing.JTable();

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Grade Students");

        lblSelectCourse.setText("Select Course:");

        comboCourses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCoursesActionPerformed(evt);
            }
        });

        btnLoadStudents.setText(" Load Students");
        btnLoadStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadStudentsActionPerformed(evt);
            }
        });

        btnAssignGrade.setText("Assign Grade");
        btnAssignGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignGradeActionPerformed(evt);
            }
        });

        btnRankStudents.setText("Rank by Performance");
        btnRankStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRankStudentsActionPerformed(evt);
            }
        });

        lblClassGPA.setText("Class GPA: 0.00");

        tableStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Name", "Email", " Grade", " GPA"
            }
        ));
        jScrollPane1.setViewportView(tableStudents);

        scrollPane.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClassGPA)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAssignGrade)
                                .addGap(142, 142, 142)
                                .addComponent(btnRankStudents))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSelectCourse)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboCourses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(btnLoadStudents)))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitle)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectCourse)
                    .addComponent(comboCourses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadStudents))
                .addGap(68, 68, 68)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAssignGrade)
                    .addComponent(btnRankStudents))
                .addGap(26, 26, 26)
                .addComponent(lblClassGPA)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadStudentsActionPerformed
         String selected = (String) comboCourses.getSelectedItem();
        
        if (selected == null || selected.equals("-- Select a course --")) {
            JOptionPane.showMessageDialog(this,
                "Please select a course.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String courseNumber = selected.split(" - ")[0];
        
        selectedCourseOffer = null;
        if (facultyProfile != null && facultyProfile.getFacultyAssignments() != null) {
            for (FacultyAssignment fa : facultyProfile.getFacultyAssignments()) {
                if (fa != null && fa.getCourseOffer() != null && 
                    fa.getCourseOffer().getCourseNumber().equals(courseNumber)) {
                    selectedCourseOffer = fa.getCourseOffer();
                    break;
                }
            }
        }
        
        if (selectedCourseOffer == null) {
            JOptionPane.showMessageDialog(this,
                "Could not load course data.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        loadEnrolledStudents();
    }//GEN-LAST:event_btnLoadStudentsActionPerformed

    private void btnAssignGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignGradeActionPerformed
         int selectedRow = tableStudents.getSelectedRow();
        
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select a student from the table.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (selectedCourseOffer == null) {
            JOptionPane.showMessageDialog(this,
                "Please load students first.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String studentId = (String) tableStudents.getValueAt(selectedRow, 0);
        String studentName = (String) tableStudents.getValueAt(selectedRow, 1);
        
        SeatAssignment targetSA = null;
        ArrayList<Seat> seats = selectedCourseOffer.getSeatList();
        if (seats != null) {
            for (Seat seat : seats) {
                if (seat != null && seat.isOccupied() && seat.seatassignment != null) {
                    if (seat.seatassignment.courseload != null && 
                        seat.seatassignment.courseload.student != null &&
                        seat.seatassignment.courseload.student.getStudentId().equals(studentId)) {
                        targetSA = seat.seatassignment;
                        break;
                    }
                }
            }
        }
        
        if (targetSA == null) {
            JOptionPane.showMessageDialog(this,
                "Could not find student assignment.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String[] grades = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "F"};
        String selectedGrade = (String) JOptionPane.showInputDialog(this,
            "Select grade for " + studentName + ":",
            "Assign Grade",
            JOptionPane.QUESTION_MESSAGE,
            null,
            grades,
            grades[0]);
        
        if (selectedGrade != null) {
            targetSA.setLetterGrade(selectedGrade);
            
            JOptionPane.showMessageDialog(this,
                "Grade assigned successfully!\n\nStudent: " + studentName + "\nGrade: " + selectedGrade,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            loadEnrolledStudents();
        }
   // TODO add your handling code here:
    }//GEN-LAST:event_btnAssignGradeActionPerformed

    private void btnRankStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRankStudentsActionPerformed
           DefaultTableModel model = (DefaultTableModel) tableStudents.getModel();
        int n = model.getRowCount();
        
        if (n == 0) {
            JOptionPane.showMessageDialog(this,
                "No students to rank. Please load students first.",
                "No Data",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Bubble sort by GPA (descending)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                try {
                    String gpa1Str = (String) model.getValueAt(j, 4);
                    String gpa2Str = (String) model.getValueAt(j + 1, 4);
                    
                    double gpa1 = Double.parseDouble(gpa1Str);
                    double gpa2 = Double.parseDouble(gpa2Str);
                    
                    if (gpa1 < gpa2) {
                        Object[] row1 = new Object[5];
                        Object[] row2 = new Object[5];
                        
                        for (int k = 0; k < 5; k++) {
                            row1[k] = model.getValueAt(j, k);
                            row2[k] = model.getValueAt(j + 1, k);
                        }
                        
                        for (int k = 0; k < 5; k++) {
                            model.setValueAt(row2[k], j, k);
                            model.setValueAt(row1[k], j + 1, k);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing GPA: " + e.getMessage());
                }
            }
        }
        
        JOptionPane.showMessageDialog(this,
            "Students ranked by GPA!\n\nHighest GPA is now at the top.",
            "Ranking Complete",
            JOptionPane.INFORMATION_MESSAGE);
    
// TODO add your handling code here:
    }//GEN-LAST:event_btnRankStudentsActionPerformed

    private void comboCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCoursesActionPerformed


        // TODO add your handling code here:
    }//GEN-LAST:event_comboCoursesActionPerformed
      private void populateCourseCombo() {
        comboCourses.removeAllItems();
        comboCourses.addItem("-- Select a course --");
        
        if (facultyProfile == null || facultyProfile.getFacultyAssignments() == null) {
            return;
        }
        
        for (FacultyAssignment fa : facultyProfile.getFacultyAssignments()) {
            try {
                if (fa != null && fa.getCourseOffer() != null) {
                    CourseOffer offer = fa.getCourseOffer();
                    String courseName = offer.getSubjectCourse() != null ? 
                        offer.getSubjectCourse().getName() : "Unknown";
                    comboCourses.addItem(offer.getCourseNumber() + " - " + courseName);
                }
            } catch (Exception e) {
                System.err.println("Error adding course: " + e.getMessage());
            }
        }
    }

    private void loadEnrolledStudents() {
        DefaultTableModel model = (DefaultTableModel) tableStudents.getModel();
        model.setRowCount(0);
        
        if (selectedCourseOffer == null) {
            model.addRow(new Object[]{"-", "No course selected", "-", "-", "0.00"});
            return;
        }
        
        ArrayList<Seat> seats = selectedCourseOffer.getSeatList();
        if (seats == null || seats.isEmpty()) {
            model.addRow(new Object[]{"-", "No students enrolled", "-", "-", "0.00"});
            lblClassGPA.setText("Class GPA: 0.00");
            JOptionPane.showMessageDialog(this,
                "No students enrolled in this course yet.",
                "No Students",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        double totalGPA = 0;
        int count = 0;
        
        for (Seat seat : seats) {
            try {
                if (seat != null && seat.isOccupied() && seat.seatassignment != null) {
                    SeatAssignment sa = seat.seatassignment;
                    
                    if (sa.courseload != null && sa.courseload.student != null) {
                        StudentProfile student = sa.courseload.student;
                        
                        String studentId = student.getStudentId() != null ? student.getStudentId() : "N/A";
                        String studentName = student.getPerson() != null ? 
                            student.getPerson().getPersonId() : "Unknown";
                        String email = student.getPerson() != null ? 
                            student.getPerson().getEmail() : "N/A";
                        String grade = sa.getLetterGrade() != null ? sa.getLetterGrade() : "IP";
                        
                        double studentGPA = 0.0;
                        if (student.getTranscript() != null) {
                            studentGPA = student.getTranscript().calculateOverallGPA();
                            totalGPA += studentGPA;
                            count++;
                        }
                        
                        Object[] row = new Object[5];
                        row[0] = studentId;
                        row[1] = studentName;
                        row[2] = email;
                        row[3] = grade;
                        row[4] = String.format("%.2f", studentGPA);
                        
                        model.addRow(row);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error loading student: " + e.getMessage());
            }
        }
        
        double classGPA = count > 0 ? totalGPA / count : 0.0;
        lblClassGPA.setText("Class GPA: " + String.format("%.2f", classGPA));
        
        if (count > 0) {
            JOptionPane.showMessageDialog(this,
                "Loaded " + count + " student(s) successfully!\n\nClass GPA: " + String.format("%.2f", classGPA),
                "Students Loaded",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            model.addRow(new Object[]{"-", "No students enrolled", "-", "-", "0.00"});
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(GradeStudentsJPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            Business.UniversitySystem testSystem = Business.ConfigureSystem.configure();
            Business.UserAccounts.UserAccount testUser = testSystem.getUserAccountDirectory().getAllAccounts().get(1);
            FacultyProfile faculty = (FacultyProfile) testUser.getProfile();
            new GradeStudentsJPanel(testSystem, faculty).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssignGrade;
    private javax.swing.JButton btnLoadStudents;
    private javax.swing.JButton btnRankStudents;
    private javax.swing.JComboBox<String> comboCourses;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClassGPA;
    private javax.swing.JLabel lblSelectCourse;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableStudents;
    // End of variables declaration//GEN-END:variables
}
