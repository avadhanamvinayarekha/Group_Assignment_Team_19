/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserInterface.StudentWorkArea;

import Business.UniversitySystem;
import Business.UserAccounts.UserAccount;
import info5100.university.example.Persona.StudentProfile;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author tasmiya
 * 
 */
public class StudentDashboard extends javax.swing.JFrame {
    
   private UniversitySystem system;
    private UserAccount currentUser;
    private StudentProfile studentProfile;
    
private JPanel mainContainer;  // Container to hold all panels
private CardLayout cardLayout; // To switch between panels


    /**
     * Creates new form StudentDashboard
     */
    public StudentDashboard(UniversitySystem system, UserAccount user) {
    this.system = system;
        this.currentUser = user;
        this.studentProfile = (StudentProfile) user.getProfile();

        initComponents();
        setupUI();

    }
         public StudentDashboard() {
        this.system = new UniversitySystem("Demo University");
        this.currentUser = new UserAccount(null, "demoUser", "1234", "STUDENT");
        this.studentProfile = null;

        initComponents();
        setupUI();
    }
        private void setupUI() {
    String titleText = "Student Dashboard";
    if (studentProfile != null && studentProfile.getPerson() != null) {
        titleText += " - " + studentProfile.getPerson().getPersonId();
    } else {
        titleText += " (Demo Mode)";
    }

    setTitle(titleText);
    setSize(1000, 700);  // Made bigger to fit panels
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    // Setup panel container and button actions
    setupPanelContainer();
    setupButtonActions();
}
        // PUT THIS METHOD - Creates the container for all panels
private void setupPanelContainer() {
    // Create CardLayout and container
    cardLayout = new CardLayout();
    mainContainer = new JPanel(cardLayout);
    
    // Create a home/menu panel (the dashboard buttons)
    JPanel homePanel = createHomePanel();
    
    // Add all panels to the container
    mainContainer.add(homePanel, "HOME");
    mainContainer.add(new CourseRegistrationJPanel(), "COURSE_REGISTRATION");
    mainContainer.add(new MyEnrollmentsJPanel(), "MY_ENROLLMENTS");
    mainContainer.add(new TranscriptJPanel(), "TRANSCRIPT");
    mainContainer.add(new GraduationAuditJPanel(), "GRADUATION_AUDIT");
    mainContainer.add(new PayTuitionJPanel(), "PAY_TUITION");
    mainContainer.add(new ProfileManagementJPanel(), "MY_PROFILE");
    
    // Replace the content pane with our container
    setContentPane(mainContainer);
    
    // Show home panel first
    cardLayout.show(mainContainer, "HOME");
}
// PUT THIS METHOD - Creates the dashboard home screen
private JPanel createHomePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    
    // Title at top
    JLabel title = new JLabel("Student Dashboard", SwingConstants.CENTER);
    title.setFont(new Font("Segoe UI", Font.BOLD, 28));
    title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
    panel.add(title, BorderLayout.NORTH);
    
    // Center panel with buttons
    JPanel centerPanel = new JPanel(new GridLayout(4, 2, 20, 20));
    centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    
    // Create styled buttons
    JButton btnCourse = createMenuButton("📚 Course Registration", "Register for courses");
    JButton btnEnroll = createMenuButton("📝 My Enrollments", "View enrolled courses");
    JButton btnTranscript = createMenuButton("📜 Transcript", "View academic history");
    JButton btnGrad = createMenuButton("🎓 Graduation Audit", "Check graduation requirements");
    JButton btnTuition = createMenuButton("💰 Pay Tuition", "Make tuition payments");
    JButton btnProfile = createMenuButton("👤 My Profile", "Update profile information");
    JButton btnLogout = createMenuButton("🚪 Logout", "Exit dashboard");
    
    // Add action listeners
    btnCourse.addActionListener(e -> showPanel("COURSE_REGISTRATION"));
    btnEnroll.addActionListener(e -> showPanel("MY_ENROLLMENTS"));
    btnTranscript.addActionListener(e -> showPanel("TRANSCRIPT"));
    btnGrad.addActionListener(e -> showPanel("GRADUATION_AUDIT"));
    btnTuition.addActionListener(e -> showPanel("PAY_TUITION"));
    btnProfile.addActionListener(e -> showPanel("MY_PROFILE"));
    btnLogout.addActionListener(e -> onLogout());
    
