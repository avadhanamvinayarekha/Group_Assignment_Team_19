/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.AdminWorkArea;
import Business.*;
import Business.UserAccounts.*;
import info5100.university.example.Persona.*;
import info5100.university.example.CourseSchedule.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

/**
 *
 * @author Hp
 */
public class AnalyticsDashboardJPanel extends javax.swing.JPanel {
private UniversitySystem system;
    /**
     * Creates new form AnalyticsDashboardJPanel
     */
  public AnalyticsDashboardJPanel() {
        initComponents();
    }
    public AnalyticsDashboardJPanel(UniversitySystem system) {
        initComponents();
                this.system = system;
        
        
        
        loadAllAnalytics();

    }
    private void loadAllAnalytics() {
        // 1. LOAD USER STATS BY ROLE
        loadUserStatsByRole();
        
        // 2. LOAD COURSE STATS PER SEMESTER
        loadCourseStats();
        
        // 3. LOAD ENROLLMENT PER COURSE
        loadEnrollmentStats();
        
        // 4. LOAD TUITION REVENUE
        loadRevenueStats();
    }
    
    private void loadUserStatsByRole() {
        DefaultTableModel model = (DefaultTableModel) tableUserStats.getModel();
        model.setRowCount(0);
        
        int adminCount = system.getUserAccountDirectory().getCountByRole("ADMIN");
        int facultyCount = system.getUserAccountDirectory().getCountByRole("FACULTY");
        int studentCount = system.getUserAccountDirectory().getCountByRole("STUDENT");
        
        model.addRow(new Object[]{"Admin", adminCount});
        model.addRow(new Object[]{"Faculty", facultyCount});
        model.addRow(new Object[]{"Student", studentCount});
        model.addRow(new Object[]{"Total", adminCount + facultyCount + studentCount});
    }
    
    private void loadCourseStats() {
        DefaultTableModel model = (DefaultTableModel) tableCourseStats.getModel();
        model.setRowCount(0);
        
        // Get all semesters from department
        HashMap<String, CourseSchedule> schedules = system.getDepartment().getMasterCourseCatalog();
        
        for (String semester : schedules.keySet()) {
            CourseSchedule cs = schedules.get(semester);
            int courseCount = cs.getSchedule().size();
            
            model.addRow(new Object[]{semester, courseCount});
        }
        
        if (schedules.isEmpty()) {
            model.addRow(new Object[]{"No data", 0});
        }
    }
    
    private void loadEnrollmentStats() {
         DefaultTableModel model = (DefaultTableModel) tableEnrollment.getModel();
        model.setRowCount(0);
        
        HashMap<String, CourseSchedule> schedules = system.getDepartment().getMasterCourseCatalog();
        
        for (CourseSchedule cs : schedules.values()) {
            for (CourseOffer offer : cs.getSchedule()) {
                int enrolled = 0;
                int capacity = offer.getSeatList().size();
                
                // Count occupied seats
                for (Seat seat : offer.getSeatList()) {
                    if (seat.isOccupied()) {
                        enrolled++;
                    }
                }
                
                double percentFull = capacity > 0 ? (enrolled * 100.0 / capacity) : 0;
                
                Object[] row = new Object[5];
                row[0] = offer.getCourseNumber();
                row[1] = offer.getSubjectCourse().getName();
                row[2] = enrolled;
                row[3] = capacity;
                row[4] = String.format("%.1f%%", percentFull);
                
                model.addRow(row);
            }
        }
        
        if (schedules.isEmpty()) {
            model.addRow(new Object[]{"No data", "", 0, 0, "0%"});
        }
    }
    
    private void loadRevenueStats() {
         DefaultTableModel model = (DefaultTableModel) tableRevenue.getModel();
        model.setRowCount(0);
        
        double grandTotal = 0;
        HashMap<String, CourseSchedule> schedules = system.getDepartment().getMasterCourseCatalog();
        
        for (String semester : schedules.keySet()) {
            CourseSchedule cs = schedules.get(semester);
            int revenue = cs.calculateTotalRevenues();
            
            model.addRow(new Object[]{semester, "$" + String.format("%,d", revenue)});
            grandTotal += revenue;
        }
        
        if (schedules.isEmpty()) {
            model.addRow(new Object[]{"No data", "$0"});
        }
        
        lblTotalRevenue.setText("Total: $" + String.format("%,.2f", grandTotal));
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
        lblSection1 = new javax.swing.JLabel();
        scrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUserStats = new javax.swing.JTable();
        lblSection3 = new javax.swing.JLabel();
        scrollPane3 = new javax.swing.JScrollPane();
        tableEnrollment = new javax.swing.JTable();
        lblSection2 = new javax.swing.JLabel();
        scrollPane2 = new javax.swing.JScrollPane();
        tableCourseStats = new javax.swing.JTable();
        lblSection4 = new javax.swing.JLabel();
        lblTotalRevenue = new javax.swing.JLabel();
        scrollPane4 = new javax.swing.JScrollPane();
        tableRevenue = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();

        lblTitle.setText("University Analytics Dashboard");

        lblSection1.setText("Active Users by Role");

        tableUserStats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Role", "Count"
            }
        ));
        tableUserStats.setMinimumSize(new java.awt.Dimension(10, 70));
        jScrollPane2.setViewportView(tableUserStats);

        scrollPane1.setViewportView(jScrollPane2);

        lblSection3.setText("Enrollnment Per Course");

        tableEnrollment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Course ID", "Name", "Enrolled", "Capacity"
            }
        ));
        scrollPane3.setViewportView(tableEnrollment);

        lblSection2.setText("Courses Per Semester");

        tableCourseStats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Semester", "Total Courses"
            }
        ));
        scrollPane2.setViewportView(tableCourseStats);

        lblSection4.setText("Tuition Revenue");

        lblTotalRevenue.setText("Total : $ 0.00");

        tableRevenue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Semester", "Revenue"
            }
        ));
        scrollPane4.setViewportView(tableRevenue);

        btnRefresh.setText("Refresh Data");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblSection1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSection2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblSection4)
                        .addGap(45, 45, 45)
                        .addComponent(lblTotalRevenue))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSection3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRefresh))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(403, 403, 403)
                            .addComponent(lblTitle))))
                .addContainerGap(438, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSection1)
                .addGap(18, 18, 18)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSection2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnRefresh)))
                .addGap(18, 18, 18)
                .addComponent(lblSection3)
                .addGap(18, 18, 18)
                .addComponent(scrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSection4)
                    .addComponent(lblTotalRevenue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

        loadAllAnalytics();
        JOptionPane.showMessageDialog(this,
            "Analytics refreshed!",
            "Info",
            JOptionPane.INFORMATION_MESSAGE);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSection1;
    private javax.swing.JLabel lblSection2;
    private javax.swing.JLabel lblSection3;
    private javax.swing.JLabel lblSection4;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalRevenue;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    private javax.swing.JScrollPane scrollPane4;
    private javax.swing.JTable tableCourseStats;
    private javax.swing.JTable tableEnrollment;
    private javax.swing.JTable tableRevenue;
    private javax.swing.JTable tableUserStats;
    // End of variables declaration//GEN-END:variables
}
