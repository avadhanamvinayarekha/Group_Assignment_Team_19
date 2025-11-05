/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.StudentWorkArea;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hp
 */
public class GraduationAuditJPanel extends javax.swing.JPanel {
    
    private List<CompletedCourse> completedCourses;
    private int totalCreditsRequired = 32;
    private int completedCredits = 0;
    private boolean hasCompletedCore = false;
    private String coreCourseName = "INFO 5100";
    private int coreCourseCredits = 4;

    /**
     * Creates new form GraduationAuditJPanel
     */
    public GraduationAuditJPanel() {
         completedCourses = new ArrayList<>();
        
        initComponents();
        
        // Safety check
        if (completedCourses == null) {
            completedCourses = new ArrayList<>();
        }
        
        setupTable();
        loadStudentData();
        calculateProgress();
        updateDisplay();
    }

    // PUT THIS METHOD - It loads what courses the student finished
private void loadStudentData() {
    completedCourses = new ArrayList<>();
    
    // Sample data - Replace with actual student data later
    // Let's pretend the student completed these courses:
    completedCourses.add(new CompletedCourse("INFO 5100", "Application Engineering", 4, "A", "Fall 2024"));
    completedCourses.add(new CompletedCourse("INFO 6150", "Web Design", 4, "A-", "Fall 2024"));
    completedCourses.add(new CompletedCourse("INFO 6205", "Data Structures", 4, "B+", "Spring 2025"));
    completedCourses.add(new CompletedCourse("INFO 7245", "Machine Learning", 4, "A", "Spring 2025"));
    
    // Calculate total completed credits
    completedCredits = 0;
    hasCompletedCore = false;
    
    for (CompletedCourse course : completedCourses) {
        completedCredits += course.getCredits();
        
        // Check if they completed the core course (INFO 5100)
        if (course.getCourseId().equals(coreCourseName)) {
            hasCompletedCore = true;
        }
    }
}

// PUT THIS METHOD - Sets up the table showing requirements
private void setupTable() {
    tableRequirements.setDefaultEditor(Object.class, null); // Make table read-only
    DefaultTableModel model = (DefaultTableModel) tableRequirements.getModel();
    model.setRowCount(0); // Clear existing rows
}

private void populateRequirementsTable() {
    DefaultTableModel model = (DefaultTableModel) tableRequirements.getModel();
    model.setRowCount(0);
    
    // Calculate credits by category
    int coreCompleted = hasCompletedCore ? coreCourseCredits : 0;
    int electivesCompleted = completedCredits - coreCompleted;
    int electivesRequired = totalCreditsRequired - coreCourseCredits;
    
    // Row 1: Core Course
    model.addRow(new Object[]{
        "Core Course (INFO 5100)",
        coreCourseCredits + " credits",
        coreCompleted + " credits",
        hasCompletedCore ? "✓ Complete" : "✗ Incomplete"
    });
    
    // Row 2: Electives
    model.addRow(new Object[]{
        "Elective Courses",
        electivesRequired + " credits",
        electivesCompleted + " credits",
        (electivesCompleted >= electivesRequired) ? "✓ Complete" : "✗ Incomplete"
    });
    
    // Row 3: Total
    model.addRow(new Object[]{
        "TOTAL",
        totalCreditsRequired + " credits",
        completedCredits + " credits",
        (completedCredits >= totalCreditsRequired) ? "✓ Complete" : "✗ Incomplete"
    });
}
// PUT THIS METHOD - Does all the math!
private void calculateProgress() {
    // This method recalculates everything
    completedCredits = 0;
    hasCompletedCore = false;
    
    for (CompletedCourse course : completedCourses) {
        completedCredits += course.getCredits();
        if (course.getCourseId().equals(coreCourseName)) {
            hasCompletedCore = true;
        }
    }
}

private boolean isEligibleToGraduate() {
    // Student can graduate if:
    // 1. They have 32 or more credits
    // 2. They completed INFO 5100 (core course)
    return (completedCredits >= totalCreditsRequired) && hasCompletedCore;
}

private int getRemainingCredits() {
    int remaining = totalCreditsRequired - completedCredits;
    return Math.max(0, remaining); // Never show negative
}

private int getProgressPercentage() {
    if (totalCreditsRequired == 0) return 0;
    int percentage = (completedCredits * 100) / totalCreditsRequired;
    return Math.min(100, percentage); // Cap at 100%
}
// PUT THIS METHOD - Updates everything on the screen!
private void updateDisplay() {
    // Update the labels
    lblRequired.setText("Required Credits: " + totalCreditsRequired);
    lblCompleted.setText("Completed Credits: " + completedCredits);
    lblRemaining.setText("Remaining Credits: " + getRemainingCredits());
    
    // Update core course status
    if (hasCompletedCore) {
        lblCoreStatus.setText("Core Course (INFO 5100): ✓ Completed");
        lblCoreStatus.setForeground(new java.awt.Color(0, 150, 0)); // Green
    } else {
        lblCoreStatus.setText("Core Course (INFO 5100): ✗ Not Completed");
        lblCoreStatus.setForeground(new java.awt.Color(200, 0, 0)); // Red
    }
    
    // Update progress bar
    int progress = getProgressPercentage();
    progressBar.setValue(progress);
    progressBar.setString(progress + "%");
    progressBar.setStringPainted(true);
    
    // Change progress bar color based on progress
    if (progress >= 100) {
        progressBar.setForeground(new java.awt.Color(0, 150, 0)); // Green
    } else if (progress >= 75) {
        progressBar.setForeground(new java.awt.Color(255, 165, 0)); // Orange
    } else {
        progressBar.setForeground(new java.awt.Color(100, 149, 237)); // Blue
    }
    
    // Update graduation eligibility
    if (isEligibleToGraduate()) {
        lblEligibility.setText("Graduation Eligibility: ✓ ELIGIBLE TO GRADUATE!");
        lblEligibility.setForeground(new java.awt.Color(0, 150, 0)); // Green
        lblEligibility.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    } else {
        String reason = "";
        if (!hasCompletedCore) {
            reason = " (Must complete INFO 5100)";
        } else if (completedCredits < totalCreditsRequired) {
            reason = " (Need " + getRemainingCredits() + " more credits)";
        }
        lblEligibility.setText("Graduation Eligibility: ✗ Not Eligible" + reason);
        lblEligibility.setForeground(new java.awt.Color(200, 0, 0)); // Red
    }
    
    // Populate the table
    populateRequirementsTable();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblProgram = new javax.swing.JLabel();
        lblRequired = new javax.swing.JLabel();
        lblCompleted = new javax.swing.JLabel();
        lblRemaining = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        lblCoreStatus = new javax.swing.JLabel();
        lblEligibility = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRequirements = new javax.swing.JTable();

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Graduation Requirements Audit");

        lblProgram.setText("Program: MSIS");

        lblRequired.setText("Required Credits: 32");

        lblCompleted.setText("Completed Credits: 0");

        lblRemaining.setText("Remaining Credits: 32");

        lblCoreStatus.setText("Core Course (INFO 5100): ✗ Not Completed");

        lblEligibility.setText("Graduation Eligibility: Not Eligible");

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        tableRequirements.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Category", "Required", "Completed", "Status"
            }
        ));
        jScrollPane1.setViewportView(tableRequirements);

        scrollPane.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRequired)
                            .addComponent(lblProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCoreStatus)
                            .addComponent(lblEligibility)
                            .addComponent(lblCompleted)
                            .addComponent(lblRemaining)
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(btnRefresh)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitle)
                .addGap(48, 48, 48)
                .addComponent(lblProgram)
                .addGap(18, 18, 18)
                .addComponent(lblRequired)
                .addGap(22, 22, 22)
                .addComponent(lblCompleted)
                .addGap(18, 18, 18)
                .addComponent(lblRemaining)
                .addGap(27, 27, 27)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCoreStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEligibility)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
         loadStudentData();
    calculateProgress();
    updateDisplay();
    
    JOptionPane.showMessageDialog(this,
        "Graduation audit refreshed successfully!",
        "Refreshed",
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnRefreshActionPerformed
// End of variables declaration                   
    
    // PUT THIS CLASS HERE - At the very bottom!
    class CompletedCourse {
        private String courseId;
        private String courseName;
        private int credits;
        private String grade;
        private String semester;
        
        public CompletedCourse(String courseId, String courseName, int credits, String grade, String semester) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.credits = credits;
            this.grade = grade;
            this.semester = semester;
        }
        
        // Getters
        public String getCourseId() { return courseId; }
        public String getCourseName() { return courseName; }
        public int getCredits() { return credits; }
        public String getGrade() { return grade; }
        public String getSemester() { return semester; }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCompleted;
    private javax.swing.JLabel lblCoreStatus;
    private javax.swing.JLabel lblEligibility;
    private javax.swing.JLabel lblProgram;
    private javax.swing.JLabel lblRemaining;
    private javax.swing.JLabel lblRequired;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableRequirements;
    // End of variables declaration//GEN-END:variables
}
