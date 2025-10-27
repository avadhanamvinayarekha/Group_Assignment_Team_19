package UserInterface.FacultyWorkArea;

import Business.UniversitySystem;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.Seat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CourseManagementJPanel extends javax.swing.JFrame {
    
    private UniversitySystem system;
    private FacultyProfile facultyProfile;

    public CourseManagementJPanel() {
        initComponents();
    }
    
    public CourseManagementJPanel(UniversitySystem system, FacultyProfile faculty) {
        initComponents();
        this.system = system;
        this.facultyProfile = faculty;
        
        setTitle("Course Management");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        loadMyCourses();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCourses = new javax.swing.JTable();
        btnViewDetails = new javax.swing.JButton();
        btnToggleEnrollment = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("My Assigned Courses");

        tableCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Course ID", " Name", "Schedule", "Enrolled", "Capacity", "Status"
            }
        ));
        jScrollPane1.setViewportView(tableCourses);

        scrollPane.setViewportView(jScrollPane1);

        btnViewDetails.setText("View/Edit Details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        btnToggleEnrollment.setText("Open/Close Enrollment");
        btnToggleEnrollment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleEnrollmentActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addGap(240, 240, 240))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnViewDetails)
                        .addGap(41, 41, 41)
                        .addComponent(btnToggleEnrollment)
                        .addGap(41, 41, 41)
                        .addComponent(btnRefresh)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitle)
                .addGap(75, 75, 75)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewDetails)
                    .addComponent(btnToggleEnrollment)
                    .addComponent(btnRefresh))
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        int selectedRow = tableCourses.getSelectedRow();
        
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select a course from the table.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this,
            "Course details editing coming soon!",
            "Info",
            JOptionPane.INFORMATION_MESSAGE);

// TODO add your handling code here:
    }//GEN-LAST:event_btnViewDetailsActionPerformed

    private void btnToggleEnrollmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleEnrollmentActionPerformed
        

// TODO add your handling code here:
    }//GEN-LAST:event_btnToggleEnrollmentActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
 loadMyCourses();        

// TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed
   private void loadMyCourses() {
        DefaultTableModel model = (DefaultTableModel) tableCourses.getModel();
        model.setRowCount(0);
        
        for (FacultyAssignment fa : facultyProfile.getFacultyAssignments()) {
            CourseOffer offer = fa.getCourseOffer();
            
            // Count enrolled students
            int enrolled = 0;
            for (Seat seat : offer.getSeatList()) {
                if (seat.isOccupied()) {
                    enrolled++;
                }
            }
            
            Object[] row = new Object[6];
            row[0] = offer.getCourseNumber();
            row[1] = offer.getSubjectCourse().getName();
            row[2] = "Mon/Wed 9:00-10:30"; // Default schedule
            row[3] = enrolled;
            row[4] = offer.getSeatList().size();
            row[5] = "Open"; // Enrollment status
            
            model.addRow(row);
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
            java.util.logging.Logger.getLogger(CourseManagementJPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CourseManagementJPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnToggleEnrollment;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableCourses;
    // End of variables declaration//GEN-END:variables
}
