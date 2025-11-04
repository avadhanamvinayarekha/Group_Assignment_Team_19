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
public class CourseRegistrationJPanel extends javax.swing.JPanel {
    
   // PUT THESE VARIABLES HERE - Right after the class declaration
    private List<Course> allCourses;
    private int currentCredits = 0;
    private final int MAX_CREDITS = 8;
    private String currentSemester = "Fall 2024";
    
    
    /**
     * Creates new form CourseRegistrationJPanel
     */
    public CourseRegistrationJPanel() {
         allCourses = new ArrayList<>(); 
        initComponents();
        setupComponents();
        loadCourses();
        populateTableForSelectedSemester();
        updateCreditLoadLabel();
    }
    // PUT THESE SETUP METHODS after the constructor
    
    private void setupComponents() {
        // Setup Semester ComboBox
        comboSemester.removeAllItems();
        comboSemester.addItem("Fall 2024");
        comboSemester.addItem("Spring 2025");
        comboSemester.addItem("Summer 2025");
        comboSemester.addItem("Fall 2025");
        
        // Setup Search Type ComboBox - 3 DIFFERENT SEARCH METHODS
        comboSearchType.removeAllItems();
        comboSearchType.addItem("Course ID");
        comboSearchType.addItem("Faculty Name");
        comboSearchType.addItem("Course Name");
        
        // Clear search text
        txtSearch.setText("");
        
        // Make table non-editable
        tableCourses.setDefaultEditor(Object.class, null);
        tableCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Add button listeners
        setupButtonListeners();
    }
    
    private void setupButtonListeners() {
        // Show All Button
        btnShowAll.addActionListener(e -> populateTableForSelectedSemester());
        
        // Enroll Button
        btnEnroll.addActionListener(e -> enrollInCourse());
        
        // Drop Button
        // Drop Button
if (btnDropCourse != null) {
    btnDropCourse.addActionListener(e -> dropCourse());
}
        
        // Refresh Button
        btnRefresh.addActionListener(e -> {
            populateTableForSelectedSemester();
            updateCreditLoadLabel();
            JOptionPane.showMessageDialog(this,
                "Course list and credit load refreshed!",
                "Refreshed",
                JOptionPane.INFORMATION_MESSAGE);
        });
    }
    // PUT THIS METHOD to load sample courses
    private void loadCourses() {
        if (allCourses == null) {
        allCourses = new ArrayList<>();
    }
    
    // Clear any existing data
    allCourses.clear();
        
        
        // Fall 2024 Courses
        allCourses.add(new Course("CSE101", "Intro to Computer Science", "Dr. Smith", 
            "MWF 9:00-10:00 AM", 3, 30, 25, "Fall 2024"));
        allCourses.add(new Course("CSE201", "Data Structures", "Dr. Johnson", 
            "TTh 10:00-11:30 AM", 4, 35, 30, "Fall 2024"));
        allCourses.add(new Course("MAT101", "Calculus I", "Prof. Davis", 
            "MWF 10:00-11:00 AM", 4, 40, 38, "Fall 2024"));
        allCourses.add(new Course("PHY101", "Physics I", "Dr. Lee", 
            "TTh 1:00-2:30 PM", 3, 35, 35, "Fall 2024"));
        allCourses.add(new Course("ENG101", "English Literature", "Prof. Brown", 
            "MWF 2:00-3:00 PM", 2, 25, 20, "Fall 2024"));
        allCourses.add(new Course("CSE301", "Database Systems", "Dr. Wilson", 
            "TTh 2:30-4:00 PM", 3, 30, 15, "Fall 2024"));
        
        // Spring 2025 Courses
        allCourses.add(new Course("CSE102", "Object Oriented Programming", "Dr. Smith", 
            "MWF 9:00-10:00 AM", 4, 30, 0, "Spring 2025"));
        allCourses.add(new Course("CSE202", "Algorithms", "Dr. Johnson", 
            "TTh 11:00-12:30 PM", 4, 35, 0, "Spring 2025"));
    }
    // PUT THESE METHODS to display courses in the table
    
    private void populateTableForSelectedSemester() {
        currentSemester = (String) comboSemester.getSelectedItem();
        List<Course> semesterCourses = new ArrayList<>();
        
        for (Course c : allCourses) {
            if (c.getSemester().equals(currentSemester)) {
                semesterCourses.add(c);
            }
        }
        
        populateTable(semesterCourses);
    }