    // Add buttons to panel
    centerPanel.add(btnCourse);
    centerPanel.add(btnEnroll);
    centerPanel.add(btnTranscript);
    centerPanel.add(btnGrad);
    centerPanel.add(btnTuition);
    centerPanel.add(btnProfile);
    centerPanel.add(new JLabel()); // Empty space
    centerPanel.add(btnLogout);
    
    panel.add(centerPanel, BorderLayout.CENTER);
    
    // Footer
    JLabel footer = new JLabel("Welcome! Select an option above to get started.", SwingConstants.CENTER);
    footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    panel.add(footer, BorderLayout.SOUTH);
    
    return panel;
}
// PUT THIS METHOD - Creates pretty menu buttons
private JButton createMenuButton(String text, String tooltip) {
    JButton button = new JButton(text);
    button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    button.setToolTipText(tooltip);
    button.setFocusPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    // Add hover effect
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(100, 149, 237));
            button.setForeground(Color.WHITE);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(null);
            button.setForeground(null);
        }
    });
    
    return button;
}
// PUT THIS METHOD - Switches to a different panel
private void showPanel(String panelName) {
    cardLayout.show(mainContainer, panelName);
    
    // Update window title
    String title = "Student Dashboard - ";
    switch (panelName) {
        case "COURSE_REGISTRATION":
            title += "Course Registration";
            break;
        case "MY_ENROLLMENTS":
            title += "My Enrollments";
            break;
        case "TRANSCRIPT":
            title += "Transcript";
            break;
        case "GRADUATION_AUDIT":
            title += "Graduation Audit";
            break;
        case "PAY_TUITION":
            title += "Pay Tuition";
            break;
        case "MY_PROFILE":
            title += "My Profile";
            break;
        default:
            title = "Student Dashboard";
    }
    setTitle(title);
}
// PUT THIS METHOD - Makes the original dashboard buttons work
private void setupButtonActions() {
    // This connects your original NetBeans buttons to the panels
    btnCourseRegistration.addActionListener(e -> showPanel("COURSE_REGISTRATION"));
    btnMyEnrollments.addActionListener(e -> showPanel("MY_ENROLLMENTS"));
    btnTranscript.addActionListener(e -> showPanel("TRANSCRIPT"));
    btnGraduationAudit.addActionListener(e -> showPanel("GRADUATION_AUDIT"));
    btnPayTuition.addActionListener(e -> showPanel("PAY_TUITION"));
    btnMyProfile.addActionListener(e -> showPanel("MY_PROFILE"));
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
        btnCourseRegistration = new javax.swing.JButton();
        btnMyEnrollments = new javax.swing.JButton();
        btnTranscript = new javax.swing.JButton();
        btnGraduationAudit = new javax.swing.JButton();
        btnPayTuition = new javax.swing.JButton();
        btnMyProfile = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Student Dashboard");

        btnCourseRegistration.setText("Course Registration");

        btnMyEnrollments.setText("My Enrollments");

        btnTranscript.setText("Transcript");

        btnGraduationAudit.setText("Graduation Audit");

        btnPayTuition.setText("Pay Tuition");

        btnMyProfile.setText("My Profile");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCourseRegistration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTranscript, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPayTuition, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMyEnrollments, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGraduationAudit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMyProfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(180, 180, 180))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(btnLogout)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblTitle)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCourseRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMyEnrollments, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGraduationAudit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTranscript, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPayTuition, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMyProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
         onLogout();
    }//GEN-LAST:event_btnLogoutActionPerformed
private void onLogout() {
        JOptionPane.showMessageDialog(this, "Logging out...");
        this.dispose();
        // Optionally go back to main login frame
        // new MainLoginFrame(system).setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentDashboard().setVisible(true);
            }
        });
    }
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCourseRegistration;
    private javax.swing.JButton btnGraduationAudit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyEnrollments;
    private javax.swing.JButton btnMyProfile;
    private javax.swing.JButton btnPayTuition;
    private javax.swing.JButton btnTranscript;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}









