/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.StudentWorkArea;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author tasmiya
 */
public class ProfileManagementJPanel extends javax.swing.JPanel {
    
    private StudentProfile currentProfile;
    private StudentProfile originalProfile; // Keep backup for cancel
    private boolean isEditMode = false;

    /**
     * Creates new form ProfileManagementJPanel
     */
    public ProfileManagementJPanel() {
        initComponents();
    setupComponents();
    loadProfile();
    setFieldsEditable(false); // Start in view mode
    setupButtons();
    }
    // PUT THIS METHOD - Sets up dropdowns and formatting
private void setupComponents() {
    // Setup graduation year dropdown
    comboGraduation.removeAllItems();
    int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    for (int i = 0; i <= 5; i++) {
        comboGraduation.addItem(String.valueOf(currentYear + i));
    }
    
    // Set profile picture placeholder
    lblProfilePicture.setText("👤");
    lblProfilePicture.setFont(new Font("Segoe UI", Font.PLAIN, 80));
    lblProfilePicture.setHorizontalAlignment(SwingConstants.CENTER);
    lblProfilePicture.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
    lblProfilePicture.setOpaque(true);
    lblProfilePicture.setBackground(new Color(240, 240, 240));
}
// PUT THIS METHOD - Loads your profile information
private void loadProfile() {
    // Create empty profile for new data input
    currentProfile = new StudentProfile(
        "",  // First name - empty
        "",  // Last name - empty
        "002123456",  // Student ID - auto-generated or set
        "",  // Email - empty
        "",  // Phone - empty
        "",  // Address - empty
        "Master of Science in Information Systems",  // Program - fixed
        "Fall 2024",  // Enrollment Date - auto-set
        "Spring 2026",  // Expected Graduation - default
        "",  // Emergency Contact Name - empty
        ""   // Emergency Contact Phone - empty
    );
    
    // Keep a backup copy
    originalProfile = new StudentProfile(currentProfile);
    
    // Display the profile
    displayProfile();
}
private void displayProfile() {
    // Fill in all the fields
    txtFirstName.setText(currentProfile.getFirstName());
    txtLastName.setText(currentProfile.getLastName());
    lblStudentId.setText(currentProfile.getStudentId());
    txtEmail.setText(currentProfile.getEmail());
    txtPhone.setText(currentProfile.getPhone());
    txtAddress.setText(currentProfile.getAddress());
    lblProgram.setText(currentProfile.getProgram());
    lblEnrollmentDate.setText(currentProfile.getEnrollmentDate());
    comboGraduation.setSelectedItem(currentProfile.getExpectedGraduation());
    txtEmergencyName.setText(currentProfile.getEmergencyContactName());
    txtEmergencyPhone.setText(currentProfile.getEmergencyContactPhone());
}
// PUT THIS METHOD - Turns editing on or off
private void setFieldsEditable(boolean editable) {
     
    isEditMode = editable;
    
    // Make these fields editable/non-editable
    txtFirstName.setEditable(editable);
    txtLastName.setEditable(editable);
    txtEmail.setEditable(editable);
    txtPhone.setEditable(editable);
    txtAddress.setEditable(editable);
    comboGraduation.setEnabled(true);  // Always enabled!
    txtEmergencyName.setEditable(editable);
    txtEmergencyPhone.setEditable(editable);
    
    // Change background color to show edit mode
    Color bgColor = editable ? Color.WHITE : new Color(245, 245, 245);
    txtFirstName.setBackground(bgColor);
    txtLastName.setBackground(bgColor);
    txtEmail.setBackground(bgColor);
    txtPhone.setBackground(bgColor);
    txtAddress.setBackground(bgColor);
    
    // CHANGED: Keep buttons always enabled
    btnSave.setEnabled(true);     // ← CHANGED!
    btnCancel.setEnabled(true);   // ← CHANGED!
    btnReset.setEnabled(true);    // ← CHANGED!
    
    // Change button text based on mode
    if (editable) {
        btnSave.setText("Save Changes");
    } else {
        btnSave.setText("Edit Profile");
    }

    
}
// PUT THIS METHOD - Saves your changes
private void saveChanges() {
    // Validate input first
    if (!validateInput()) {
        return; // Don't save if validation fails
    }
    
    // Update profile with new values
    currentProfile.setFirstName(txtFirstName.getText().trim());
    currentProfile.setLastName(txtLastName.getText().trim());
    currentProfile.setEmail(txtEmail.getText().trim());
    currentProfile.setPhone(txtPhone.getText().trim());
    currentProfile.setAddress(txtAddress.getText().trim());
    currentProfile.setExpectedGraduation((String) comboGraduation.getSelectedItem());
    currentProfile.setEmergencyContactName(txtEmergencyName.getText().trim());
    currentProfile.setEmergencyContactPhone(txtEmergencyPhone.getText().trim());
    
    // Update backup
    originalProfile = new StudentProfile(currentProfile);
    
    // Turn off edit mode
    setFieldsEditable(false);
    
    // Show success message
    JOptionPane.showMessageDialog(this,
        "Profile updated successfully!\n\n" +
        "Your changes have been saved.",
        "Success",
        JOptionPane.INFORMATION_MESSAGE);
}
// PUT THIS METHOD - Checks if your input is valid
private boolean validateInput() {
    // Check first name
    if (txtFirstName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "First name cannot be empty!",
            "Validation Error",
            JOptionPane.ERROR_MESSAGE);
        txtFirstName.requestFocus();
        return false;
    }
    