    private void populateTable(List<Course> courses) {
        DefaultTableModel model = (DefaultTableModel) tableCourses.getModel();
        model.setRowCount(0);
        
        for (Course c : courses) {
            String status;
            if (c.isEnrolled()) {
                status = "Enrolled";
            } else if (c.getEnrolled() >= c.getCapacity()) {
                status = "Full";
            } else {
                status = "Available";
            }
            
            model.addRow(new Object[]{
                c.getId(),
                c.getName(),
                c.getFaculty(),
                c.getSchedule(),
                c.getCredits(),
                c.getEnrolled() + "/" + c.getCapacity(),
                status
            });
        }
    }
    
    private void updateCreditLoadLabel() {
        lblCreditLoad.setText("Current Credit Load: " + currentCredits + " / " + MAX_CREDITS);
        
        // Visual feedback for credit load
        if (currentCredits >= MAX_CREDITS) {
            lblCreditLoad.setForeground(java.awt.Color.RED);
        } else if (currentCredits >= 6) {
            lblCreditLoad.setForeground(java.awt.Color.ORANGE);
        } else {
            lblCreditLoad.setForeground(java.awt.Color.BLACK);
        }
    }
    // PUT THESE 3 SEARCH METHODS
    
    // SEARCH METHOD 1: Search by Course ID
    private void searchByCourseId(String query) {
        List<Course> results = new ArrayList<>();
        for (Course c : allCourses) {
            if (c.getSemester().equals(currentSemester) && 
                c.getId().toLowerCase().contains(query.toLowerCase())) {
                results.add(c);
            }
        }
        displaySearchResults(results, "Course ID");
    }

    // SEARCH METHOD 2: Search by Faculty Name
    private void searchByFacultyName(String query) {
        List<Course> results = new ArrayList<>();
        for (Course c : allCourses) {
            if (c.getSemester().equals(currentSemester) && 
                c.getFaculty().toLowerCase().contains(query.toLowerCase())) {
                results.add(c);
            }
        }
        displaySearchResults(results, "Faculty Name");
    }

    // SEARCH METHOD 3: Search by Course Name
    private void searchByCourseName(String query) {
        List<Course> results = new ArrayList<>();
        for (Course c : allCourses) {
            if (c.getSemester().equals(currentSemester) && 
                c.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(c);
            }
        }
        displaySearchResults(results, "Course Name");
    }

