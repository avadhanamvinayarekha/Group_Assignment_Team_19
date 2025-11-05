/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.StudentWorkArea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hp
 */
public class TranscriptJPanel extends javax.swing.JPanel {
    
    private List<CourseRecord> allCourseRecords;
    private double overallGPA = 0.0;
    private int totalCreditsEarned = 0;
    private Map<String, Double> termGPAs; // Store GPA for each semester
    private PayTuitionJPanel payTuitionPanel;

    /**
     * Creates new form TranscriptJPanel
     */
    public TranscriptJPanel() {
       allCourseRecords = new ArrayList<>();
        termGPAs = new HashMap<>();
        
        initComponents();
        
        // Safety check
        if (allCourseRecords == null) {
            allCourseRecords = new ArrayList<>();
        }
        if (termGPAs == null) {
            termGPAs = new HashMap<>();
        }
        
        setupComponents();
        loadTranscriptData();
        calculateGPAs();
        updateDisplay();
    }
    // PUT THIS METHOD - Shows message if tuition not paid
private void showTuitionRequiredMessage() {
    // Clear the table
    DefaultTableModel model = (DefaultTableModel) tableTranscript.getModel();
    model.setRowCount(0);
    
    // Show big message in the table
    model.addRow(new Object[]{
        "⚠️", "TUITION PAYMENT REQUIRED", "⚠️", "⚠️", "⚠️", "⚠️", "⚠️"
    });
    model.addRow(new Object[]{
        "", "Your transcript is not available", "", "", "", "", ""
    });
    model.addRow(new Object[]{
        "", "Please pay your tuition balance first", "", "", "", "", ""
    });
    model.addRow(new Object[]{
        "", "Go to: Pay Tuition → Pay Full Balance", "", "", "", "", ""
    });
    
    // Update labels
    lblTitle.setText("Academic Transcript - PAYMENT REQUIRED");
    lblTitle.setForeground(new java.awt.Color(200, 0, 0));
    lblOverallGPA.setText("Overall GPA: N/A");
    lblTotalCredits.setText("Total Credits: N/A");
    
    // Show popup message
    JOptionPane.showMessageDialog(this,
        "⚠️ TRANSCRIPT ACCESS DENIED ⚠️\n\n" +
        "You cannot view your transcript until tuition is paid.\n\n" +
        "Please go to the 'Pay Tuition' tab and pay your balance.\n" +
        "Once paid, your transcript will be available immediately.",
        "Tuition Payment Required",
        JOptionPane.WARNING_MESSAGE);
}
// PUT THIS METHOD - Sets up the semester dropdown
private void setupComponents() {
    // Setup semester combo box
    comboSemester.removeAllItems();
    comboSemester.addItem("All Semesters");
    comboSemester.addItem("Fall 2024");
    comboSemester.addItem("Spring 2025");
    comboSemester.addItem("Summer 2025");
    comboSemester.addItem("Fall 2025");
    
    // Make table non-editable
    tableTranscript.setDefaultEditor(Object.class, null);
    tableTranscript.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    termGPAs = new HashMap<>();
}
// PUT THIS METHOD - Loads all your course grades
private void loadTranscriptData() {
    allCourseRecords = new ArrayList<>();
    
    // Sample transcript data - Replace with actual student data
    
    // Fall 2024 courses
    allCourseRecords.add(new CourseRecord("Fall 2024", "INFO 5100", 
        "Application Engineering", 4, "A", 4.0));
    allCourseRecords.add(new CourseRecord("Fall 2024", "INFO 6150", 
        "Web Design", 4, "A-", 3.7));
    
    // Spring 2025 courses
    allCourseRecords.add(new CourseRecord("Spring 2025", "INFO 6205", 
        "Data Structures", 4, "B+", 3.3));
    allCourseRecords.add(new CourseRecord("Spring 2025", "INFO 7245", 
        "Machine Learning", 4, "A", 4.0));
    
    // Summer 2025 courses
    allCourseRecords.add(new CourseRecord("Summer 2025", "INFO 6350", 
        "Algorithms", 4, "B", 3.0));
    
    // Fall 2025 courses (current)
    allCourseRecords.add(new CourseRecord("Fall 2025", "INFO 7374", 
        "Special Topics in CS", 4, "A-", 3.7));
    allCourseRecords.add(new CourseRecord("Fall 2025", "INFO 6350", 
        "Database Management", 4, "B+", 3.3));
}
// PUT THESE METHODS - Calculate Term GPA and Overall GPA
private void calculateGPAs() {
    termGPAs.clear();
    
    // Group courses by term
    Map<String, List<CourseRecord>> coursesByTerm = new HashMap<>();
    
    for (CourseRecord record : allCourseRecords) {
        String term = record.getTerm();
        if (!coursesByTerm.containsKey(term)) {
            coursesByTerm.put(term, new ArrayList<>());
        }
        coursesByTerm.get(term).add(record);
    }
    
    // Calculate Term GPA for each semester
    for (String term : coursesByTerm.keySet()) {
        List<CourseRecord> termCourses = coursesByTerm.get(term);
        double termGPA = calculateTermGPA(termCourses);
        termGPAs.put(term, termGPA);
    }
    
    // Calculate Overall GPA
    overallGPA = calculateOverallGPA();
    
    // Calculate total credits
    totalCreditsEarned = 0;
    for (CourseRecord record : allCourseRecords) {
        totalCreditsEarned += record.getCredits();
    }
}

private double calculateTermGPA(List<CourseRecord> termCourses) {
    double totalQualityPoints = 0.0;
    int totalCredits = 0;
    
    for (CourseRecord course : termCourses) {
        // Quality Points = Grade Points × Credit Hours
        double qualityPoints = course.getGradePoints() * course.getCredits();
        totalQualityPoints += qualityPoints;
        totalCredits += course.getCredits();
    }
    
    if (totalCredits == 0) return 0.0;
    
    // Term GPA = Total Quality Points / Total Credits
    return totalQualityPoints / totalCredits;
}

private double calculateOverallGPA() {
    double totalQualityPoints = 0.0;
    int totalCredits = 0;
    
    // Sum up ALL courses from ALL terms
    for (CourseRecord course : allCourseRecords) {
        double qualityPoints = course.getGradePoints() * course.getCredits();
        totalQualityPoints += qualityPoints;
        totalCredits += course.getCredits();
    }
    
    if (totalCredits == 0) return 0.0;
    
    // Overall GPA = Total Quality Points (All Terms) / Total Credits (All Terms)
    return totalQualityPoints / totalCredits;
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
// PUT THIS METHOD - Figures out your academic standing
private String determineAcademicStanding(String term, double termGPA) {
    // Rules:
    // 1. Good Standing: Term GPA ≥ 3.0 AND Overall GPA ≥ 3.0
    // 2. Academic Warning: Term GPA < 3.0 (even if overall GPA ≥ 3.0)
    // 3. Academic Probation: Overall GPA < 3.0 (regardless of term GPA)
    
    if (overallGPA < 3.0) {
        return "Academic Probation";
    } else if (termGPA < 3.0) {
        return "Academic Warning";
    } else {
        return "Good Standing";
    }
}
// PUT THESE METHODS - Show transcript in table
private void populateTable(List<CourseRecord> records) {
    DefaultTableModel model = (DefaultTableModel) tableTranscript.getModel();
    model.setRowCount(0); // Clear existing rows
    
    if (records.isEmpty()) {
        model.addRow(new Object[]{
            "-", "No courses found", "-", "-", "-", "-", "-"
        });
        return;
    }
    
    // Group by term and show
    Map<String, List<CourseRecord>> coursesByTerm = new HashMap<>();
    
    for (CourseRecord record : records) {
        String term = record.getTerm();
        if (!coursesByTerm.containsKey(term)) {
            coursesByTerm.put(term, new ArrayList<>());
        }
        coursesByTerm.get(term).add(record);
    }
    
    // Calculate running overall GPA
    double runningQualityPoints = 0.0;
    int runningCredits = 0;
    
    // Show courses term by term
    for (String term : getSortedTerms(coursesByTerm.keySet())) {
        List<CourseRecord> termCourses = coursesByTerm.get(term);
        double termGPA = termGPAs.get(term);
        String academicStanding = determineAcademicStanding(term, termGPA);
        
        // Add each course in the term
        for (int i = 0; i < termCourses.size(); i++) {
            CourseRecord course = termCourses.get(i);
            
            // Update running totals
            runningQualityPoints += (course.getGradePoints() * course.getCredits());
            runningCredits += course.getCredits();
            double runningOverallGPA = runningCredits > 0 ? runningQualityPoints / runningCredits : 0.0;
            
            Object[] row = new Object[7];
            
            // Show term and academic standing only on first row of each term
            if (i == 0) {
                row[0] = term;
                row[1] = academicStanding;
            } else {
                row[0] = "";
                row[1] = "";
            }
            
            row[2] = course.getCourseId();
            row[3] = course.getCourseName();
            row[4] = course.getGrade();
            row[5] = String.format("%.2f", termGPA);
            row[6] = String.format("%.2f", runningOverallGPA);
            
            model.addRow(row);
        }
        
        // Add a separator row between terms
        model.addRow(new Object[]{"", "", "", "", "", "", ""});
    }
}

private List<String> getSortedTerms(java.util.Set<String> terms) {
    // Sort terms chronologically
    List<String> sortedTerms = new ArrayList<>(terms);
    sortedTerms.sort((t1, t2) -> {
        // Simple sorting: Fall < Spring < Summer, and by year
        String[] parts1 = t1.split(" ");
        String[] parts2 = t2.split(" ");
        
        int year1 = Integer.parseInt(parts1[1]);
        int year2 = Integer.parseInt(parts2[1]);
        
        if (year1 != year2) {
            return year1 - year2;
        }
        
        // Same year, sort by season
        return getSeasonOrder(parts1[0]) - getSeasonOrder(parts2[0]);
    });
    
    return sortedTerms;
}

private int getSeasonOrder(String season) {
    switch (season) {
        case "Spring": return 1;
        case "Summer": return 2;
        case "Fall": return 3;
        default: return 0;
    }
}
// PUT THIS METHOD - Updates all labels and display
private void updateDisplay() {
    // Update GPA label with color coding
    lblOverallGPA.setText(String.format("Overall GPA: %.2f", overallGPA));
    
    // Color code GPA
    if (overallGPA >= 3.5) {
        lblOverallGPA.setForeground(new java.awt.Color(0, 150, 0)); // Dark Green - Excellent
    } else if (overallGPA >= 3.0) {
        lblOverallGPA.setForeground(new java.awt.Color(100, 149, 237)); // Blue - Good
    } else if (overallGPA >= 2.5) {
        lblOverallGPA.setForeground(new java.awt.Color(255, 165, 0)); // Orange - Warning
    } else {
        lblOverallGPA.setForeground(new java.awt.Color(200, 0, 0)); // Red - Probation
    }
    
    // Update credits label
    lblTotalCredits.setText("Total Credits Earned: " + totalCreditsEarned);
    
    // Populate table with all courses
    populateTable(allCourseRecords);
}
// PUT THIS METHOD - Filter transcript by semester
private void filterBySemester() {
    String selectedSemester = (String) comboSemester.getSelectedItem();
    
    if (selectedSemester.equals("All Semesters")) {
        // Show all courses
        populateTable(allCourseRecords);
    } else {
        // Filter by selected semester
        List<CourseRecord> filteredRecords = new ArrayList<>();
        
        for (CourseRecord record : allCourseRecords) {
            if (record.getTerm().equals(selectedSemester)) {
                filteredRecords.add(record);
            }
        }
        
        populateTable(filteredRecords);
        
        // Show info message
        if (!filteredRecords.isEmpty()) {
            double semesterGPA = termGPAs.get(selectedSemester);
            JOptionPane.showMessageDialog(this,
                "Showing courses for: " + selectedSemester + "\n\n" +
                "Courses: " + filteredRecords.size() + "\n" +
                "Term GPA: " + String.format("%.2f", semesterGPA),
                "Filtered View",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "No courses found for: " + selectedSemester,
                "No Results",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
// PUT THIS METHOD - Creates printable transcript
private void printTranscript() {
    StringBuilder transcript = new StringBuilder();
    
    transcript.append("═══════════════════════════════════════════════════════\n");
    transcript.append("              ACADEMIC TRANSCRIPT                      \n");
    transcript.append("═══════════════════════════════════════════════════════\n\n");
    
    transcript.append("Student: John Doe\n");
    transcript.append("Student ID: 002123456\n");
    transcript.append("Program: Master of Science in Information Systems\n\n");
    
    transcript.append(String.format("Overall GPA: %.2f\n", overallGPA));
    transcript.append(String.format("Total Credits Earned: %d\n\n", totalCreditsEarned));
    
    transcript.append("───────────────────────────────────────────────────────\n");
    transcript.append("COURSE HISTORY\n");
    transcript.append("───────────────────────────────────────────────────────\n\n");
    
    // Group by term
    Map<String, List<CourseRecord>> coursesByTerm = new HashMap<>();
    for (CourseRecord record : allCourseRecords) {
        if (!coursesByTerm.containsKey(record.getTerm())) {
            coursesByTerm.put(record.getTerm(), new ArrayList<>());
        }
        coursesByTerm.get(record.getTerm()).add(record);
    }
    
    // Print each term
    for (String term : getSortedTerms(coursesByTerm.keySet())) {
        List<CourseRecord> termCourses = coursesByTerm.get(term);
        double termGPA = termGPAs.get(term);
        String standing = determineAcademicStanding(term, termGPA);
        
        transcript.append(term).append("\n");
        transcript.append("Academic Standing: ").append(standing).append("\n");
        transcript.append("Term GPA: ").append(String.format("%.2f", termGPA)).append("\n\n");
        
        for (CourseRecord course : termCourses) {
            transcript.append(String.format("  %-12s %-35s %2s  %d credits\n",
                course.getCourseId(),
                course.getCourseName(),
                course.getGrade(),
                course.getCredits()));
        }
        
        transcript.append("\n");
    }
    
    transcript.append("═══════════════════════════════════════════════════════\n");
    transcript.append("End of Transcript\n");
    transcript.append("═══════════════════════════════════════════════════════\n");
    
    // Show in dialog
    JTextArea textArea = new JTextArea(transcript.toString());
    textArea.setEditable(false);
    textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
    
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new java.awt.Dimension(600, 500));
    
    JOptionPane.showMessageDialog(this,
        scrollPane,
        "Printable Transcript",
        JOptionPane.PLAIN_MESSAGE);
}
// PUT THIS METHOD - Makes all buttons work
private void setupButtons() {
    // Print Button
    btnPrint.addActionListener(e -> printTranscript());
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
        lblFilter = new javax.swing.JLabel();
        comboSemester = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();
        lblOverallGPA = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        lblTotalCredits = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTranscript = new javax.swing.JTable();

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Academic Transcript");

        lblFilter.setText("Filter by Semester:");

        comboSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnFilter.setText("Apply Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        lblOverallGPA.setText("Overall GPA: 0.00");

        btnPrint.setText("Print Transcript");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        lblTotalCredits.setText("Total Credits Earned: 0");

        tableTranscript.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Term", "Academic Standing", "Course ID", "Name", "Grade", "Term GPA", "Overall GPA"
            }
        ));
        jScrollPane1.setViewportView(tableTranscript);

        scrollPane.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 406, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addGap(297, 297, 297))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalCredits)
                        .addGap(41, 41, 41)
                        .addComponent(lblOverallGPA)
                        .addGap(49, 49, 49)
                        .addComponent(btnPrint))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnFilter))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblTitle)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFilter)
                    .addComponent(comboSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addGap(27, 27, 27)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOverallGPA)
                    .addComponent(btnPrint)
                    .addComponent(lblTotalCredits))
                .addContainerGap(80, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
         filterBySemester();
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        printTranscript(); 
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox<String> comboSemester;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFilter;
    private javax.swing.JLabel lblOverallGPA;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalCredits;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableTranscript;
    // End of variables declaration//GEN-END:variables
// End of variables declaration                   
    
    // PUT THIS CLASS HERE - At the very bottom!
    class CourseRecord {
        private String term;
        private String courseId;
        private String courseName;
        private int credits;
        private String grade;
        private double gradePoints;
        
        public CourseRecord(String term, String courseId, String courseName, 
                           int credits, String grade, double gradePoints) {
            this.term = term;
            this.courseId = courseId;
            this.courseName = courseName;
            this.credits = credits;
            this.grade = grade;
            this.gradePoints = gradePoints;
        }
        
        // Getters
        public String getTerm() { return term; }
        public String getCourseId() { return courseId; }
        public String getCourseName() { return courseName; }
        public int getCredits() { return credits; }
        public String getGrade() { return grade; }
        public double getGradePoints() { return gradePoints; }
    }

}
