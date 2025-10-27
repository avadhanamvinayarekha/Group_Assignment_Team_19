/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.StudentWorkArea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tasmiya
 */
public class MyEnrollmentsJPanel extends javax.swing.JPanel {
    
    private List<Enrollment> currentEnrollments;
    private String currentSemester = "Fall 2025";
    private int totalCredits = 0;
    private final int MAX_CREDITS = 8;

    /**
     * Creates new form MyEnrollmentsJPanel
     */
    public MyEnrollmentsJPanel() {
          // Initialize EVERYTHING first
        currentEnrollments = new ArrayList<>();
        
        initComponents();
        
        // Safety check - initialize again if needed
        if (currentEnrollments == null) {
            currentEnrollments = new ArrayList<>();
        }
        
        setupTable();
        loadEnrollments();
        updateDisplay();
        setupButtons();
        setupTableDoubleClick();
    }
     private void setupTableDoubleClick() {
        tableEnrollments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedRow = tableEnrollments.getSelectedRow();
                    if (selectedRow >= 0) {
                        String[] options = {"Submit Assignment", "View Assignments", "Cancel"};
                        int choice = JOptionPane.showOptionDialog(
                            MyEnrollmentsJPanel.this,
                            "What would you like to do?",
                            "Course Actions",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                        );
                        
                        if (choice == 0) submitAssignment();
                        else if (choice == 1) viewAssignments();
                    }
                }
            }
        });
    }
    // PUT THIS METHOD - Loads all courses you're enrolled in
private void loadEnrollments() {
    currentEnrollments = new ArrayList<>();
    
    // Sample enrolled courses - Replace with actual student data later
    currentEnrollments.add(new Enrollment("INFO 5100", "Application Engineering", 
        "Dr. Smith", 4, "A", "In Progress", "Fall 2025"));
    currentEnrollments.add(new Enrollment("INFO 6150", "Web Design", 
        "Prof. Johnson", 4, "A-", "In Progress", "Fall 2025"));
    
    // Calculate total credits
    totalCredits = 0;
    for (Enrollment enrollment : currentEnrollments) {
        totalCredits += enrollment.getCredits();
    }
}
// PUT THIS METHOD - Loads all courses you're enrolled in
 
// PUT THESE METHODS - Sets up the table
private void setupTable() {
    // Make table read-only (can't edit directly)
    tableEnrollments.setDefaultEditor(Object.class, null);
    tableEnrollments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
}

private void populateTable() {
    DefaultTableModel model = (DefaultTableModel) tableEnrollments.getModel();
    model.setRowCount(0); // Clear existing rows
    
    for (Enrollment enrollment : currentEnrollments) {
        model.addRow(new Object[]{
            enrollment.getCourseId(),
            enrollment.getCourseName(),
            enrollment.getFaculty(),
            enrollment.getCredits(),
            enrollment.getGrade()
        });
    }
}
// PUT THESE METHODS - Sets up the table
 