    // Check last name
    if (txtLastName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Last name cannot be empty!",
            "Validation Error",
            JOptionPane.ERROR_MESSAGE);
        txtLastName.requestFocus();
        return false;
    }
    
    // Check email format
    String email = txtEmail.getText().trim();
    if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
        JOptionPane.showMessageDialog(this,
            "Please enter a valid email address!\n\n" +
            "Example: john.doe@northeastern.edu",
            "Validation Error",
            JOptionPane.ERROR_MESSAGE);
        txtEmail.requestFocus();
        return false;
    }
    
    // Check phone format
    String phone = txtPhone.getText().trim();
    if (phone.isEmpty() || phone.length() < 10) {
        JOptionPane.showMessageDialog(this,
            "Please enter a valid phone number!\n\n" +
            "Example: 617-555-1234",
            "Validation Error",
            JOptionPane.ERROR_MESSAGE);
        txtPhone.requestFocus();
        return false;
    }
    
    // Check emergency contact
    if (txtEmergencyName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Emergency contact name is required!",
            "Validation Error",
            JOptionPane.ERROR_MESSAGE);
        txtEmergencyName.requestFocus();
        return false;
    }
    
    if (txtEmergencyPhone.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Emergency contact phone is required!",
            "Validation Error",
            JOptionPane.ERROR_MESSAGE);
        txtEmergencyPhone.requestFocus();
        return false;
    }
    
    return true; // All validations passed!
}
// PUT THIS METHOD - Cancels your changes
private void cancelChanges() {
    // Ask for confirmation
    int confirm = JOptionPane.showConfirmDialog(this,
        "Discard all changes?\n\n" +
        "Any unsaved changes will be lost.",
        "Confirm Cancel",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
    
    if (confirm == JOptionPane.YES_OPTION) {
        // Restore original values
        currentProfile = new StudentProfile(originalProfile);
        displayProfile();
        setFieldsEditable(false);
        
        JOptionPane.showMessageDialog(this,
            "Changes discarded.\n\n" +
            "Profile restored to previous values.",
            "Cancelled",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
// PUT THIS METHOD - Resets everything
private void resetToOriginal() {
    int confirm = JOptionPane.showConfirmDialog(this,
        "Reset all fields to original values?",
        "Confirm Reset",
        JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        displayProfile();
        JOptionPane.showMessageDialog(this,
            "Fields reset to current saved values.",
            "Reset Complete",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
// PUT THIS METHOD - Changes your profile picture (simulated)
private void changeProfilePhoto() {
    // Simulate photo selection
    String[] options = {"😀", "😎", "🎓", "💼", "👨‍💻", "👩‍💻", "📚", "Cancel"};
    
    String selection = (String) JOptionPane.showInputDialog(
        this,
        "Select a profile icon:\n(In a real system, you would upload a photo)",
        "Change Profile Picture",
        JOptionPane.PLAIN_MESSAGE,
        null,
        options,
        options[0]
    );
    
    if (selection != null && !selection.equals("Cancel")) {
        lblProfilePicture.setText(selection);
        lblProfilePicture.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        
        JOptionPane.showMessageDialog(this,
            "Profile picture updated!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
// PUT THIS METHOD - Makes all buttons work!
private void setupButtons() {
    // Enable Edit Mode (Double-click any field)
     
    // Enable Edit Mode (Double-click any field)
    txtFirstName.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getClickCount() == 2 && !isEditMode) {
                setFieldsEditable(true);
                JOptionPane.showMessageDialog(ProfileManagementJPanel.this,
                    "Edit mode enabled!\n\n" +
                    "Make your changes and click 'Save Changes' when done.",
                    "Edit Mode",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
    
    // Save Button - using e1
    btnSave.addActionListener(e1 -> {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Save all changes to your profile?",
            "Confirm Save",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            saveChanges();
        }
    });
    
    // Cancel Button - using e2
    btnCancel.addActionListener(e2 -> cancelChanges());
    
    // Reset Button - using e3
    btnReset.addActionListener(e3 -> resetToOriginal());
    
    // Change Photo Button - using e4
    btnChangePhoto.addActionListener(e4 -> changeProfilePhoto());


}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
<<<<<<< Updated upstream
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
=======
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
>>>>>>> Stashed changes
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblProfilePicture = new javax.swing.JLabel();
        btnChangePhoto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        lblStudentId = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        lblProgram = new javax.swing.JLabel();
        lblEnrollmentDate = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        comboGraduation = new javax.swing.JComboBox<>();
        txtEmergencyName = new javax.swing.JTextField();
        txtEmergencyPhone = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("My Profile");

        lblProfilePicture.setText("Profile Picture");

        btnChangePhoto.setText("Change Photo");

        jLabel2.setText("First Name:");

        jLabel3.setText("Last Name:");

        txtFirstName.setText(" ");

        txtLastName.setText(" ");

        lblStudentId.setText("Student ID");

        jLabel4.setText("Email:");

        jLabel5.setText("Phone:");

        jLabel6.setText("Address:");

        txtEmail.setText(" ");

        txtPhone.setText(" ");

        lblProgram.setText("Program:");

        lblEnrollmentDate.setText("Enrollment Date:");

        jLabel9.setText("Expected Graduation:");

        jLabel10.setText("Emergency Contact Name:");

        jLabel11.setText("Emergency Contact Phone:");

        btnSave.setText("Save Changes");

        btnCancel.setText("Cancel");

        btnReset.setText("Reset");

        comboGraduation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtEmergencyName.setText(" ");

        txtEmergencyPhone.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtLastName))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtFirstName))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblStudentId)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblProfilePicture)
                                        .addGap(48, 48, 48)
                                        .addComponent(btnChangePhoto))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEmail)
                                            .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                            .addComponent(txtAddress)))
                                    .addComponent(lblProgram)
                                    .addComponent(lblEnrollmentDate)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(comboGraduation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 56, Short.MAX_VALUE))
                                            .addComponent(txtEmergencyName)
                                            .addComponent(txtEmergencyPhone))))
                                .addGap(0, 31, Short.MAX_VALUE)))))
                .addGap(267, 267, 267))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnSave)
                .addGap(84, 84, 84)
                .addComponent(btnCancel)
                .addGap(93, 93, 93)
                .addComponent(btnReset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProfilePicture)
                            .addComponent(btnChangePhoto))
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2))
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblStudentId)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblProgram)
                .addGap(18, 18, 18)
                .addComponent(lblEnrollmentDate)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(comboGraduation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtEmergencyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11))
                    .addComponent(txtEmergencyPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnReset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
<<<<<<< Updated upstream
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
=======
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
>>>>>>> Stashed changes
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChangePhoto;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> comboGraduation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblEnrollmentDate;
    private javax.swing.JLabel lblProfilePicture;
    private javax.swing.JLabel lblProgram;
    private javax.swing.JLabel lblStudentId;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmergencyName;
    private javax.swing.JTextField txtEmergencyPhone;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhone;
<<<<<<< Updated upstream
    // End of variables declaration                   
=======
    // End of variables declaration//GEN-END:variables
>>>>>>> Stashed changes
// End of variables declaration                   
    
    // PUT THIS CLASS HERE - At the very bottom!
    class StudentProfile {
        private String firstName;
        private String lastName;
        private String studentId;
        private String email;
        private String phone;
        private String address;
        private String program;
        private String enrollmentDate;
        private String expectedGraduation;
        private String emergencyContactName;
        private String emergencyContactPhone;
        
        // Constructor
        public StudentProfile(String firstName, String lastName, String studentId,
                            String email, String phone, String address,
                            String program, String enrollmentDate, String expectedGraduation,
                            String emergencyContactName, String emergencyContactPhone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.studentId = studentId;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.program = program;
            this.enrollmentDate = enrollmentDate;
            this.expectedGraduation = expectedGraduation;
            this.emergencyContactName = emergencyContactName;
            this.emergencyContactPhone = emergencyContactPhone;
        }
        
        // Copy constructor (for backup)
        public StudentProfile(StudentProfile other) {
            this.firstName = other.firstName;
            this.lastName = other.lastName;
            this.studentId = other.studentId;
            this.email = other.email;
            this.phone = other.phone;
            this.address = other.address;
            this.program = other.program;
            this.enrollmentDate = other.enrollmentDate;
            this.expectedGraduation = other.expectedGraduation;
            this.emergencyContactName = other.emergencyContactName;
            this.emergencyContactPhone = other.emergencyContactPhone;
        }
        
        // Getters
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getStudentId() { return studentId; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getAddress() { return address; }
        public String getProgram() { return program; }
        public String getEnrollmentDate() { return enrollmentDate; }
        public String getExpectedGraduation() { return expectedGraduation; }
        public String getEmergencyContactName() { return emergencyContactName; }
        public String getEmergencyContactPhone() { return emergencyContactPhone; }
        
        // Setters
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setEmail(String email) { this.email = email; }
        public void setPhone(String phone) { this.phone = phone; }
        public void setAddress(String address) { this.address = address; }
        public void setExpectedGraduation(String expectedGraduation) { 
            this.expectedGraduation = expectedGraduation; 
        }
        public void setEmergencyContactName(String emergencyContactName) { 
            this.emergencyContactName = emergencyContactName; 
        }
        public void setEmergencyContactPhone(String emergencyContactPhone) { 
            this.emergencyContactPhone = emergencyContactPhone; 
        }
    }

}