    private void displaySearchResults(List<Course> results, String searchType) {
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No courses found matching your search for " + searchType + ".",
                "No Results",
                JOptionPane.INFORMATION_MESSAGE);
            populateTableForSelectedSemester();
        } else {
            populateTable(results);
        }
    }
    
    private Course findCourse(String courseId) {
        for (Course c : allCourses) {
            if (c.getId().equals(courseId) && c.getSemester().equals(currentSemester)) {
                return c;
            }
        }
        return null;
    }
    // PUT THIS ENROLL METHOD
    private void enrollInCourse() {
        int selectedRow = tableCourses.getSelectedRow();
        
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select a course from the table to enroll.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String courseId = (String) tableCourses.getValueAt(selectedRow, 0);
        Course selectedCourse = findCourse(courseId);
        if (selectedCourse == null) return;
        
        // CHECK 1: Already enrolled
        if (selectedCourse.isEnrolled()) {
            JOptionPane.showMessageDialog(this,
                "You are already enrolled in this course!",
                "Already Enrolled",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // CHECK 2: Course is full
        if (selectedCourse.getEnrolled() >= selectedCourse.getCapacity()) {
            JOptionPane.showMessageDialog(this,
                "This course is full! No seats available.",
                "Course Full",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // CHECK 3: Credit limit (MAX 8 CREDITS)
        int courseCredits = selectedCourse.getCredits();
        if (currentCredits + courseCredits > MAX_CREDITS) {
            JOptionPane.showMessageDialog(this,
                "Cannot enroll! You would exceed the maximum credit limit.\n\n" +
                "Current Credits: " + currentCredits + "\n" +
                "Course Credits: " + courseCredits + "\n" +
                "Total Would Be: " + (currentCredits + courseCredits) + "\n" +
                "Maximum Allowed: " + MAX_CREDITS,
                "Credit Limit Exceeded",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // ENROLL THE STUDENT
        int confirm = JOptionPane.showConfirmDialog(this,
            "Enroll in " + selectedCourse.getName() + "?\n\n" +
            "Credits: " + courseCredits + "\n" +
            "Faculty: " + selectedCourse.getFaculty(),
            "Confirm Enrollment",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            selectedCourse.setEnrolled(true);
            selectedCourse.incrementEnrolled();
            currentCredits += courseCredits;
            
            updateCreditLoadLabel();
            populateTableForSelectedSemester();
            
            JOptionPane.showMessageDialog(this,
                "Successfully enrolled!",
                "Enrollment Successful",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    // PUT THIS DROP METHOD
    private void dropCourse() {
        int selectedRow = tableCourses.getSelectedRow();
        
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select a course from the table to drop.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String courseId = (String) tableCourses.getValueAt(selectedRow, 0);
        Course selectedCourse = findCourse(courseId);
        
        if (selectedCourse == null) return;
        
        if (!selectedCourse.isEnrolled()) {
            JOptionPane.showMessageDialog(this,
                "You are not enrolled in this course!",
                "Not Enrolled",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Drop " + selectedCourse.getName() + "?",
            "Confirm Drop",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            selectedCourse.setEnrolled(false);
            selectedCourse.decrementEnrolled();
            currentCredits -= selectedCourse.getCredits();
            
            updateCreditLoadLabel();
            populateTableForSelectedSemester();
            
            JOptionPane.showMessageDialog(this,
                "Successfully dropped course!",
                "Drop Successful",
                JOptionPane.INFORMATION_MESSAGE);
        }
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
        comboSemester = new javax.swing.JComboBox<>();
        lblSearchBy = new javax.swing.JLabel();
        comboSearchType = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnShowAll = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCourses = new javax.swing.JTable();
        lblCreditLoad = new javax.swing.JLabel();
        btnEnroll = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnDropCourse = new javax.swing.JButton();

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Course Registration");

        lblSemester.setText("Semester:");

        comboSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSemesterActionPerformed(evt);
            }
        });

        lblSearchBy.setText("Search By:");

        comboSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtSearch.setText(" ");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnShowAll.setText("Show All Courses");

        tableCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Course ID", "Name", "Faculty", "Schedule", "Credits", "Enrolled/Capacity", "Status"
            }
        ));
        jScrollPane1.setViewportView(tableCourses);

        scrollPane.setViewportView(jScrollPane1);

        lblCreditLoad.setText("Current Credit Load: 0 / 8");

        btnEnroll.setText("Enroll");

        btnRefresh.setText("Refresh");

        btnDropCourse.setText("Drop Course");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSearchBy)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnShowAll)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSearch)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(lblTitle))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 102, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblCreditLoad)
                .addGap(161, 161, 161)
                .addComponent(btnEnroll)
                .addGap(66, 66, 66)
                .addComponent(btnRefresh)
                .addGap(49, 49, 49)
                .addComponent(btnDropCourse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSemester)
                        .addComponent(comboSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSearchBy))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(18, 18, 18)
                        .addComponent(btnShowAll)))
                .addGap(35, 35, 35)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCreditLoad)
                    .addComponent(btnEnroll)
                    .addComponent(btnRefresh)
                    .addComponent(btnDropCourse))
                .addContainerGap(66, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
 

 
    
    private void comboSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSemesterActionPerformed
        // TODO add your handling code here:
        populateTableForSelectedSemester();
    }//GEN-LAST:event_comboSemesterActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
         String searchType = (String) comboSearchType.getSelectedItem();
        String query = txtSearch.getText().trim();
        
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a search term.",
                "Input Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Perform search based on selected type
        switch (searchType) {
            case "Course ID":
                searchByCourseId(query);
                break;
            case "Faculty Name":
                searchByFacultyName(query);
                break;
            case "Course Name":
                searchByCourseName(query);
                break;
        }
    
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDropCourse;
    private javax.swing.JButton btnEnroll;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JComboBox<String> comboSearchType;
    private javax.swing.JComboBox<String> comboSemester;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCreditLoad;
    private javax.swing.JLabel lblSearchBy;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableCourses;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
// Put this AT THE VERY END, inside your class but before the final }
class Course {
    private String id, name, faculty, schedule, semester;
    private int credits, capacity, enrolled;
    private boolean isEnrolled;

    public Course(String id, String name, String faculty, String schedule, 
                  int credits, int capacity, int enrolled, String semester) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.schedule = schedule;
        this.credits = credits;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.semester = semester;
        this.isEnrolled = false;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getFaculty() { return faculty; }
    public String getSchedule() { return schedule; }
    public int getCredits() { return credits; }
    public int getCapacity() { return capacity; }
    public int getEnrolled() { return enrolled; }
    public String getSemester() { return semester; }
    public boolean isEnrolled() { return isEnrolled; }

    // Setters
    public void setEnrolled(boolean enrolled) { this.isEnrolled = enrolled; }
    public void incrementEnrolled() { this.enrolled++; }
    public void decrementEnrolled() { this.enrolled--; }
}
}