// PUT THIS METHOD - Updates all the text on screen
private void updateDisplay() {
    // Update semester label
    lblSemester.setText("Semester: " + currentSemester);
    
    // Update total credits with color coding
    lblTotalCredits.setText("Total Credits: " + totalCredits + " / " + MAX_CREDITS);
    
    // Color code the credits
    if (totalCredits >= MAX_CREDITS) {
        lblTotalCredits.setForeground(new java.awt.Color(200, 0, 0)); // Red - at max
    } else if (totalCredits >= 6) {
        lblTotalCredits.setForeground(new java.awt.Color(255, 165, 0)); // Orange - getting full
    } else {
        lblTotalCredits.setForeground(new java.awt.Color(0, 150, 0)); // Green - room for more
    }
    
    // Update the table
    populateTable();
    
    // Show summary message
    if (currentEnrollments.isEmpty()) {
        lblTitle.setText("My Current Enrollments (No courses enrolled)");
    } else {
        lblTitle.setText("My Current Enrollments (" + currentEnrollments.size() + " courses)");
    }
}
// PUT THIS METHOD - Lets you drop a course
private void dropCourse() {
    int selectedRow = tableEnrollments.getSelectedRow();
    
    // Check if a course is selected
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this,
            "Please select a course from the table to drop.",
            "No Selection",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Get the selected course
    String courseId = (String) tableEnrollments.getValueAt(selectedRow, 0);
    Enrollment selectedEnrollment = null;
    
    for (Enrollment e : currentEnrollments) {
        if (e.getCourseId().equals(courseId)) {
            selectedEnrollment = e;
            break;
        }
    }
    
    if (selectedEnrollment == null) return;
    
    // Confirm before dropping
    int confirm = JOptionPane.showConfirmDialog(this,
        "Are you sure you want to drop this course?\n\n" +
        "Course: " + selectedEnrollment.getCourseName() + "\n" +
        "Credits: " + selectedEnrollment.getCredits() + "\n\n" +
        "This action cannot be undone!",
        "Confirm Drop",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);
    
    if (confirm == JOptionPane.YES_OPTION) {
        // Drop the course
        int creditsDropped = selectedEnrollment.getCredits();
        currentEnrollments.remove(selectedEnrollment);
        totalCredits -= creditsDropped;
        
        // Update display
        updateDisplay();
        
        // Success message
        JOptionPane.showMessageDialog(this,
            "Course dropped successfully!\n\n" +
            "Course: " + selectedEnrollment.getCourseName() + "\n" +
            "Credits removed: " + creditsDropped + "\n" +
            "New total: " + totalCredits + " credits",
            "Course Dropped",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
// PUT THESE METHODS - Calculates your academic progress
private double calculateGPA() {
    if (currentEnrollments.isEmpty()) return 0.0;
    
    double totalPoints = 0.0;
    int totalCreditHours = 0;
    
    for (Enrollment e : currentEnrollments) {
        if (!e.getGrade().equals("In Progress") && !e.getGrade().equals("N/A")) {
            double gradePoint = convertGradeToPoints(e.getGrade());
            totalPoints += (gradePoint * e.getCredits());
            totalCreditHours += e.getCredits();
        }
    }
    
    if (totalCreditHours == 0) return 0.0;
    return totalPoints / totalCreditHours;
}

private double convertGradeToPoints(String grade) {
    switch (grade) {
        case "A": return 4.0;
        case "A-": return 3.7;
        case "B+": return 3.3;
        case "B": return 3.0;
        case "B-": return 2.7;
        case "C+": return 2.3;
        case "C": return 2.0;
        case "C-": return 1.7;
        case "D": return 1.0;
        case "F": return 0.0;
        default: return 0.0;
    }
}

private String getAcademicStatus() {
    double gpa = calculateGPA();
    
    if (gpa >= 3.5) {
        return "Excellent Standing";
    } else if (gpa >= 3.0) {
        return "Good Standing";
    } else if (gpa >= 2.5) {
        return "Satisfactory";
    } else if (gpa >= 2.0) {
        return "Academic Warning";
    } else {
        return "Academic Probation";
    }
}

private void showAcademicProgress() {
    double gpa = calculateGPA();
    String status = getAcademicStatus();
    
    int completedCourses = 0;
    for (Enrollment e : currentEnrollments) {
        if (!e.getGrade().equals("In Progress")) {
            completedCourses++;
        }
    }
    
    String message = "=== ACADEMIC PROGRESS REPORT ===\n\n" +
                     "Semester: " + currentSemester + "\n" +
                     "Enrolled Courses: " + currentEnrollments.size() + "\n" +
                     "Completed Courses: " + completedCourses + "\n" +
                     "Total Credits: " + totalCredits + " / " + MAX_CREDITS + "\n\n" +
                     "Current GPA: " + String.format("%.2f", gpa) + "\n" +
                     "Academic Status: " + status + "\n\n";
    
    if (totalCredits < MAX_CREDITS) {
        message += "You can still enroll in " + (MAX_CREDITS - totalCredits) + " more credits!";
    } else {
        message += "You are at maximum credit load.";
    }
    
    JOptionPane.showMessageDialog(this,
        message,
        "Academic Progress Report",
        JOptionPane.INFORMATION_MESSAGE);
}
// PUT THESE METHODS - Makes all buttons work + ASSIGNMENT FEATURES
private void setupButtons() {
     btnDropCourse.addActionListener(e1 -> dropCourse());  // ← e1
    
    // Refresh Button
    btnRefresh.addActionListener(e2 -> {  // ← e2
        loadEnrollments();
        updateDisplay();
        
        JOptionPane.showMessageDialog(this,
            "Enrollment list refreshed!\n" +
            "Total courses: " + currentEnrollments.size() + "\n" +
            "Total credits: " + totalCredits,
            "Refreshed",
            JOptionPane.INFORMATION_MESSAGE);
    });
    
    // Submit Assignment Button
    btnSubmit.addActionListener(e3 -> submitAssignment());  // ← e3
    
    // View Assignments Button
    btnView.addActionListener(e4 -> viewAssignments());
}

// NEW: ASSIGNMENT SUBMISSION FEATURE
private void submitAssignment() {
    int selectedRow = tableEnrollments.getSelectedRow();
    
    // Check if course is selected
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this,
            "Please select a course first to submit an assignment.",
            "No Course Selected",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Get selected course
    String courseId = (String) tableEnrollments.getValueAt(selectedRow, 0);
    String courseName = (String) tableEnrollments.getValueAt(selectedRow, 1);
    
    // Find the enrollment
    Enrollment selectedEnrollment = null;
    for (Enrollment e : currentEnrollments) {
        if (e.getCourseId().equals(courseId)) {
            selectedEnrollment = e;
            break;
        }
    }
    
    if (selectedEnrollment == null) return;
    
    // Show assignment submission dialog
    showAssignmentSubmissionDialog(selectedEnrollment);
}

private void showAssignmentSubmissionDialog(Enrollment enrollment) {
    // Create custom dialog
    JDialog dialog = new JDialog();
    dialog.setTitle("Submit Assignment - " + enrollment.getCourseName());
    dialog.setModal(true);
    dialog.setSize(500, 400);
    dialog.setLocationRelativeTo(this);
    
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
    // Course info
    JLabel lblCourse = new JLabel("Course: " + enrollment.getCourseId() + " - " + enrollment.getCourseName());
    lblCourse.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    panel.add(lblCourse);
    panel.add(Box.createVerticalStrut(20));
    
    // Assignment title
    JLabel lblTitle = new JLabel("Assignment Title:");
    panel.add(lblTitle);
    JTextField txtTitle = new JTextField();
    txtTitle.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));
    panel.add(txtTitle);
    panel.add(Box.createVerticalStrut(10));
    
    // Assignment type dropdown
    JLabel lblType = new JLabel("Assignment Type:");
    panel.add(lblType);
    JComboBox<String> comboType = new JComboBox<>(new String[]{
        "Homework", "Project", "Quiz", "Exam", "Lab", "Essay", "Presentation"
    });
    comboType.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));
    panel.add(comboType);
    panel.add(Box.createVerticalStrut(10));
    
    // Description
    JLabel lblDesc = new JLabel("Description/Comments:");
    panel.add(lblDesc);
    JTextArea txtDescription = new JTextArea(5, 20);
    txtDescription.setLineWrap(true);
    txtDescription.setWrapStyleWord(true);
    JScrollPane scrollDesc = new JScrollPane(txtDescription);
    scrollDesc.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 100));
    panel.add(scrollDesc);
    panel.add(Box.createVerticalStrut(10));
    
    // File attachment (simulated)
    JLabel lblFile = new JLabel("File: (Click to attach)");
    panel.add(lblFile);
    JButton btnAttach = new JButton("📎 Attach File");
    btnAttach.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));
    btnAttach.addActionListener(e -> {
        String fileName = JOptionPane.showInputDialog(dialog, 
            "Enter file name (simulation):", "assignment.pdf");
        if (fileName != null && !fileName.trim().isEmpty()) {
            lblFile.setText("File: " + fileName);
        }
    });
    panel.add(btnAttach);
    panel.add(Box.createVerticalStrut(20));
    
    // Buttons
    JPanel buttonPanel = new JPanel();
    JButton btnSubmit = new JButton("Submit Assignment");
    JButton btnCancel = new JButton("Cancel");
    
    btnSubmit.addActionListener(e -> {
        String title = txtTitle.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(dialog,
                "Please enter an assignment title.",
                "Required Field",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Create assignment record
        String type = (String) comboType.getSelectedItem();
        String description = txtDescription.getText();
        
        Assignment assignment = new Assignment(
            "A" + System.currentTimeMillis(), // Generate ID
            enrollment.getCourseId(),
            title,
            type,
            description,
            "Submitted",
            new java.util.Date().toString(),
            "Not Graded Yet"
        );
        
        enrollment.addAssignment(assignment);
        
        JOptionPane.showMessageDialog(dialog,
            "Assignment submitted successfully!\n\n" +
            "Course: " + enrollment.getCourseName() + "\n" +
            "Assignment: " + title + "\n" +
            "Type: " + type + "\n" +
            "Status: Submitted\n\n" +
            "Your instructor will grade it soon.",
            "Submission Successful",
            JOptionPane.INFORMATION_MESSAGE);
        
        dialog.dispose();
    });
    
    btnCancel.addActionListener(e -> dialog.dispose());
    
    buttonPanel.add(btnSubmit);
    buttonPanel.add(btnCancel);
    panel.add(buttonPanel);
    
    dialog.add(panel);
    dialog.setVisible(true);
}

private void viewAssignments() {
    int selectedRow = tableEnrollments.getSelectedRow();
    
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this,
            "Please select a course to view its assignments.",
            "No Course Selected",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String courseId = (String) tableEnrollments.getValueAt(selectedRow, 0);
    Enrollment selectedEnrollment = null;
    
    for (Enrollment e : currentEnrollments) {
        if (e.getCourseId().equals(courseId)) {
            selectedEnrollment = e;
            break;
        }
    }
    
    if (selectedEnrollment == null) return;
    
    List<Assignment> assignments = selectedEnrollment.getAssignments();
    
    if (assignments.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "No assignments submitted for this course yet.\n\n" +
            "Course: " + selectedEnrollment.getCourseName(),
            "No Assignments",
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    // Build assignment list message
    StringBuilder message = new StringBuilder();
    message.append("Assignments for: ").append(selectedEnrollment.getCourseName()).append("\n\n");
    
    for (int i = 0; i < assignments.size(); i++) {
        Assignment a = assignments.get(i);
        message.append("Assignment ").append(i + 1).append(":\n");
        message.append("  Title: ").append(a.getTitle()).append("\n");
        message.append("  Type: ").append(a.getType()).append("\n");
        message.append("  Status: ").append(a.getStatus()).append("\n");
        message.append("  Grade: ").append(a.getGrade()).append("\n");
        message.append("  Submitted: ").append(a.getSubmissionDate()).append("\n\n");
    }
    
    JTextArea textArea = new JTextArea(message.toString());
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new java.awt.Dimension(450, 300));
    
    JOptionPane.showMessageDialog(this,
        scrollPane,
        "My Assignments - " + selectedEnrollment.getCourseName(),
        JOptionPane.INFORMATION_MESSAGE);
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
        lblSemester = new javax.swing.JLabel();
        lblTotalCredits = new javax.swing.JLabel();
        btnDropCourse = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEnrollments = new javax.swing.JTable();
        btnSubmit = new javax.swing.JButton();
        btnView = new javax.swing.JButton();

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("My Current Enrollments");

        lblSemester.setText("Semester: Fall 2025");

        lblTotalCredits.setText("Total Credits: 0");

        btnDropCourse.setText("Drop Selected Course");

        btnRefresh.setText("Refresh");

        tableEnrollments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Course ID", "Name", "Faculty", "Credits", "Grade"
            }
        ));
        jScrollPane1.setViewportView(tableEnrollments);

        scrollPane.setViewportView(jScrollPane1);

        btnSubmit.setText("Submit Assignment ");

        btnView.setText("View My Assignments");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSemester)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotalCredits)
                                .addGap(68, 68, 68)
                                .addComponent(btnDropCourse)
                                .addGap(46, 46, 46)
                                .addComponent(btnRefresh))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btnSubmit)
                        .addGap(109, 109, 109)
                        .addComponent(btnView)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSemester)
                .addGap(26, 26, 26)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCredits)
                    .addComponent(btnDropCourse)
                    .addComponent(btnRefresh))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnView))
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDropCourse;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalCredits;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableEnrollments;
    // End of variables declaration//GEN-END:variables
// End of variables declaration                   
    
    // PUT THESE CLASSES HERE - At the very bottom!
    
    // Class 1: Enrollment (stores course enrollment info)
    class Enrollment {
        private String courseId;
        private String courseName;
        private String faculty;
        private int credits;
        private String grade;
        private String status; // "In Progress", "Completed", "Withdrawn"
        private String semester;
        private List<Assignment> assignments; // NEW: Store assignments
        
        public Enrollment(String courseId, String courseName, String faculty, 
                         int credits, String grade, String status, String semester) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.faculty = faculty;
            this.credits = credits;
            this.grade = grade;
            this.status = status;
            this.semester = semester;
            this.assignments = new ArrayList<>(); // Initialize assignment list
        }
        
        // Getters
        public String getCourseId() { return courseId; }
        public String getCourseName() { return courseName; }
        public String getFaculty() { return faculty; }
        public int getCredits() { return credits; }
        public String getGrade() { return grade; }
        public String getStatus() { return status; }
        public String getSemester() { return semester; }
        public List<Assignment> getAssignments() { return assignments; }
        
        // Setters
        public void setGrade(String grade) { this.grade = grade; }
        public void setStatus(String status) { this.status = status; }
        
        // Assignment management
        public void addAssignment(Assignment assignment) {
            this.assignments.add(assignment);
        }
        
        public int getSubmittedAssignmentCount() {
            int count = 0;
            for (Assignment a : assignments) {
                if (a.getStatus().equals("Submitted") || a.getStatus().equals("Graded")) {
                    count++;
                }
            }
            return count;
        }
    }
    
    // Class 2: Assignment (stores assignment info)
    class Assignment {
        private String assignmentId;
        private String courseId;
        private String title;
        private String type; // Homework, Project, Quiz, Exam, etc.
        private String description;
        private String status; // Pending, Submitted, Graded
        private String submissionDate;
        private String grade;
        
        public Assignment(String assignmentId, String courseId, String title, String type,
                         String description, String status, String submissionDate, String grade) {
            this.assignmentId = assignmentId;
            this.courseId = courseId;
            this.title = title;
            this.type = type;
            this.description = description;
            this.status = status;
            this.submissionDate = submissionDate;
            this.grade = grade;
        }
        
        // Getters
        public String getAssignmentId() { return assignmentId; }
        public String getCourseId() { return courseId; }
        public String getTitle() { return title; }
        public String getType() { return type; }
        public String getDescription() { return description; }
        public String getStatus() { return status; }
        public String getSubmissionDate() { return submissionDate; }
        public String getGrade() { return grade; }
        
        // Setters
        public void setStatus(String status) { this.status = status; }
        public void setGrade(String grade) { this.grade = grade; }
    }

}
